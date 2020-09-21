package com.utm.common;

import java.io.Serializable;

public class Payload implements Serializable
{
    private int id;
    private String topic;
    private String message;

    public Payload(int id, String topic, String message)
    {
        this.id = id;
        this.topic = topic;
        this.message = message;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getId()
    {
        return id;
    }

    public String getTopic()
    {
        return topic;
    }

    public String getMessage()
    {
        return message;
    }
}
