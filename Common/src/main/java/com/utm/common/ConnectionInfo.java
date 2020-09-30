package com.utm.common;

import com.utm.common.rcp.publisher.Payload;

import java.net.Socket;

public class ConnectionInfo {
   // public Stub stub;
    public Payload payload;

    public ConnectionInfo(Payload payload) {
        this.payload = payload;
    }
}
