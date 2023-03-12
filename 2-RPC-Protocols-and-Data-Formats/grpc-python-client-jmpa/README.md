py -m pip install grpcio-tools

py -m pip install --upgrade pip

py -m grpc_tools.protoc -I proto --python_out=. --grpc_python_out=. proto/message.proto

py message_client.py
