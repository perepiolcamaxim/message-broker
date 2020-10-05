package com.utm.broker;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TestamentStorage
{
    public static Queue<Testament> testaments = new ConcurrentLinkedDeque<Testament>();

    public static void add(Testament testament)
    {
        testaments.add(testament);
    }

    public static boolean isEmpty()
    {
        return testaments.isEmpty();
    }

    public static Testament getTestamentById(int id)
    {
        for (Testament testament : testaments)
        {
            if(testament.getId() == id)
                return testament;
        }
        return null;
    }
}
