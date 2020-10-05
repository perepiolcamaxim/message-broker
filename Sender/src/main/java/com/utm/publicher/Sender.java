package com.utm.publicher;

import com.utm.common.ConnectionSetting;
import com.utm.common.rcp.publisher.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class Sender
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("com.utm.publisher.Publisher...");

        int id = (int )(Math.random() * 1000 + 1);

        String topic, message;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ConnectionSetting.IP, ConnectionSetting.PORT).
                usePlaintext().build();
        PublisherGrpc.PublisherBlockingStub stub = PublisherGrpc.newBlockingStub(channel);

        System.out.println("Testament\nEnter the topic:");
        topic = scanner.nextLine();
        System.out.println("Enter the message:");
        message = scanner.nextLine();

        Payload payload = Payload.newBuilder().setId(id).setTopic(topic).setMessage(message).build();

        LWTRequest request1 = LWTRequest.newBuilder().addPayload(payload).build();
        stub.setLastWillAndTestament(request1);

        while (true)
        {
            System.out.println("1. Send a message\n2. Disconnect");
            String state = scanner.nextLine();
            if(Integer.parseInt(state) == 1)
            {
                System.out.println("Enter the topic:");
                topic = scanner.nextLine();
                System.out.println("Enter the message:");
                message = scanner.nextLine().toLowerCase();

                payload = Payload.newBuilder().setId(id).setTopic(topic).setMessage(message).build();

                PublishRequest request = PublishRequest.newBuilder().addPayload(payload).build();
                PublishResponse response = stub.publishMessage(request);
            }
            else if(Integer.parseInt(state) == 2)
            {
                DisconnectRequest request = DisconnectRequest.newBuilder().setId(id).build();
                DisconnectResponse response = stub.disconnect(request);
                if(response.getIsSuccess())
                {
                    return;
                }
            }
        }
    }
}