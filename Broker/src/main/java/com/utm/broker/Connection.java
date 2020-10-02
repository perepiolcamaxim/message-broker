package com.utm.broker;

import com.utm.common.ConnectionSetting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Connection
{
    private String address;
    private String topic;
    private ManagedChannel channel;

    public String getAddress() {
        return address;
    }
    public String getTopic() {
        return topic;
    }
    public ManagedChannel getChannel() {return channel;}

    public Connection(String address, String topic, ManagedChannel channel)
    {
        this.address = address;
        this.topic = topic;
        this.channel = channel;
    }
}