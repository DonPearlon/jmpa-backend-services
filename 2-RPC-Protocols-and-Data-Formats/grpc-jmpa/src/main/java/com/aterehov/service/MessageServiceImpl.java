package com.aterehov.service;

import com.aterehov.stubs.message.MessageRequest;
import com.aterehov.stubs.message.MessageResponse;
import com.aterehov.stubs.message.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    public static final int NUMBER_OF_STREAM_ITERATIONS = 5;
    public static final String RESPONSE_TO_MESSAGE = "Response to: %s";

    @Override
    public void process(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        var messageResponse = MessageResponse
                .newBuilder()
                .setMessage(generateResponseMessage(request))
                .build();
        responseObserver.onNext(messageResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void processServerSideStreaming(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        createMessageStreamingResponse(request, responseObserver);
        responseObserver.onCompleted();
    }

    private void createMessageStreamingResponse(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        for (int i = 1; i <= NUMBER_OF_STREAM_ITERATIONS; i++) {

            var messageResponse = MessageResponse
                    .newBuilder()
                    .setMessage(generateResponseMessage(request))
                    .build();
            responseObserver.onNext(messageResponse);
        }
    }

    @Override
    public StreamObserver<MessageRequest> processClientSideStreaming(StreamObserver<MessageResponse> responseObserver) {
        return new StreamObserver<>() {
            final StringBuffer stringBuffer = new StringBuffer();

            @Override
            public void onNext(MessageRequest messageRequest) {
                stringBuffer.append(generateResponseMessage(messageRequest));
                stringBuffer.append("-");
            }

            @Override
            public void onCompleted() {
                var messageResponse = MessageResponse
                        .newBuilder()
                        .setMessage(stringBuffer.toString())
                        .build();
                responseObserver.onNext(messageResponse);
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable throwable) {
                LOGGER.error("Error: {}", throwable.getMessage());
            }
        };
    }

    @Override
    public StreamObserver<MessageRequest> processBidirectionalStreaming(StreamObserver<MessageResponse> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(MessageRequest request) {

                createMessageStreamingResponse(request, responseObserver);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable throwable) {
                LOGGER.error("Error: {}", throwable.getMessage());
            }
        };
    }

    private String generateResponseMessage(MessageRequest request) {
        var requestMessage = request.getMessage();
        if ((requestMessage != null) && (requestMessage.equalsIgnoreCase("ping"))) {
            return "Pong";
        }
        return RESPONSE_TO_MESSAGE.formatted(requestMessage);
    }
}
