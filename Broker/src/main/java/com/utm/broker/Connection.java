package com.utm.broker;

public class Connection {
    public String getAddress() {
        return address;
    }

    public String getTopic() {
        return topic;
    }

    public String address;
    public String topic;

    public Connection(String address, String topic) {
        this.address = address;
        this.topic = topic;
    }
}
