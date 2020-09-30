package com.utm.broker;

import com.utm.common.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConnectionStorage         // se stocheza conexiunile
{


    static List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());

    public static void add(Connection connectionInfo)
    {
        synchronized (connections)
        {
            connections.add(connectionInfo);
        }
    }

//    static void remove(Socket socket)
//    {
//        synchronized (connections)
//        {
//            connections.removeIf(connection -> (connection.socket.getInetAddress() == socket.getInetAddress())
//                    && (connection.socket.getPort() == socket.getPort()));
//        }
//    }
//
//    static void print()
//    {
//        synchronized (connections)
//        {
//            for (Connection connectionInfo : connections)
//            {
//                System.out.println(connectionInfo.socket.getInetAddress() + " " + connectionInfo.socket.getPort() + " "
//                        + connectionInfo.payload.getTopic());
//            }
//        }
//    }

    public static ArrayList<Connection> getConnectionsByTopic(String topic)
    {
        ArrayList<Connection> connectionsByTopic = new ArrayList<>();
        for (Connection connection:connections)
        {
            if(connection.getTopic().equalsIgnoreCase(topic))
            {
                connectionsByTopic.add(connection);
            }
        }
        return connectionsByTopic;
    }
}