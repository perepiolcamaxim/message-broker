package com.utm.receiver;

import com.utm.common.ConnectionSetting;
import com.utm.common.rcp.subscriber.SubscribeRequest;
import com.utm.common.rcp.subscriber.SubscribeResponse;
import com.utm.common.rcp.subscriber.SubscriberGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


import java.util.Scanner;


public class Receiver {

    public static void main(String[] args) {
        int ID = 1;
        String topic;
        Scanner scanner = new Scanner(System.in);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ConnectionSetting.IP, ConnectionSetting.PORT).usePlaintext().build();

        SubscriberGrpc.SubscriberBlockingStub stub = SubscriberGrpc.newBlockingStub(channel);
        System.out.println("com.utm.receiver.Receiver...");

        while (true) {
            System.out.println("Enter the topic:");
            topic = scanner.nextLine();

            SubscribeRequest request = SubscribeRequest.newBuilder().setAddress(ConnectionSetting.IP + ConnectionSetting.SUBSCRIBER_PORT).setTopic(topic).build();
            SubscribeResponse response = stub.subscribe(request);
            System.out.println("Response : " + response.getIsSuccess());
        }
    }

}
