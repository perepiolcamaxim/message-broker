package com.utm.broker;
import common.ConnectionSetting;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerSocket
{
    private ServerSocket serverSocket;

    public void start()
    {
        try
        {
            serverSocket = new ServerSocket(ConnectionSetting.PORT);
        }
        catch (IOException e)
        {
            System.out.println("Can't conect to port!");
        }

        while(true)
        {
            try
            {
                Socket clientSocket = serverSocket.accept();

                BrokerThread brokerThread = new BrokerThread(clientSocket);  // se creaza thread nou
                Thread thread = new Thread(brokerThread);

                thread.start();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}