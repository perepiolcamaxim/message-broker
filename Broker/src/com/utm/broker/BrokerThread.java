package com.utm.broker;

import com.utm.common.Payload;
import java.io.*;
import java.net.Socket;

public class BrokerThread implements Runnable
{
    private Socket clientSocket;
    private PayloadHandler handler;

    private BufferedReader bufferedReader;
    private ObjectInputStream objectInputStream;

    public BrokerThread(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
        try
        {
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (IOException e)
        {
            System.out.println("Probleme cu sreamurile socketului");
            e.printStackTrace();
        }

        handler = new PayloadHandler();
    }

    @Override
    public void run()
    {
        try
        {
            Payload payload = (Payload) objectInputStream.readObject();
            System.out.println(payload.getId() + " " + payload.getTopic() + " " + payload.getMessage());
            handler.handle(clientSocket, payload);
        }
        catch (IOException | ClassNotFoundException e)
        {
            try
            {
                clientSocket.close();
                System.out.println("Client socket is closed");
            }
            catch (IOException ioException)
            {
                System.out.println("Can't close the client socket");
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
