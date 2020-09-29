package common;

import common.Payload;

import java.net.Socket;

public class ConnectionInfo
{
    public Socket socket;
    public Payload payload;

    public ConnectionInfo(Socket socket, Payload payload)
    {
        this.socket = socket;
        this.payload = payload;
    }
}
