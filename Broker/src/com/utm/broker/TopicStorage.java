package com.utm.broker;

import com.utm.common.Payload;

import java.util.*;

public class TopicStorage
{
    public static Map<String, Queue<String>> topicsAndMessages =
            Collections.synchronizedMap(new HashMap<String, Queue<String>>());
    private static Queue<String> itemsList = null;

    public static synchronized void addToStorage(Payload payload)
    {
        if(searchIfTopicExists(payload.getTopic()) == 1)
        {
            topicsAndMessages.get(payload.getTopic()).add(payload.getMessage());
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

}
