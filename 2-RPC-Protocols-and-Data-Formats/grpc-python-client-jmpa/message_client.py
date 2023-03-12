import message_pb2_grpc
import message_pb2
import grpc

def run():
    with grpc.insecure_channel('localhost:8080') as channel:
        stub = message_pb2_grpc.MessageServiceStub(channel)
        message_request = message_pb2.MessageRequest(message = "Ping")
        for i in range(1000):
            print("Request: " + message_request.message)
            message_response = stub.process(message_request)
            print("Response: " + message_response.message)

if __name__ == "__main__":
    run()
