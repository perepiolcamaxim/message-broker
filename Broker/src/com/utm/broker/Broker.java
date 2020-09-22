package com.utm.broker;
import java.io.IOException;

public class Broker
{
    public static void main(String[] args)
    {
        Worker worker = new Worker();
        worker.send();
        System.out.println("Broker...");

        BrokerSocket brokerSocket = new BrokerSocket();
        brokerSocket.start();
    }
}