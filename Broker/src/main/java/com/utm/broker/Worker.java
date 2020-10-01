package com.utm.broker;

import com.utm.common.ConnectionSetting;
import com.utm.common.rcp.notifier.NotifierGrpc;
import com.utm.common.rcp.notifier.NotifyRequest;
import com.utm.common.rcp.notifier.NotifyResponse;
import com.utm.common.rcp.publisher.Payload;
import com.utm.common.rcp.subscriber.SubscribeRequest;
import com.utm.common.rcp.subscriber.SubscribeResponse;
import com.utm.common.rcp.subscriber.SubscriberGrpc;

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
                                        System.out.println("Trying to notify subscriber - " + connection.getAddress());
                                        NotifierGrpc.NotifierBlockingStub stub = NotifierGrpc.newBlockingStub(connection.getChannel());
                                        NotifyRequest request = NotifyRequest.newBuilder().setContent(payload.getMessage()).build();
                                        try
                                        {
                                            NotifyResponse response = stub.notify(request);
                                        }
                                        catch (Exception e)
                                        {
                                            System.out.println("Can't send the message! Receiver closed!");
                                            System.out.println(e);
                                            ConnectionStorage.remove(connection);
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