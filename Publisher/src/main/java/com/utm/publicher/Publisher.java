package com.utm.publicher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;

public class Publisher
{
    public static void main(String[] args)
    {
        System.out.println("com.utm.publisher.Publisher...");

        PublisherSocket publisherSocket = new PublisherSocket();

        Payload payload = new Payload(1, "sport", "Hello");
        Gson gson = new GsonBuilder().create();

        String payloadInJsonString = gson.toJson(payload);

        publisherSocket.send(payloadInJsonString);
    }
}