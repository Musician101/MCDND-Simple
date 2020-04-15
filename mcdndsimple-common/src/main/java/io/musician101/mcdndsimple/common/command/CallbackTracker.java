package io.musician101.mcdndsimple.common.command;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.annotation.Nonnull;

public class CallbackTracker<C> {

    private final ConcurrentMap<UUID, Consumer<C>> callbacks = new ConcurrentHashMap<>();
    private final LoadingCache<Consumer<C>, UUID> callbackCache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).<Consumer<C>, UUID>removalListener(notification -> callbacks.remove(notification.getValue(), notification.getKey())).build(new CacheLoader<Consumer<C>, UUID>() {

        @Override
        public UUID load(@Nonnull Consumer<C> callback) {
            UUID ret = UUID.randomUUID();
            callbacks.putIfAbsent(ret, callback);
            return ret;
        }
    });

    public Consumer<C> get(UUID uuid) {
        return callbacks.get(uuid);
    }

    public UUID getIdForCallback(Consumer<C> callback) {
        return callbackCache.getUnchecked(callback);
    }

    public Set<UUID> getKeys() {
        return callbacks.keySet();
    }
}
