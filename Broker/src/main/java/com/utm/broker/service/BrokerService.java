package com.utm.broker.service;

import com.utm.broker.Broker;
import com.utm.broker.rcp.BrokerRequest;
import com.utm.broker.rcp.BrokerResponse;
import com.utm.broker.rcp.BrokerGrpc;
import io.grpc.stub.StreamObserver;

public class BrokerService extends BrokerGrpc.BrokerImplBase {

    @Override
    public void hello(BrokerRequest request, StreamObserver<BrokerResponse> responseObserver) {
        Integer id = request.getId();
        String topic = request.getTopic();
        String message = request.getMessage();
        BrokerResponse.Builder response = BrokerResponse.newBuilder();
        if (!message.isEmpty()) {
            response.setResponseCode("1").setResponseMessage("Success");
        } else {
            response.setResponseCode("0").setResponseMessage("Failure");
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
