package com.aterehov.server;

import com.aterehov.service.MessageServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GrpcMessageServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcMessageServer.class);
    private static final int SERVER_PORT = 8080;
    private Server server;

    public void startServer() {
        try {
            server = ServerBuilder.forPort(SERVER_PORT)
                    .addService(new MessageServiceImpl())
                    .build()
                    .start();
            LOGGER.info("Server started on port {}", SERVER_PORT);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                LOGGER.info("Clean server shutdown in case JVM was shutdown");
                try {
                    GrpcMessageServer.this.stopServer();
                } catch (InterruptedException exception) {
                    LOGGER.error("Server shutdown interrupted: {}", exception.getMessage());
                }
            }));
        } catch (IOException exception) {
            LOGGER.error("Server did not start: {}", exception.getMessage());
        }
    }

    public void stopServer() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var server = new GrpcMessageServer();
        server.startServer();
        server.blockUntilShutdown();

    }
}