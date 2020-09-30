package com.utm.publicher;

import com.utm.common.ConnectionSetting;
import com.utm.common.rcp.publisher.Payload;
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

        Integer id = 0;
        String topic, message;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ConnectionSetting.IP, ConnectionSetting.PORT).usePlaintext().build();
        PublisherGrpc.PublisherBlockingStub stub = PublisherGrpc.newBlockingStub(channel);

        while (true) {
            System.out.println("Enter the topic:");
            topic = scanner.nextLine();
            System.out.println("Enter the message:");
            message = scanner.nextLine();
            Payload payload = Payload.newBuilder().setId(id).setTopic(topic).setMessage(message).build();
            PublishRequest request = PublishRequest.newBuilder().addPayload(payload).build();
            PublishResponse response = stub.publishMessage(request);
            System.out.println("Response : " + response.getIsSuccess());
            // Payload payload = new Payload(id, topic, message);
            //String payloadInJsonString = gson.toJson(payload);
            //publisherSocket.send(payloadInJsonString);
        }
    }
}