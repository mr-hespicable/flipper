package com.websafe.flipper.api_processing;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIRequest {
    //private static final String apikey = "812beee0-95e9-487a-ba88-01596b087c8c";
    private static final Logger logger = Logger.getLogger(APIRequest.class.getName());
    private static String sponse = null;

    public String getData(String StringyURL) {
        try {
            URL url = new URL(StringyURL);
            try {
                // Open a connection to the URL
                HttpURLConnection connection = getConnection(url);

                //System.out.println("connection to " + url + " opened.");

                // Get the response code
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Response Code: " + responseCode);
                }
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
            } catch (MalformedURLException e) {
                System.out.println("shit.");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred ", e);
        }
        return sponse;
    }

    @NotNull
    private static HttpURLConnection getConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                /* removed as apikey not needed
                if (NeedsApiKey.equals(Boolean.TRUE)) {
                    connection.setRequestMethod(apikey);
                    System.out.println("apikey used, " + apikey);
                }
                */

        // Set the request method to GET
        connection.setRequestMethod("GET");
        return connection;
    }

}
