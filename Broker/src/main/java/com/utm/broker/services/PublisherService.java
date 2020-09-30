package com.utm.broker.services;

import com.utm.common.rcp.publisher.Payload;
import com.utm.common.rcp.publisher.PublishRequest;
import com.utm.common.rcp.publisher.PublishResponse;
import com.utm.common.rcp.publisher.PublisherGrpc;
import io.grpc.stub.StreamObserver;

public class PublisherService extends PublisherGrpc.PublisherImplBase {
    @Override
    public void publishMessage(PublishRequest request, StreamObserver<PublishResponse> responseObserver) {
        PublishResponse.Builder response = PublishResponse.newBuilder();
        Payload payload = request.getPayload(0);
        System.out.println("Received: " + payload.getId() + payload.getTopic() + payload.getMessage());
        response.setIsSuccess(true);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}