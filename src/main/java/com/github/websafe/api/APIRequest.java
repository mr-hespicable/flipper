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
    private static String sponse = null;

    public String getResponse(URL url, Boolean NeedsApiKey) {

        try {
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.out.println("connection to " + url + " opened.");

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




            /*Write response to console
             * for some reason it doesn't write to chat so idfk whats going on there... maybe >>chat has a limit and it reaches it?<<
             */
            //System.out.println(response);
            sponse = response.toString();

            // Disconnect the connection
            connection.disconnect();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
        return sponse;
    }

}
