package com.aterehov.client;

import com.aterehov.client.executor.GrpcStreamingMessageExecutor;
import com.aterehov.stubs.message.MessageRequest;
import com.aterehov.stubs.message.MessageServiceGrpc;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GrpcStreamingMessageClient extends GrpcClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcStreamingMessageClient.class);
    public static final int NUMBER_OF_MESSAGES = 300;

    public static void main(String[] args) {
        ManagedChannel channel = createManagedChannel();
        try {
            MessageServiceGrpc.MessageServiceBlockingStub blockingStub = MessageServiceGrpc.newBlockingStub(channel);
            MessageServiceGrpc.MessageServiceStub nonBlockingStub = MessageServiceGrpc.newStub(channel);
            List<MessageRequest> messages = createMessages();
            var streamingMessageExecutor = new GrpcStreamingMessageExecutor(blockingStub, nonBlockingStub);
            streamingMessageExecutor.executeServerSideStreaming(createMessageRequest());
            streamingMessageExecutor.executeClientSideStreaming(messages);
            streamingMessageExecutor.executeBidirectionalStreaming(messages);
        } catch (InterruptedException exception) {
            LOGGER.error("Error occurred while message streaming: {}", exception.getMessage());
        }
        finally {
            shutdownChannel(channel);
        }
    }

    private static MessageRequest createMessageRequest() {
        return MessageRequest.newBuilder()
                .setMessage("Ping")
                .build();
    }

    private static List<MessageRequest> createMessages() {
        MessageRequest messageRequest = createMessageRequest();
        List<MessageRequest> messages = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_MESSAGES; i++) {
            messages.add(messageRequest);
        }
        return messages;
    }

}
