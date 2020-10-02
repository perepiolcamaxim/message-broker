package com.utm.broker.services;

import com.utm.broker.Connection;
import com.utm.broker.ConnectionStorage;
import com.utm.broker.PayloadStorage;
import com.utm.common.rcp.publisher.Payload;
import com.utm.common.rcp.subscriber.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;

public class ReceiverService extends SubscriberGrpc.SubscriberImplBase
{
    @Override
    public void subscribe(SubscribeRequest request, StreamObserver<SubscribeResponse> responseObserver)
    {
        String[] tokens = request.getAddress().split(":");
        String ipAdress = tokens[0];
        int port = Integer.parseInt(tokens[1]);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ipAdress, port)
                .usePlaintext().build();

        String[] topics = request.getTopic().split("&");

        for (String topic : topics)
        {
            Connection connection = new Connection(request.getAddress(), topic, channel);
            ConnectionStorage.add(connection); //se salveaza conexiunea in storage
        }

        ConnectionStorage.print();

        SubscribeResponse.Builder response = SubscribeResponse.newBuilder();
        response.setIsSuccess(true);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getNewsByKeyWord(GetNewsRequest request, StreamObserver<GetNewsResponse> responseObserver)
    {
        String news = "";
        int i = 0;

        for(Payload payload:PayloadStorage.getPayloads())
        {
            if(payload.getMessage().contains(request.getTopic()))
            {
                i++;
                news  = news + i + ". " + payload.getTopic() +" : "  + payload.getMessage() + "\n";
            }
        }

        GetNewsResponse.Builder response = GetNewsResponse.newBuilder();
        response.setMessages(news);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}