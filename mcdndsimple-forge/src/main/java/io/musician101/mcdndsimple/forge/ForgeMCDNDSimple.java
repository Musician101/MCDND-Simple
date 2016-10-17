package io.musician101.mcdndsimple.forge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = ForgeMCDNDSimple.MOD_ID,
        name = ForgeMCDNDSimple.MOD_NAME,
        version = ForgeMCDNDSimple.VERSION
)
public class ForgeMCDNDSimple
{

    public static final String MOD_ID = "mcdndsimple";
    public static final String MOD_NAME = "Mcdndsimple";
    public static final String VERSION = "1.0-SNAPSHOT";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    }
}
