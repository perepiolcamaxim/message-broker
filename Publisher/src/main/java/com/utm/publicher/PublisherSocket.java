package com.utm.publicher;

import com.utm.common.ConnectionSetting;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class PublisherSocket
{
    private Socket socket;
    private PayloadHandler handler = new PayloadHandler();

    public void connect(String ip, int port)
    {
        try
        {
            socket = new Socket(ip, port);
        }
        catch (ConnectException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("Can't connect to broker");
            e.printStackTrace();
        }
    }

    public void send(String payload)
    {
        connect(ConnectionSetting.IP, ConnectionSetting.PORT);
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(payload);
            writer.flush();

            String fromServer;

            while ((fromServer = reader.readLine()) != null)
            {
                handler.handle(fromServer);
            }

            writer.close();
            reader.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}