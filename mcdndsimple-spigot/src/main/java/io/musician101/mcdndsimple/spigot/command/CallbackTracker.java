package io.musician101.mcdndsimple.spigot.command;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import org.bukkit.command.CommandSender;

public class CallbackTracker {

    private final ConcurrentMap<UUID, Consumer<CommandSender>> callbacks = new ConcurrentHashMap<>();
    private final LoadingCache<Consumer<CommandSender>, UUID> callbackCache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).removalListener(notification -> callbacks.remove(notification.getValue(), notification.getKey())).build(new CacheLoader<Consumer<CommandSender>, UUID>() {

        @Override
        public UUID load(@Nonnull Consumer<CommandSender> callback) {
            UUID ret = UUID.randomUUID();
            callbacks.putIfAbsent(ret, callback);
            return ret;
        }
    });

    public Consumer<CommandSender> get(UUID uuid) {
        return callbacks.get(uuid);
    }

    public UUID getIdForCallback(Consumer<CommandSender> callback) {
        return callbackCache.getUnchecked(callback);
    }
}
