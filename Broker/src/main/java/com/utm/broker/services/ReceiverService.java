package com.utm.broker.services;

import com.utm.broker.Connection;
import com.utm.broker.ConnectionStorage;
import com.utm.broker.PayloadStorage;
import com.utm.common.ConnectionInfo;
import com.utm.common.rcp.publisher.Payload;
import com.utm.common.rcp.subscriber.*;
import io.grpc.Grpc;
import io.grpc.ServerCall;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;

public class ReceiverService extends SubscriberGrpc.SubscriberImplBase
{
    @Override
    public void subscribe(SubscribeRequest request, StreamObserver<SubscribeResponse> responseObserver)
    {
        String[] topics = request.getTopic().split("&");

        for (String topic : topics){
            Connection connection = new Connection(request.getAddress(), topic);
            ConnectionStorage.add(connection); //se salveaza conexiunea in storage
        }
        System.out.println("Lista de receiveri:");
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
                System.out.println(news);
            }
        }

        GetNewsResponse.Builder response = GetNewsResponse.newBuilder();
        response.setMessages(news);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}