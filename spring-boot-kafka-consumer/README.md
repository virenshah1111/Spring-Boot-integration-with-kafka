# Spring Boot with Kafka Consumer Example

This Project covers how to use Spring Boot with Spring Kafka to Consume JSON/String message to a Kafka topic

## Install Kafka in Windows
- Apache Kafka can be downloaded from its official site [kafka.apache.org](https://kafka.apache.org/downloads)
- select the downloaded Binary file and extract the file then move the extracted folder to the directory where you wish to keep the files
- Create two folder inside kafka directory 1. kafka-logs 2. zookeeper-data
- Open zookeeper.properties file which is inside config folder then update path of `dataDir` field to full path of `/zookeeper-data`
- Open server.properties file which is inside same config folder then update path of `log.dirs` field to full path of `/kafka-logs`

## Start Zookeeper
- `.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties`

## Start Kafka Server
- `.\bin\windows\kafka-server-start.bat .\config\server.properties`

## Create Kafka Topic
- `.\bin\kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafka-string-topic`

## Publish to the Kafka Topic via Console
- `.\bin\kafka-console-producer.sh --broker-list localhost:9092 --topic kafka-string-topic`
- `.\bin\kafka-console-producer.sh --broker-list localhost:9092 --topic kafka-json-topic`