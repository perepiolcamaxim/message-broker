package com.utm.broker;

import com.utm.broker.services.PublisherService;
import com.utm.common.ConnectionSetting;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer
{
    private Server server;

    public void start()
    {
        System.out.println("Broker...");
        server = ServerBuilder.forPort(ConnectionSetting.PORT).addService(new PublisherService()).build();
        try {
            server.start();
            System.out.println("Server started on " + server.getPort());
        } catch (IOException e) {
            System.out.println("Server didn't start on " + server.getPort());
        }
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            System.out.println("Await Termination!!!");
        }

        while(true)
        {
//            try
//            {
//               // Socket clientSocket = serverSocket.accept();
//
//               // BrokerThread brokerThread = new BrokerThread();  // se creaza thread nou
//                //Thread thread = new Thread(brokerThread);
//
//                thread.start();
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
        }
    }
}