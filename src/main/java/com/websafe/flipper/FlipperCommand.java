package com.websafe.flipper;

import com.websafe.flipper.apiProcessing.APIRequest;
import com.websafe.flipper.algorithm.GetAuctionInfo;
import com.websafe.flipper.helper.Decoder;
import com.websafe.flipper.algorithm.GetItemInfo;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
public class FlipperCommand extends CommandBase {

    public static APIRequest api = new APIRequest();
    public static GetAuctionInfo ah = new GetAuctionInfo();
    public static Decoder dec = new Decoder();
    private static final String temp = "H4sIAAAAAAAAAHWUT28bRRTAn+OkdVxQ1AMSJ/oKDU1w48TrdexE4pA6/yxoUsVpqgpV1nj32V6yO7PMzibxhQsXPgAHhOAEkiU+hj9KPgjizXodqkgcPOt583t/570pAyxDISgDQGEBFgK/sFGApbZKpSmUoWjEcBkWSXqjnCiGVyEUMxJKUIBHb2Rfk7gU/ZAKRVg+Dnw6DMUwYfyfMjz0gyQOxZiNfKs0lVj6BJ5MJ80jEhq7Hst2cTrxW47Dn9ZarVZvrMMqA12jSQ7NyB57ldoWf3bW+LuecZUaYxXG2jow2B4J6VFOOqs56qzO2dbqB/C+iMRwDm/N4a07uMFwjeGXSqYJ7hkjvEvsxkT+TKeRqzTmGs2qVXnOKsckwlnEouLsuPdCdmtOlaP+isF9GpBMKCdrtWrjHuvsZOxTW4i5a1Gp5dQ8VIusM9KRhsIwGFJeBVGpO/edM/qZ/fsd/25//YvX93fb336xW76br1lpn2IzQi4/36TGTqfDshfW2iEJM2LRoWBfcogX84Mjra5Z5QK+4M1rrQx5JlDyDmie0ffpFUlhiCGwWJMr+U6l6CuUyuBIXBEKHAXDEZJU6XAEnzNxwH0npLHOQrqiEI3CNCFMVESoBrY8HoeENOMikiZBdmxGQYKBoehp7u2M/NSjBC3sZ/ePY/ZuxCXhQKsIPmXoOrDpJdgf23vmjmBZlQ18OZ1sH6ZhiF0ymHXFLr7N2L7S0ua3trXprmdt3Y3FtUw4lZkxjAJpK8HB63GeN9VtLzcT8pT0E0xjm5XASNwEURrBswwS9qrziKrIldI4D+86CENbnabRIq+JkD7STRwqn2z6kmerP4ZHzJCkKKDEpvEiM+ze/vGnTe+MfkgDzSVhX+dclbbgPldRP8HDUCkNn7D8otPBtorikOx1slIVdnhWp5NwOrkU04nG+c6ur96dH3fauP/m5Ojg9ARfnp6ed/E/vAhLngrZ8s8//ViCxRMRETT4+HWqCY9U6Cv9POHyKmNj2r79/e//W6EMKwc3nD3Ppg76qaGkCCta8HCPe2k81MIn+/wUSlCKlB8MAtKwGLOfInycn/eyfmJoqZQ9ZY/P906+6b3tnB8fnPWy0Mvw0Ydtxaq+HYxeMhsMVi2y18FsJnqD2UxYg0Uo67t+zwXx3VDMBA+G2cjYDb+ii2nKITxr9QdOUzRpw6v7gw2X3NaGaNTdDdd1dlrOtuf57qAEyyaIKDEiimGlvlnfdOpYq+26Lu69AliAB7PnjS3DvyyEzi3bBQAA";


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
            +"start: Starts the flipper.\n"
            +"stop: Stops the flipper.\n";

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

                    case "set":
                        switch(args[1].toLowerCase()) {
                            case "budget":

                        }
                        break;
                }
            } else {
                sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
        }).start();
    }
}
