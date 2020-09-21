package com.utm.publicher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;

public class Publisher
{
    public static void main(String[] args) {
        System.out.println("com.utm.publisher.Publisher...");

        Payload payload;

        PublisherSocket publisherSocket = new PublisherSocket();
        publisherSocket.connect(ConnectionSetting.IP, ConnectionSetting.PORT);

            payload = new Payload();
            payload.setId(1);
            payload.setMessage("Barcelona");
            payload.setTopic("sport");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String payloadInJsonString = gson.toJson(payload);
            System.out.println(payloadInJsonString);
            publisherSocket.send(payloadInJsonString);


    }
}