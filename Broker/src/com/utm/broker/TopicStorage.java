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
        // if list does not exist create it
        if (topicsAndMessages.isEmpty())
        {
            itemsList = new ArrayList<String>();
            itemsList.add(payload.getMessage());
            topicsAndMessages.put(payload.getTopic(), itemsList);

            for (Map.Entry<String, ArrayList<String>> entry : topicsAndMessages.entrySet())
            {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
        }
        else
            {
            for (Map.Entry<String, ArrayList<String>> entry : topicsAndMessages.entrySet())
            {
                if (payload.getTopic().equalsIgnoreCase(entry.getKey()))
                {
                    itemsList = entry.getValue();
                    itemsList.add(payload.getMessage());

                    topicsAndMessages.put(payload.getTopic(), itemsList);
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }
                else
                    {
                    itemsList = new ArrayList<String>();
                    itemsList.add(payload.getMessage());
                    topicsAndMessages.put(payload.getTopic(), itemsList);
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }
            }
        }
    }
}
