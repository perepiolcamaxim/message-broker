package com.utm.broker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionStorage // se stocheza conexiunile
{
    static final List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());

    public static void add(Connection connectionInfo)
    {
        synchronized (connections)
        {
            connections.add(connectionInfo);
        }
    }

    public static ArrayList<Connection> getConnectionsByTopic(String topic)
    {
        synchronized (connections)
        {
            ArrayList<Connection> connectionsByTopic = new ArrayList<>();
            for (Connection connection : connections)
            {
                if (connection.getTopic().equalsIgnoreCase(topic))
                {
                    connectionsByTopic.add(connection);
                }
            }
            return connectionsByTopic;
        }
    }

    public static void remove(Connection connection)
    {
        synchronized (connections)
        {
            connections.removeIf(connection1 -> connection.getAddress() == connection1.getAddress());
        }
    }

    public static void print()
    {
        synchronized (connections)
        {
            System.out.println("Receivers:");
            for (Connection connection : connections)
            {
                System.out.println(connection.getAddress() + " " + connection.getTopic());
            }
        }
    }
}