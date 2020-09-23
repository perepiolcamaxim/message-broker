package com.utm.publicher;

import com.utm.common.ConnectionSetting;

import java.io.*;
import java.net.Socket;

public class PublisherSocket
{
    private Socket socket;
    private PayloadHandler handler = new PayloadHandler();
    private BufferedReader reader;
    private PrintWriter writer;

    PublisherSocket(String ip, int port)
    {
        try
        {
            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        }
        catch (IOException e)
        {
            System.out.println("Can't connect to broker");
            e.printStackTrace();
        }
    }

    public void send(String payload)
    {
        writer.println(payload);
        writer.flush();

            /*String fromServer;

            while ((fromServer = reader.readLine()) != null)
            {
                handler.handle(fromServer);
            }

            writer.close();
            reader.close();
            socket.close();
             */
    }
}