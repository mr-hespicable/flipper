package com.github.websafe;

import com.github.websafe.apiProcessing.APIRequest;
import com.github.websafe.apiProcessing.GetInfo;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class FlipperCommand extends CommandBase {

    private static final Logger logger = Logger.getLogger(FlipperCommand.class.getName());
    public static String tempor = null;
    public static APIRequest api = new APIRequest();



    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandName() {
        return "flipper";
    }

    public static final String HelpMenu =
            "Available arguments:\n"
            +"\n";

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return HelpMenu;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        new Thread(() -> {
            System.out.println(Arrays.toString(args));

            if (args.length >= 1) {
                System.out.println(args[0]);
                switch (args[0].toLowerCase()) {
                    case "start":
                        if (!Objects.equals(args[1], "")) {
                            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(args[1]));
                            tempor = api.getData(args[1], Boolean.FALSE);
                        }
                        else {
                            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("no url provided"));
                        }
                        break;
                    case "stop":
                        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(GetInfo.GetResponse("https://api.hypixel.net/resources/skyblock/election").toString()));
                        break;
                }
            }
        }).start();
    }
}