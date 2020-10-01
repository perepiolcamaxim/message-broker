package com.utm.receiver;

import com.utm.common.ConnectionSetting;
import com.utm.common.rcp.subscriber.*;
import com.utm.receiver.services.NotifierService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import me.alexpanov.net.FreePortFinder;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Receiver
{
    public static void main(String[] args)
    {
        System.out.println("com.utm.receiver.Receiver...");
        Scanner scanner = new Scanner(System.in);
        int port = FreePortFinder.findFreeLocalPort();

        Server server = ServerBuilder.forPort(port)
                .addService(new NotifierService())
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

        int ID = 1;
        String topic;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ConnectionSetting.IP, ConnectionSetting.PORT)
                .usePlaintext().build();

        SubscriberGrpc.SubscriberBlockingStub stub = SubscriberGrpc.newBlockingStub(channel);

        System.out.println("Enter the topic:");
        topic = scanner.nextLine();

        //SubscribeRequest request = SubscribeRequest.newBuilder().setAddress(ConnectionSetting.IP+ ":" + port).setTopic(topic).build();
        GetNewsRequest request = GetNewsRequest.newBuilder().setTopic(topic).build();
        //SubscribeResponse response = stub.subscribe(request);
       GetNewsResponse response = stub.getNewsByKeyWord(request);

        System.out.println("Response : \n" + response.getMessages());

        try
        {
            server.awaitTermination();
        } catch (InterruptedException e) {
            System.out.println("Await Termination!!!");
        }
    }
}