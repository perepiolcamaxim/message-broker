package com.utm.broker;

import com.utm.common.rcp.publisher.Payload;
import com.utm.common.rcp.publisher.PublishRequest;

import java.io.PrintWriter;


public class PayloadHandler          // se lamureste ce sa faca cu inputul
{
String clientAddress;
    public void handle(String clientAddress, Payload payload)
    {
        this.clientAddress = clientAddress;

        if(payload.getId() == 0)       // e publisher, vezi ce topic contine si pune mesajul in storage
        {
            PayloadStorage.add(payload);
        }
        else if(payload.getId() == 1)      // receiverul poate se aboneaza la mai multe topicuri, delimitate cu "&"
        {
            String[] topics = payload.getTopic().split("&");

            for (String topic : topics)
            {
               // Payload newPayload = new Payload(1, topic, null);

                Connection connection = new Connection(clientAddress,payload.getTopic());
                ConnectionStorage.add(connection);
            }
            System.out.println("Lista de receiveri:");
            //ConnectionStorage.print();
        }
        else if(payload.getId() == 2)// ia cuvintul cheie si trimite-i stiri ce contin acest cuvint
        {
            PrintWriter writer = null;
            for(Payload payload1 : PayloadStorage.getPayloads())
            {
                if(payload1.getMessage().toLowerCase().contains(payload.getTopic().toLowerCase()))
                {
                    try
                    {
                        PublishRequest request = PublishRequest.newBuilder().addPayload(payload).build();
                        //PublishResponse response = clientStub.publishMessage(request);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            assert writer != null;
            writer.println("By receiver!");
            writer.flush();
            writer.close();
//            try
//            {
//                clientStub.;
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
        }
        else
        {
            System.out.println("Invalid id");
//            try
//            {
//                clientSocket.close();
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
        }
    }
}
