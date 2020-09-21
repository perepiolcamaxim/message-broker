package com.utm.receiver;

import com.utm.common.Payload;

public class Receiver
{
    public static void main(String[] args)
    {
        System.out.println("com.utm.receiver.Receiver...");

        Payload payload = new Payload(0, "sport", null);

        ReceiveSocket socket = new ReceiveSocket();
        socket.send(payload);
    }
}
