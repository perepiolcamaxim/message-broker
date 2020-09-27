package com.utm.broker;

import com.utm.common.ConnectionInfo;
import com.utm.common.Payload;
import java.net.Socket;
import java.util.Arrays;

public class PayloadHandler          // se lamureste ce sa faca cu inputul
{
    private Socket clientSocket;

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
        else if(payload.getId() == 1)     // e publisher, vezi ce topic contine si pune mesajul in storage
        {
            PayloadStorage.add(payload);
            return 1;
        }
        else if(payload.getId() == 3)
        {
            //TO DO
            // ia cuvintul cheie si trimite-i stiri ce contin acest cuvint
            return 3;
        }
        else if(payload.getId() == 4)
        {
            String[] topics = payload.getTopic().split("&");
            System.out.println(Arrays.toString(topics));
            for (String topic : topics)
            {
                Payload newPayload = new Payload(0, topic, null);
                System.out.println(newPayload.getTopic());
                ConnectionInfo connectionInfo = new ConnectionInfo(clientSocket, newPayload);
                ConnectionStorage.add(connectionInfo);
            }
            System.out.println("Lista de receiveri:");
            ConnectionStorage.print();
            return 4;
        }
        else
        {
            return -1;
        }
    }
}
