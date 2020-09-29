package com.utm.broker;

import common.Payload;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PayloadStorage
{
    public static Queue<Payload> payloads = new ConcurrentLinkedDeque<Payload>();

    public static void add(Payload payload)
    {
        payloads.add(payload);
    }

    public static Payload getNext()
    {
        return payloads.peek();
    }

    public static boolean isEmpty()
    {
        return payloads.isEmpty();
    }

    public static boolean remove(Payload payload)
    {
        return payloads.remove(payload);
    }
    /*
    public static Map<String, Queue<String>> topicsAndMessages =
            Collections.synchronizedMap(new HashMap<String, Queue<String>>());
    private static Queue<String> itemsList = null;

    public static synchronized void addToStorage(Payload payload)
    {
        if(searchIfTopicExists(payload.getTopic()) == 1)
        {
            topicsAndMessages.get(payload.getTopic().toLowerCase()).add(payload.getMessage());
        }
        else {
            itemsList = new LinkedList<String>();
            itemsList.add(payload.getMessage());
            topicsAndMessages.put(payload.getTopic(), itemsList);
        }
    }

    private static synchronized int searchIfTopicExists(String topic)
    {
        for (Map.Entry<String, Queue<String>> entry : topicsAndMessages.entrySet())
        {
            if(entry.getKey().equalsIgnoreCase(topic))
            {
                return 1;
            }
        }
        return 0;
    }

    public static Set<String> getTopics()
    {
        if(topicsAndMessages != null)
        {
            return topicsAndMessages.keySet();
        }
        return null;
    }
     */
}
