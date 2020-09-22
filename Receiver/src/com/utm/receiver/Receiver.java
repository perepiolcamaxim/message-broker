package com.utm.receiver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utm.common.Payload;

public class Receiver
{
    public static void main(String[] args)
    {
        System.out.println("com.utm.receiver.Receiver...");

        Payload payload = new Payload(0, "sport", null);

        ReceiveSocket socket = new ReceiveSocket();

        Gson gson = new GsonBuilder().create();
        String payloadInJsonString = gson.toJson(payload);

        socket.send(payloadInJsonString);
    }
}
