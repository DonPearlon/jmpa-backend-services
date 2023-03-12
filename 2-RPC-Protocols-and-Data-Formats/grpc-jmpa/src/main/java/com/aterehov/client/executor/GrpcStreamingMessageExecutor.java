package com.aterehov.client.executor;

import com.aterehov.stubs.message.MessageRequest;
import com.aterehov.stubs.message.MessageResponse;
import com.aterehov.stubs.message.MessageServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GrpcStreamingMessageExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcStreamingMessageExecutor.class);

    private static final int THREAD_SLEEP_MILLIS = 20;
    private static final int AWAIT_TIMEOUT_MINUTES = 1;

    private final MessageServiceGrpc.MessageServiceBlockingStub blockingStub;
    private final MessageServiceGrpc.MessageServiceStub nonBlockingStub;

    public GrpcStreamingMessageExecutor(MessageServiceGrpc.MessageServiceBlockingStub blockingStub, MessageServiceGrpc.MessageServiceStub nonBlockingStub) {
        this.blockingStub = blockingStub;
        this.nonBlockingStub = nonBlockingStub;
    }

    public void executeServerSideStreaming(MessageRequest messageRequest) {
        LOGGER.info("########## Server Side Streaming of Messages ##########");
        Iterator<MessageResponse> messageResponseIterator;
        try {
            LOGGER.info("Request: {}", messageRequest.getMessage());
            messageResponseIterator = blockingStub.processServerSideStreaming(messageRequest);
            while (messageResponseIterator.hasNext()) {
                MessageResponse messageResponse = messageResponseIterator.next();
                LOGGER.info("Response: {}", messageResponse.getMessage());
            }
        } catch (RuntimeException exception) {
            LOGGER.info("RPC failed: {}", exception.getMessage());
        }
    }

    public void executeClientSideStreaming(List<MessageRequest> messages) throws InterruptedException {
        LOGGER.info("########## Client Side Streaming of Messages ##########");
        final var finishLatch = new CountDownLatch(1);
        StreamObserver<MessageResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(MessageResponse messageResponse) {
                LOGGER.info("Response: {}", messageResponse.getMessage());
            }

            @Override
            public void onCompleted() {
                LOGGER.info("Finished Client Side Streaming of Messages");
                finishLatch.countDown();
            }

            @Override
            public void onError(Throwable throwable) {
                LOGGER.error("Client Side Streaming of Messages Failed: {}", throwable.getMessage());
                finishLatch.countDown();
            }
        };

        StreamObserver<MessageRequest> requestObserver = nonBlockingStub.processClientSideStreaming(responseObserver);
        try {

            for (MessageRequest messageRequest : messages) {
                LOGGER.info("Request: {}", messageRequest.getMessage());
                requestObserver.onNext(messageRequest);
                if (finishLatch.getCount() == 0) {
                    return;
                }
            }
        } catch (RuntimeException exception) {
            requestObserver.onError(exception);
            throw exception;
        }
        requestObserver.onCompleted();
        if (!finishLatch.await(AWAIT_TIMEOUT_MINUTES, TimeUnit.MINUTES)) {
            LOGGER.warn("Client Side Streaming of Messages can not finish within 1 minute");
        }
    }

    public void executeBidirectionalStreaming(List<MessageRequest> messages) throws InterruptedException {

        LOGGER.info("########## Bidirectional Streaming of Messages ##########");
        final var finishLatch = new CountDownLatch(1);
        StreamObserver<MessageResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(MessageResponse messageResponse) {
                LOGGER.info("Response: {}", messageResponse.getMessage());
            }

            @Override
            public void onCompleted() {
                LOGGER.info("Finished Bidirectional Streaming of Messages");
                finishLatch.countDown();
            }

            @Override
            public void onError(Throwable throwable) {
                LOGGER.error("Bidirectional Streaming of Messages Failed: {}", throwable.getMessage());
                finishLatch.countDown();
            }
        };
        StreamObserver<MessageRequest> requestObserver = nonBlockingStub.processBidirectionalStreaming(responseObserver);
        try {
            for (MessageRequest messageRequest : messages) {
                LOGGER.info("Request: {}", messageRequest.getMessage());
                requestObserver.onNext(messageRequest);
                Thread.sleep(THREAD_SLEEP_MILLIS);
                if (finishLatch.getCount() == 0) {
                    return;
                }
            }
        } catch (RuntimeException exception) {
            requestObserver.onError(exception);
            throw exception;
        }
        requestObserver.onCompleted();

        if (!finishLatch.await(AWAIT_TIMEOUT_MINUTES, TimeUnit.MINUTES)) {
            LOGGER.warn("Bidirectional Streaming of Messages can not finish within 1 minute");
        }

    }

}
