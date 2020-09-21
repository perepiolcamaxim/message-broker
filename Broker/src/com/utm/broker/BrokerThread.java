package com.utm.broker;

import com.utm.common.Payload;
import java.io.*;
import java.net.Socket;

public class BrokerThread implements Runnable
{
    private Socket clientSocket;
    private PayloadHandler handler;

    private ObjectInputStream objectInputStream;

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
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            Payload payload = (Payload) objectInputStream.readObject();
            handler.handle(clientSocket, payload);
        }
        catch (Exception e)
        {
            System.out.println("Ceva eroare, da pox...");
            e.printStackTrace();
        }
    }
}
