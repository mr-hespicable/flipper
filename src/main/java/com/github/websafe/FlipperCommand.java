package com.github.websafe;

import com.github.websafe.apiProcessing.APIRequest;
import com.github.websafe.flipper.GetAuctionInfo;
import com.github.websafe.helper.Decoder;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.Objects;
public class FlipperCommand extends CommandBase {

    public static String tempor = null;
    public static APIRequest api = new APIRequest();
    public static GetAuctionInfo ah = new GetAuctionInfo();
    public static Decoder dec = new Decoder();
    private static final String temp = "H4sIAAAAAAAAAF1TzZLaRhAedu0YuPiYSlUOSpWPZneQEBKu8kEr5EUsIxZWSEiXLf0M0rAjRKSRAb2Dn4P34Hn8DKkMeFNOcpqu/r7u7+vumjYALdAgbQBA4wpckbjxrQHe6nm1YY02uGZBcg1aIxLjLzRISs76qw3aTy8VpdPdBhdNcGXG4APu4VVPUXBHlUO5I8lx2FH7q35HUqRIlaSuMlD7vO6xyLe4YASXLdBkeM+qApcX6SZ46wS0wo3veJcnpj6GgdulkTRPw6VGzGGeINuDyDYhWkddqzZ2SN89mLpGotH4q5/R0l/QF5NofV5LrNpZ+8N5huz0xV8nEGUW9dbj1BPR3rIXMrItgtYa9IhZ6kRLzM3dIRT9bXjvTD2u+9pnEmW0ju/pn6Eop1Ft1CZ51RTpJsy+wHg5povM2ccuPfju7OIzHo27/tMPXnzv9OKRc/CX6IL9mI3rUWvytOj9N3fmL8flq3Yej+a7KVG//qtHFbq08l3r4Lk+nGQyjfUB9JfpxcekXkie7b+g2qFTdwZRveBzzw5Wvdh59ayHap9YQ622RE+ybANamcN3EPU4XqPMhF49X1suqr21KXqZKSJxvka2RibaT3+h60DPnafxvfF/7+ebsVCc01A3kyk572i/DZ/Mh3/w1Sz/+Y7gw2r2+TO/fBu8i0m5pcGhBd5M8gI3efId+ON0VOyUlEKYM0axwCOcbdnho2AKu3wT4+JCISuBpbi44IGwCw4Cy4UoDYqEpxhockq1vbm54T1/57F+QT4JpyOGp2P/lr8y/AghPBs5HeXTkRqPpt4Eb6wgw+BXnjLOqoKdVmdN4e7ihpt+b+xZEWiMFSSsGC6b568DfrNHC2tozJ9N61l7vpva9sR4NtCj7fGWVcUZH/pQVAZKv9dR+hB2eqtQ7QwiFXaCeCDLMFCVVTxoghYjGS5ZkG3Be/W2q9yKkqB8kiVBQwBcgV+GQRYkGFwD8DfHMEFowAMAAA==";

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
                        break;
                        //System.out.println(dec.itemBytes(temp));
                }
            } else {
                sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
        }).start();
    }
}
