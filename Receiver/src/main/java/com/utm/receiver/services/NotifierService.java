package com.utm.receiver.services;

import com.utm.common.rcp.notifier.NotifierGrpc;
import com.utm.common.rcp.notifier.NotifyRequest;
import com.utm.common.rcp.notifier.NotifyResponse;
import com.utm.common.rcp.publisher.PublishResponse;
import io.grpc.stub.StreamObserver;

public class NotifierService extends NotifierGrpc.NotifierImplBase
{
    @Override
    public void notify(NotifyRequest request, StreamObserver<NotifyResponse> responseObserver)
    {
        System.out.println(request.getContent());

        NotifyResponse.Builder response = NotifyResponse.newBuilder();
        response.setIsSuccess(true);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
