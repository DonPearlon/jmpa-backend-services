package com.aterehov.client;

import com.aterehov.stubs.message.MessageRequest;
import com.aterehov.stubs.message.MessageResponse;
import com.aterehov.stubs.message.MessageServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class GrpcMessageClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcMessageClient.class);
    private static final int SERVER_PORT = 8080;
    private static final String SERVER_HOST = "localhost";
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

    private static ManagedChannel createManagedChannel() {
        return ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();
    }

    private static void shutdownChannel(ManagedChannel channel) {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            LOGGER.error("Failed to shutdown channel: {}", exception.getMessage());
        }
    }
}
