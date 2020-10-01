package com.utm.broker;

import com.utm.broker.services.PublisherService;
import com.utm.broker.services.ReceiverService;
import com.utm.common.ConnectionSetting;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Broker
{
    public static void main(String[] args)
    {
        System.out.println("Broker...");
        Worker worker = new Worker();
        worker.send();

        Server server = ServerBuilder.forPort(ConnectionSetting.PORT)
                .addService(new PublisherService())
                .addService(new ReceiverService())
                .build();
        try
        {
            server.start();
            System.out.println("Server started on port: " + server.getPort());
        }
        catch (IOException e)
        {
            System.out.println("Server didn't start on " + server.getPort());
        }
        try
        {
            server.awaitTermination();
        } catch (InterruptedException e) {
            System.out.println("Await Termination!!!");
        }
    }
}