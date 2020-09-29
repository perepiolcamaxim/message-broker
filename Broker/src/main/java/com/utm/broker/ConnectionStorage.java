package com.utm.broker;

import common.ConnectionInfo;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionStorage         // se stocheza conexiunile
{
    static List<ConnectionInfo> connections = Collections.synchronizedList(new ArrayList<ConnectionInfo>());

    static void add(ConnectionInfo connectionInfo)
    {
        synchronized (connections)
        {
            connections.add(connectionInfo);
        }
    }

    static void remove(Socket socket)
    {
        synchronized (connections)
        {
            connections.removeIf(connection -> (connection.socket.getInetAddress() == socket.getInetAddress())
                    && (connection.socket.getPort() == socket.getPort()));
        }
    }

    static void print()
    {
        synchronized (connections)
        {
            for (ConnectionInfo connectionInfo : connections)
            {
                System.out.println(connectionInfo.socket.getInetAddress() + " " + connectionInfo.socket.getPort() + " "
                        + connectionInfo.payload.getTopic());
            }
        }
    }

    public static ArrayList<ConnectionInfo> getConnectionsByTopic(String topic)
    {
        ArrayList<ConnectionInfo> connectionsByTopic = new ArrayList<>();
        for (ConnectionInfo connection:connections)
        {
            if(connection.payload.getTopic().equalsIgnoreCase(topic))
            {
                connectionsByTopic.add(connection);
            }
        }
        return connectionsByTopic;
    }
}