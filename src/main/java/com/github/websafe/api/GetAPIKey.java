package com.github.websafe.api;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import java.util.Arrays;

public class GetAPIKey extends CommandBase {

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandName() {
        return "setapikey";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "blah blah blah";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        new Thread(() -> {

        }).start();
    }
}
