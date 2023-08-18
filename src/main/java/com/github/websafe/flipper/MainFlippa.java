package com.github.websafe.flipper;

import com.github.websafe.FlipperCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "flipper", useMetadata=true)
public class MainFlippa {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("**********INITALIZING FLIPPER**********");
        ClientCommandHandler.instance.registerCommand(new FlipperCommand());
    }
}
