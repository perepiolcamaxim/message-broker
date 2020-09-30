package com.utm.common.rcp.subscriber;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Subscriber.proto")
public final class SubscriberGrpc {

  private SubscriberGrpc() {}

  public static final String SERVICE_NAME = "com.utm.common.Subscriber";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.utm.common.rcp.subscriber.SubscribeRequest,
      com.utm.common.rcp.subscriber.SubscribeResponse> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribe",
      requestType = com.utm.common.rcp.subscriber.SubscribeRequest.class,
      responseType = com.utm.common.rcp.subscriber.SubscribeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.utm.common.rcp.subscriber.SubscribeRequest,
      com.utm.common.rcp.subscriber.SubscribeResponse> getSubscribeMethod() {
    io.grpc.MethodDescriptor<com.utm.common.rcp.subscriber.SubscribeRequest, com.utm.common.rcp.subscriber.SubscribeResponse> getSubscribeMethod;
    if ((getSubscribeMethod = SubscriberGrpc.getSubscribeMethod) == null) {
      synchronized (SubscriberGrpc.class) {
        if ((getSubscribeMethod = SubscriberGrpc.getSubscribeMethod) == null) {
          SubscriberGrpc.getSubscribeMethod = getSubscribeMethod = 
              io.grpc.MethodDescriptor.<com.utm.common.rcp.subscriber.SubscribeRequest, com.utm.common.rcp.subscriber.SubscribeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.utm.common.Subscriber", "subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.utm.common.rcp.subscriber.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.utm.common.rcp.subscriber.SubscribeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SubscriberMethodDescriptorSupplier("subscribe"))
                  .build();
          }
        }
     }
     return getSubscribeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SubscriberStub newStub(io.grpc.Channel channel) {
    return new SubscriberStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SubscriberBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SubscriberBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SubscriberFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SubscriberFutureStub(channel);
  }

  /**
   */
  public static abstract class SubscriberImplBase implements io.grpc.BindableService {

    /**
     */
    public void subscribe(com.utm.common.rcp.subscriber.SubscribeRequest request,
        io.grpc.stub.StreamObserver<com.utm.common.rcp.subscriber.SubscribeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubscribeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.utm.common.rcp.subscriber.SubscribeRequest,
                com.utm.common.rcp.subscriber.SubscribeResponse>(
                  this, METHODID_SUBSCRIBE)))
          .build();
    }
  }

  /**
   */
  public static final class SubscriberStub extends io.grpc.stub.AbstractStub<SubscriberStub> {
    private SubscriberStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SubscriberStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriberStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SubscriberStub(channel, callOptions);
    }

    /**
     */
    public void subscribe(com.utm.common.rcp.subscriber.SubscribeRequest request,
        io.grpc.stub.StreamObserver<com.utm.common.rcp.subscriber.SubscribeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SubscriberBlockingStub extends io.grpc.stub.AbstractStub<SubscriberBlockingStub> {
    private SubscriberBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SubscriberBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriberBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SubscriberBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.utm.common.rcp.subscriber.SubscribeResponse subscribe(com.utm.common.rcp.subscriber.SubscribeRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubscribeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SubscriberFutureStub extends io.grpc.stub.AbstractStub<SubscriberFutureStub> {
    private SubscriberFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SubscriberFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriberFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SubscriberFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.utm.common.rcp.subscriber.SubscribeResponse> subscribe(
        com.utm.common.rcp.subscriber.SubscribeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBSCRIBE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SubscriberImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SubscriberImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE:
          serviceImpl.subscribe((com.utm.common.rcp.subscriber.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<com.utm.common.rcp.subscriber.SubscribeResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SubscriberBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SubscriberBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.utm.common.rcp.subscriber.SubscriberOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Subscriber");
    }
  }

  private static final class SubscriberFileDescriptorSupplier
      extends SubscriberBaseDescriptorSupplier {
    SubscriberFileDescriptorSupplier() {}
  }

  private static final class SubscriberMethodDescriptorSupplier
      extends SubscriberBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SubscriberMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SubscriberGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SubscriberFileDescriptorSupplier())
              .addMethod(getSubscribeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
