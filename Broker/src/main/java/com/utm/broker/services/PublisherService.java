package com.utm.broker.services;

import com.utm.broker.PayloadHandler;
import com.utm.broker.PayloadStorage;
import com.utm.common.rcp.publisher.Payload;
import com.utm.common.rcp.publisher.PublishRequest;
import com.utm.common.rcp.publisher.PublishResponse;
import com.utm.common.rcp.publisher.PublisherGrpc;
import io.grpc.stub.StreamObserver;

public class PublisherService extends PublisherGrpc.PublisherImplBase
{
    private final PayloadHandler handler = new PayloadHandler();;
    public PublisherService()
    {
       //PayloadStorageService payloads = new ConcurrentLinkedDeque<Payload>();
    }

    @Override
    public void publishMessage(PublishRequest request, StreamObserver<PublishResponse> responseObserver)
    {
        PublishResponse.Builder response = PublishResponse.newBuilder();
        Payload payload = request.getPayload(0);
        PayloadStorage.add(payload);
        PayloadStorage.print();
        
        //handler.handle(clientStub, payload);
        response.setIsSuccess(true);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}