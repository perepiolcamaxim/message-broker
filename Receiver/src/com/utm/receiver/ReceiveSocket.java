package com.utm.receiver;

import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;

import java.io.IOException;
import java.io.ObjectOutputStream;
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

    public void send(Payload payload)
    {
        connect(ConnectionSetting.IP, ConnectionSetting.PORT);
        try
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(payload);
            objectOutputStream.flush();
            objectOutputStream.close();
        }
        catch (IOException e)
        {
            System.out.println("Can't get stream from socket");
            e.printStackTrace();
        }
    }
}
