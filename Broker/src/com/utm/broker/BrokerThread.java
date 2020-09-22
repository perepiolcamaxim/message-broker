package com.utm.broker;

import com.google.gson.Gson;
import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;
import java.io.*;
import java.net.Socket;

public class BrokerThread implements Runnable
{
    private Socket clientSocket;
    private PayloadHandler handler;

    public BrokerThread(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
        handler = new PayloadHandler();
    }

    @Override
    public void run()
    {
        try
        {
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            int outputLine;

            while ((inputLine = reader.readLine()) != null)
            {
                Gson gson = new Gson();
                Payload payload = gson.fromJson(inputLine, Payload.class);

                outputLine = handler.handle(clientSocket, payload);

                if(outputLine == 1)
                {
                    writer.println("Payload received!");
                    writer.flush();
                }
                else
                {
                    writer.println("Hello receiver!");
                    writer.flush();
                }
            }

            writer.close();
            reader.close();
            clientSocket.close();
        }
        catch (IOException e)
        {
            System.out.println("Client disconected");
        }
    }
}
