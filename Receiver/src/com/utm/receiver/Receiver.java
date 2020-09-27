package com.utm.receiver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utm.common.ConnectionSetting;
import com.utm.common.Payload;
import java.util.Scanner;

public class Receiver
{
    public static void main(String[] args)
    {
        System.out.println("com.utm.receiver.Receiver...");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the topic:");
        String topic = scanner.nextLine();

        Payload payload = new Payload(4, topic, null);

        ReceiveSocket socket = new ReceiveSocket(ConnectionSetting.IP, ConnectionSetting.PORT);

        Gson gson = new GsonBuilder().create();
        String payloadInJsonString = gson.toJson(payload);

        socket.send(payloadInJsonString);
    }
}
