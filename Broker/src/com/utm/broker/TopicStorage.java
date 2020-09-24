package com.utm.broker;

import com.utm.common.Payload;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TopicStorage
{
    public static Map<String, ArrayList<String>> topicsAndMessages =
            Collections.synchronizedMap(new HashMap<String, ArrayList<String>>());
    public static ArrayList<String> itemsList = null;

    public static synchronized void addToStorage(Payload payload)
    {
        if(searchIfTopicExists(payload.getTopic()) == 1)
        {
            topicsAndMessages.get(payload.getTopic()).add(payload.getMessage());
        }
        else {
            itemsList = new ArrayList<String>();
            itemsList.add(payload.getMessage());
            topicsAndMessages.put(payload.getTopic(), itemsList);
        }
    }

    private static synchronized int searchIfTopicExists(String topic)
    {
        for (Map.Entry<String, ArrayList<String>> entry : topicsAndMessages.entrySet())
        {
            if(entry.getKey().equalsIgnoreCase(topic))
            {
                return 1;
            }
        }
        return 0;
    }
}
