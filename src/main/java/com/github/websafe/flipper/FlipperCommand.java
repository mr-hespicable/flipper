package com.github.websafe.flipper;

import com.github.websafe.api.APIRequest;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlipperCommand extends CommandBase {

    private static final Logger logger = Logger.getLogger(FlipperCommand.class.getName());

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
                switch (args[0].toLowerCase()) {
                    case "start":
                        if (!Objects.equals(args[1], "")) {
                            declareShit(args[1]);
                        }
                        else {
                            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("no url provded"));
                        }
                        break;
                    case "stop":
                        break;
                }
            }
        }).start();

    }

    public void declareShit(String url) {
        try {
            APIRequest g = new APIRequest();
            URL url1 = new URL(url);
            g.getAuctions(url1);
        }
        catch (MalformedURLException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }
}
