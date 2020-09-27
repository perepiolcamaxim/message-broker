package com.utm.receiver;

import com.utm.common.ConnectionSetting;

import java.io.*;
import java.net.Socket;

public class ReceiveSocket
{
    private Socket socket;
    private PayloadHandler handler = new PayloadHandler();
    private BufferedReader reader;
    private PrintWriter writer;

    public void connect(String ip, int port)
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
        connect(ConnectionSetting.IP, ConnectionSetting.PORT);
        try
        {
            writer.println(payload);
            writer.flush();

            String fromServer;
            while ((fromServer = reader.readLine()) != null)
            {
                handler.handle(fromServer);
            }
        }
        catch (IOException e)
        {
            writer.close();
            try
            {
                reader.close();
                socket.close();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
    }
}
