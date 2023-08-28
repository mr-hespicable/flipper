package com.websafe.flipper;

import com.websafe.flipper.proxy.CommonProxy;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MODID, name=Reference.NAME, version=Reference.VERSION)
public class MainFlippa {

    @SidedProxy(modId = Reference.MODID, serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            System.out.println("**********INITIALIZING FLIPPER**********");
            ClientCommandHandler.instance.registerCommand(new FlipperCommand());
        }
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }
}
// /flipper stop
// §