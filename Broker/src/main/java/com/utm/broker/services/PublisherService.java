package com.utm.broker.services;

import com.utm.broker.PayloadStorage;
import com.utm.broker.Testament;
import com.utm.broker.TestamentStorage;
import com.utm.common.rcp.publisher.*;
import io.grpc.stub.StreamObserver;

public class PublisherService extends PublisherGrpc.PublisherImplBase
{
    @Override
    public void setLastWillAndTestament(LWTRequest request, StreamObserver<LWTResponse> responseObserver)
    {
        Testament testament = new Testament(request.getPayload(0).getId(), request.getPayload(0).getTopic(),
                request.getPayload(0).getMessage());
        TestamentStorage.add(testament);

        LWTResponse.Builder response = LWTResponse.newBuilder();
        response.setIsSuccess(true);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void publishMessage(PublishRequest request, StreamObserver<PublishResponse> responseObserver)
    {
        Payload payload = request.getPayload(0);
        PayloadStorage.add(payload);

        PublishResponse.Builder response = PublishResponse.newBuilder();
        response.setIsSuccess(true);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void disconnect(DisconnectRequest request, StreamObserver<DisconnectResponse> responseObserver)
    {
        System.out.println("Publisher gracefully shutdown!");

        //TO DO :somehow disconnect publisher
        DisconnectResponse.Builder response = DisconnectResponse.newBuilder();

        response.setIsSuccess(true);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}