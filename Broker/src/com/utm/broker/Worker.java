package com.utm.broker;

import com.utm.common.ConnectionInfo;

import java.io.IOException;
import java.io.PrintWriter;

public class Worker
{
    public void send()
    {
        new Thread(new Runnable() {
            public void run()
            {
                while(true)
                {
                    while (!ConnectionStorage.connections.isEmpty())
                    {
                        for (ConnectionInfo connectionInfo : ConnectionStorage.connections)
                        {
                            try
                            {
                                PrintWriter writer = new PrintWriter(connectionInfo.socket.getOutputStream());

                                //TopicStorage.topicsAndMessages.get(connectionInfo.payload.getTopic()); se extrage lista cu mesaje
                                writer.println("Message from topic!" + connectionInfo.payload.getTopic());
                                writer.flush();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        try
                        {
                            Thread.sleep(3000);
                        }
                        catch (InterruptedException e)
                        {
                            System.out.println("Can't sleep the worker thread");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }
}
