package com.utm.publicher;

import com.utm.common.Payload;
import java.io.*;
import java.net.Socket;

public class PublisherSocket
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
