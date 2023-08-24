package com.github.websafe;

import com.github.websafe.apiProcessing.APIRequest;
import com.github.websafe.flipper.GetAuctionInfo;
import com.github.websafe.helper.Decoder;

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
    public static Decoder dec = new Decoder();
    private static final String temp = "CgALaGVsbG8gd29ybGQDAAR0ZXN0AAAAAAA";

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
            +"\n";

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
                            tempor = api.getData(args[1], Boolean.FALSE);
                        }
                        else {
                            sender.addChatMessage(new ChatComponentText("no url provided"));
                        }
                        break;
                    case "stop":
                        ah.getAuction();
                        break;
                    case "test":
                        try {
                            sender.addChatMessage(new ChatComponentText(dec.itemBytes(temp).toString()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;

                }
            } else {
                sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
        }).start();
    }
}
