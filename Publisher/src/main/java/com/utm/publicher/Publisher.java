package com.utm.publicher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;

import java.util.Scanner;

public class Publisher
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("com.utm.publisher.Publisher...");

        PublisherSocket publisherSocket = new PublisherSocket(ConnectionSetting.IP, ConnectionSetting.PORT);

        int id = 1;
        String topic, message;

        Gson gson = new GsonBuilder().create();

        while(true)
        {
            System.out.println("Enter the topic:");
            topic = scanner.nextLine();
            System.out.println("Enter the message:");
            message = scanner.nextLine();

            Payload payload = new Payload(id, topic, message);
            String payloadInJsonString = gson.toJson(payload);
            publisherSocket.send(payloadInJsonString);
        }
    }
}