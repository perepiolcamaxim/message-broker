package com.utm.broker;

import com.utm.common.ConnectionInfo;
import java.util.ArrayList;
import java.util.List;

public class ConnectionStorage         // se stocheza conexiunile
{
    static List<ConnectionInfo> connections = new ArrayList<ConnectionInfo>();

    static void add(ConnectionInfo connectionInfo)
    {
        connections.add(connectionInfo);
    }

    static void remove(ConnectionInfo connectionInfo)
    {
        connections.remove(connectionInfo);
    }

    static void print()
    {
        for (ConnectionInfo connectionInfo: connections)
        {
            System.out.println(connectionInfo.socket.getInetAddress() + " " + connectionInfo.socket.getPort() + " "
                    + connectionInfo.topic);
        }
    }
}