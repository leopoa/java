# SAMPLE DOCKER KAFKA + SPRING BOOT

## DOCKER KAFKA

Build from Source

$> docker build -t spotify/kafka kafka

Run
- Set topics created: my-topic, default-topic
- Set group-id: mymirror

$> docker run -p 2181:2181 -p 9092:9092 \
    --env ADVERTISED_HOST=localhost \
    --env ADVERTISED_PORT=9092 \
    --env CONSUMER_THREADS=1 \
    --env TOPICS=my-topic,default-topic \
    --env ZK_CONNECT=kafka7zookeeper:2181/root/path \
    --env GROUP_ID=mymirror \
    spotify/kafka

## SPRING BOOT

Configuration: application.yml (reference docker environment)
- Set group-id
- Set default topic

Controller
- Send to default topic passing only message
- Send to customize topic passing topic name and message

Sample URLs:
- http://localhost:8080/producer?message=12345
- http://localhost:8080/producer/my-topic?message=12345

## RUN APPLICATION

./gradlew build && java -jar build/libs/spring-boot-kafka-0.0.1-SNAPSHOT.jar
