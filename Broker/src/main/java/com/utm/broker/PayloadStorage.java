package com.utm.broker;

import com.utm.common.rcp.publisher.Payload;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PayloadStorage
{
    private static final Queue<Payload> payloads = new ConcurrentLinkedDeque<Payload>();
    private static final Queue<Payload> payloadsForever = new ConcurrentLinkedDeque<Payload>();

    public static void add(Payload payload) {
        payloads.add(payload);
        payloadsForever.add(payload);
    }

    public static Queue<Payload> getPayloads() {
        return payloadsForever;
    }

    public static Payload getNext() {
        return payloads.poll();
    }

    public static boolean isEmpty() {
        return payloads.isEmpty();
    }

    public static void print()
    {
        System.out.println("Payloads:");
        for(Payload payload : payloads)
        {
            System.out.println(payload.getId() + " " + payload.getTopic() + " " + payload.getMessage());
        }
    }
}
