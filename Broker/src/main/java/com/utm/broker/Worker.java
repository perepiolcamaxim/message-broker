package com.utm.broker;

import com.utm.common.rcp.notifier.NotifierGrpc;
import com.utm.common.rcp.notifier.NotifyRequest;
import com.utm.common.rcp.notifier.NotifyResponse;
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

                            ArrayList<Connection> connectionsByTopic = ConnectionStorage
                                    .getConnectionsByTopic(payload.getTopic());

                            if(!connectionsByTopic.isEmpty())
                            {
                                for (Connection connection : connectionsByTopic)
                                {
                                    NotifierGrpc.NotifierBlockingStub stub = NotifierGrpc.newBlockingStub(connection.getChannel());
                                    NotifyRequest request = NotifyRequest.newBuilder().setContent(payload.getMessage()).build();
                                    try
                                    {
                                        NotifyResponse response = stub.notify(request);
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Receiver disconented!");
                                        ConnectionStorage.removeByAddress(connection);
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
                        try
                        {
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}