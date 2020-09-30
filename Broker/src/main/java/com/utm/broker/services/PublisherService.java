package com.utm.broker.services;


import com.utm.common.rcp.publisher.PublishRequest;
import com.utm.common.rcp.publisher.PublishResponse;
import com.utm.common.rcp.publisher.PublisherGrpc;
import io.grpc.stub.StreamObserver;

public class PublisherService extends PublisherGrpc.PublisherImplBase {


    @Override
    public void publishMessage(PublishRequest request, StreamObserver<PublishResponse> responseObserver) {
        PublishResponse.Builder response = PublishResponse.newBuilder();
        System.out.println("Received: " + request.getId() + request.getTopic() + request.getMessage());
        response.setIsSuccess(true);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}