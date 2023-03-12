package com.aterehov.client;

import com.aterehov.stubs.message.MessageRequest;
import com.aterehov.stubs.message.MessageResponse;
import com.aterehov.stubs.message.MessageServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcMessageClient extends  GrpcClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcMessageClient.class);

    private static final int NUMBER_OF_ITERATIONS = 1000;

    public static void main(String[] args) {
        var channel = createManagedChannel();
        MessageServiceGrpc.MessageServiceBlockingStub stub = MessageServiceGrpc.newBlockingStub(channel);
        var messageRequest = MessageRequest.newBuilder()
                .setMessage("Ping")
                .build();
        int iteration = 0;
        MessageResponse messageResponse;
        while (iteration <= NUMBER_OF_ITERATIONS) {
            LOGGER.info("Request: {}", messageRequest.getMessage());
            messageResponse = stub.process(messageRequest);
            LOGGER.info("Response: {}", messageResponse.getMessage());
            iteration++;
        }
        shutdownChannel(channel);
    }
}
