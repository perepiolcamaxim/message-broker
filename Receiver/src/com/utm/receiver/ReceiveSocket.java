package com.utm.receiver;

import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;

import java.io.*;
import java.net.Socket;

public class ReceiveSocket
{
    private Socket socket;

    public void connect(String ip, int port)
    {
        try
        {
            socket = new Socket(ip, port);
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
            String fromServer;

            while ((fromServer = reader.readLine()) != null)
            {
                System.out.println(fromServer);

                if (fromServer.equals("Server: Bye"))
                    break;

                writer.println(payload);
                writer.flush();
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
