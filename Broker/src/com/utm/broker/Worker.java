package com.utm.broker;

import com.utm.common.ConnectionInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Worker
{
    public void send()
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                while(true)
                {
                    while(!TopicStorage.topicsAndMessages.isEmpty())
                    {
                        while(!ConnectionStorage.connections.isEmpty())
                        {
                            ArrayList<String> messages = TopicStorage.topicsAndMessages.get("sport");
                            ArrayList<ConnectionInfo> clients = ConnectionStorage.getConnectionsByTopic("sport");

                            for (String message : messages)
                            {
                                for (ConnectionInfo connectionInfo : clients)
                                {
                                    try
                                    {
                                        PrintWriter writer = new PrintWriter(connectionInfo.socket.getOutputStream());

                                        writer.println(message);
                                        writer.flush();
                                        Thread.sleep(1000);
                                    } catch (IOException | InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        try
                        {
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException e)
                        {
                            System.out.println("Can't sleep the worker thread");
                            e.printStackTrace();
                        }
                    }
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("Can't sleep the worker thread");
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
