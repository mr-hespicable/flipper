package com.websafe.flipper;

import com.websafe.flipper.apiProcessing.APIRequest;
import com.websafe.flipper.algorithm.GetAuctionInfo;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
public class FlipperCommand extends CommandBase {

    public static String tempor = null;
    public static APIRequest api = new APIRequest();
    public static GetAuctionInfo ah = new GetAuctionInfo();

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandName() {
        return "flipper";
    }

    private static final String HelpMenu =
            "Available arguments:\n"
            +"start: Starts the flipper.\n";

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return HelpMenu;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args){
        new Thread(() -> {
            System.out.println(Arrays.toString(args));

            if (args.length >= 1) {
                System.out.println(args[0]);
                switch (args[0].toLowerCase()) {
                    //TODO: add more commands and create default functionality which returns help msg
                    case "start":
                        if (!Objects.equals(args[1], "")) {
                            sender.addChatMessage(new ChatComponentText(args[1]));
                        }
                        else {
                            sender.addChatMessage(new ChatComponentText("no url provided"));
                        }
                        break;
                    case "stop":
                        try {
                            ah.getAuction();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;

                    case "test":
                        break;
                }
            } else {
                sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
        }).start();
    }
}