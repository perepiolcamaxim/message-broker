package com.utm.broker;

public class Testament
{
    private int id;
    private String topic;
    private String message;

    public Testament(int id, String topic, String message)
    {
        this.id = id;
        this.topic = topic;
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
