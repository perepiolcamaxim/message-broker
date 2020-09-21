package com.utm.common;
import java.net.Socket;

public class ConnectionInfo
{
    public Socket socket;
    public String topic;

    public ConnectionInfo(Socket socket, String topic)
    {
        this.socket = socket;
        this.topic = topic;
    }
}
