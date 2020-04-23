docker build -t zuul:v1
docker run -d -p 8090:8090 --name zuul --network demo_deposit_kafka-local zuul:v1
