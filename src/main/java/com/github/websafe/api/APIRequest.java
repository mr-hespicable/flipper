package com.github.websafe.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class APIRequest {
    private static final String apikey = "812beee0-95e9-487a-ba88-01596b087c8c";
    private static final Logger logger = Logger.getLogger(APIRequest.class.getName());

    public void getAuctions(URL url) {
        try {
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();


            // Print the response
            String sponsive = response.toString();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(sponsive));
            System.out.println("this" + sponsive);

            // Disconnect the connection
            connection.disconnect();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }

}
