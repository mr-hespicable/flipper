package com.github.websafe;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.Objects;

public class FlipperCommand extends CommandBase {

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandName() {
        return HelpMenu;
    }

    public static final String HelpMenu =
            "Available arguments:\n"
            +"\n";

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "all flipper commands";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        new Thread(() -> {
            System.out.println(Arrays.toString(args));

            if (args.length >= 1) {
                switch (args[0].toLowerCase()) {
                    case "start":
                        break;
                    case "stop":
                        int x = 1;
                        break;
                }
            }
        }).start();
    }
}
