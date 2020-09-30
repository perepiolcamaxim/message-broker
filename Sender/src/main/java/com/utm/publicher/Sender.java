package com.utm.publicher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;
import com.utm.common.rcp.publisher.PublishRequest;
import com.utm.common.rcp.publisher.PublishResponse;
import com.utm.common.rcp.publisher.PublisherGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class Sender {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("com.utm.publisher.Publisher...");

        //   PublisherSocket publisherSocket = new PublisherSocket(ConnectionSetting.IP, ConnectionSetting.PORT);

        int id = 0;
        String topic, message;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", ConnectionSetting.PORT).usePlaintext().build();
        PublisherGrpc.PublisherBlockingStub stub = PublisherGrpc.newBlockingStub(channel);
        Gson gson = new GsonBuilder().create();

        // while(true)
        // {
        System.out.println("Enter the topic:");
        topic = scanner.nextLine();
        System.out.println("Enter the message:");
        message = scanner.nextLine();
        PublishRequest request = PublishRequest.newBuilder().setId(id).setTopic(topic).setMessage(message).build();
        PublishResponse response = stub.publishMessage(request);
        System.out.println("Response : " + response.getIsSuccess());

        // Payload payload = new Payload(id, topic, message);
        //String payloadInJsonString = gson.toJson(payload);
        //publisherSocket.send(payloadInJsonString);
        // }
    }
}