package com.utm.broker;

import com.utm.common.ConnectionInfo;
import com.utm.common.Payload;

import java.net.Socket;

import static com.utm.broker.TopicStorage.addToStorage;

public class PayloadHandler          // se lamureste ce sa faca cu inputul
{
    private Socket clientSocket;
    TopicStorage topicStorage = new TopicStorage();

    public int handle(Socket clientSocket, Payload payload)
    {
        this.clientSocket = clientSocket;

        if(payload.getId() == 0)  //  e receiver, vezi ce topic vrea si inscre-l in storage
        {

            ConnectionInfo connectionInfo = new ConnectionInfo(clientSocket, payload);
            ConnectionStorage.add(connectionInfo);
            System.out.println("Lista de receiveri:");
            ConnectionStorage.print();
            return 0;
        }
        else      // e publisher, vezi ce topic contine si pune mesajul in storage
        {
            addToStorage(payload);
            return 1;
        }
    }
}
