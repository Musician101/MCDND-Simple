package io.musician101.mcdndsimple.forge.network;

import com.google.gson.reflect.TypeToken;
import io.musician101.mcdndsimple.common.character.nonplayer.NonPlayerStorage;
import io.musician101.mcdndsimple.common.reference.Reference;
import io.musician101.mcdndsimple.forge.ForgeMCDNDSimple;
import io.musician101.mcdndsimple.forge.gui.npc.NonPlayersGui;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import static io.musician101.mcdndsimple.common.serialization.Keys.GSON;

public class Network {

    public final static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MOD_ID, "network"), () -> Reference.VERSION, s -> s.equals(Reference.VERSION), s -> s.equals(Reference.VERSION));
    private static int id = 0;

    private Network() {

    }

    public static void init() {
        INSTANCE.registerMessage(id++, NonPlayerListPacket.class, (msg, packetBuffer) -> packetBuffer.writeString(GSON.toJson(msg.getList())), packetBuffer -> new NonPlayerListPacket(GSON.fromJson(packetBuffer.readString(32767), new TypeToken<List<String>>(){}.getType())), (msg, contextSupplier) -> {
            Context context = contextSupplier.get();
            if (context.getDirection().getOriginationSide() == LogicalSide.SERVER) {
                Minecraft.getInstance().displayGuiScreen(new NonPlayersGui(msg.getList()));
            }

            context.setPacketHandled(true);
        });
        INSTANCE.registerMessage(id++, DeleteNonPlayerPacket.class, (msg, packetBuffer) -> packetBuffer.writeString(msg.getName()), packetBuffer -> new DeleteNonPlayerPacket(packetBuffer.readString(32767)), (msg, contextSupplier) -> {
            Context context = contextSupplier.get();
            if (context.getDirection().getOriginationSide() == LogicalSide.CLIENT) {
                EntityPlayerMP player = context.getSender();
                if (player == null) {
                    return;
                }

                NonPlayerStorage storage = ForgeMCDNDSimple.getInstance().getNonPlayerSheetStorage();
                String name = msg.getName();
                if (storage.delete(player.getUniqueID(), name)) {
                    player.sendMessage(new TextComponentString(name + " has been deleted.").setStyle(new Style().setColor(TextFormatting.GREEN)));
                }
                else {
                    player.sendMessage(new TextComponentString("You can't delete " + name + ".").setStyle(new Style().setColor(TextFormatting.RED)));
                }
            }
        });
    }
}
