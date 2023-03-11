package com.aterehov.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class GrpcClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcClient.class);
    private static final int SERVER_PORT = 8080;
    private static final String SERVER_HOST = "localhost";

    private static final int AWAIT_TERMINATION_SECONDS = 5;

    protected static ManagedChannel createManagedChannel() {
        return ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();
    }

    protected static void shutdownChannel(ManagedChannel channel) {
        try {
            channel.shutdown().awaitTermination(AWAIT_TERMINATION_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            LOGGER.error("Failed to shutdown channel: {}", exception.getMessage());
        }
    }
}
