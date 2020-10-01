package com.utm.broker;

import com.utm.common.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionStorage // se stocheza conexiunile
{
    static List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());

    public static void add(Connection connectionInfo)
    {
        synchronized (connections)
        {
            connections.add(connectionInfo);
        }
    }

    public static ArrayList<Connection> getConnectionsByTopic(String topic)
    {
        ArrayList<Connection> connectionsByTopic = new ArrayList<>();
        for (Connection connection : connections)
        {
            if(connection.getTopic().equalsIgnoreCase(topic))
            {
                connectionsByTopic.add(connection);
            }
        }
        return connectionsByTopic;
    }
    public static void print()
    {
        System.out.println("Receivers:");
        for(Connection connection : connections)
        {
            System.out.println(connection.address + " " + connection.topic);
        }
    }
}