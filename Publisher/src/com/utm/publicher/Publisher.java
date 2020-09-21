package com.utm.publicher;

import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;

public class Publisher
{
    public static void main(String[] args)
    {
        System.out.println("com.utm.publicher.Publisher...");

        Payload payload = new Payload(1, "sport", "messi paraseste barcelona");

        PublisherSocket publisherSocket = new PublisherSocket();

        publisherSocket.send(payload);
    }
}