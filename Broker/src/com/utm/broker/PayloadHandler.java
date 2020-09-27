package com.utm.broker;

import com.utm.common.ConnectionInfo;
import com.utm.common.Payload;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class PayloadHandler          // se lamureste ce sa faca cu inputul
{
    private Socket clientSocket;

    public int handle(Socket clientSocket, Payload payload)
    {
        this.clientSocket = clientSocket;

        if(payload.getId() == 0)       // e publisher, vezi ce topic contine si pune mesajul in storage
        {
            PayloadStorage.add(payload);
            return 1;
        }
        else if(payload.getId() == 1)      // receiverul poate se aboneaza la mai multe topicuri, delimitate cu "&"
        {
            String[] topics = payload.getTopic().split("&");

            for (String topic : topics)
            {
                Payload newPayload = new Payload(1, topic, null);

                ConnectionInfo connectionInfo = new ConnectionInfo(clientSocket, newPayload);
                ConnectionStorage.add(connectionInfo);
            }
            System.out.println("Lista de receiveri:");
            ConnectionStorage.print();
            return 1;
        }
        else if(payload.getId() == 2)// ia cuvintul cheie si trimite-i stiri ce contin acest cuvint
        {
            PrintWriter writer = null;
            for(Payload payload1 : PayloadStorage.payloads)
            {
                if(payload1.getMessage().toLowerCase().contains(payload.getTopic().toLowerCase()))
                {
                    try
                    {
                        writer = new PrintWriter(clientSocket.getOutputStream());
                        writer.println(payload1.getTopic() + "| " + payload1.getMessage());
                        writer.flush();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            return 2;
        }
        else
        {
            return -1;
        }
    }
}
