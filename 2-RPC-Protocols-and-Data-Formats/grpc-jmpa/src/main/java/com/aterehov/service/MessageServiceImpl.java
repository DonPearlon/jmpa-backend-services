package com.aterehov.service;

import com.aterehov.stubs.message.MessageRequest;
import com.aterehov.stubs.message.MessageResponse;
import com.aterehov.stubs.message.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageServiceImpl extends MessageServiceGrpc.MessageServiceImplBase {

    //private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);
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

    private String generateResponseMessage(MessageRequest request) {
        var requestMessage = request.getMessage();
        if ((requestMessage != null) && (requestMessage.equalsIgnoreCase("ping"))) {
            return "Pong";
        }
        return RESPONSE_TO_MESSAGE.formatted(requestMessage);
    }
}
