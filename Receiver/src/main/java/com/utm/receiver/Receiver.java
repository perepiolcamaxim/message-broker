package com.utm.receiver;

import com.utm.common.ConnectionSetting;
import com.utm.common.rcp.subscriber.SubscribeRequest;
import com.utm.common.rcp.subscriber.SubscribeResponse;
import com.utm.common.rcp.subscriber.SubscriberGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
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

        int ID = 1;
        String topic;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ConnectionSetting.IP, ConnectionSetting.PORT)
                .usePlaintext().build();

        SubscriberGrpc.SubscriberBlockingStub stub = SubscriberGrpc.newBlockingStub(channel);


        System.out.println("Enter the topic:");
        topic = scanner.nextLine();

        int port = FreePortFinder.findFreeLocalPort();
        SubscribeRequest request = SubscribeRequest.newBuilder().setAddress(ConnectionSetting.IP
                + ":" + port).setTopic(topic).build();

        SubscribeResponse response = stub.subscribe(request);
        System.out.println("Response : " + response.getIsSuccess());
    }
}