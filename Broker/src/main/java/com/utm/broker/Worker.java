package com.utm.broker;

import com.utm.common.rcp.publisher.Payload;
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
                    while(!PayloadStorage.isEmpty())
                    {
                        if(!ConnectionStorage.connections.isEmpty())
                        {
                            Payload payload = PayloadStorage.getNext();
                            if(payload != null)
                            {
                                ArrayList<Connection> connectionsByTopic = ConnectionStorage
                                        .getConnectionsByTopic(payload.getTopic());
                                if(!connectionsByTopic.isEmpty())
                                {
                                    for (Connection connection : connectionsByTopic)
                                    {
                                        // sent messege via channel
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