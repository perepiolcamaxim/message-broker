package com.utm.broker;

import com.utm.common.rcp.publisher.Payload;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PayloadStorage {
    public static Queue<Payload> getPayloads() {
        return payloads;
    }

    private static Queue<Payload> payloads = new ConcurrentLinkedDeque<Payload>();

    public static void add(Payload payload) {
        payloads.add(payload);
    }

    public static Payload getNext() {
        return payloads.peek();
    }

    public static boolean isEmpty() {
        return payloads.isEmpty();
    }

    public static void remove(Payload payload) {
        payloads.remove(payload);
    }
}
