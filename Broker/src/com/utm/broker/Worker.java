package com.utm.broker;

import com.utm.common.ConnectionInfo;
import com.utm.common.Payload;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;

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
                    while(!PayloadStorage.isEmpty())
                    {
                        if(!ConnectionStorage.connections.isEmpty())
                        {
                            Payload payload = PayloadStorage.getNext();
                            if(payload != null)
                            {
                                ArrayList<ConnectionInfo> clients = ConnectionStorage.getConnectionsByTopic(payload.getTopic());
                                if(!clients.isEmpty())
                                {
                                    for (ConnectionInfo client : clients)
                                    {
                                        try
                                        {
                                            PrintWriter writer = new PrintWriter(client.socket.getOutputStream());
                                            writer.println(payload.getMessage());
                                            writer.flush();
                                        } catch (IOException e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                    try
                                    {
                                        Thread.sleep(2000);
                                    }
                                    catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                    PayloadStorage.remove(payload);
                                }
                            }
                        }
                    }
                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
