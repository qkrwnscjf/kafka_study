package com.example.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class JsonConsumer {
    public static void main(String[] args) {
        String topic = "vaccine-topic";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "json-consumer-group");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList(topic));
            System.out.println("Kafka Consumer started. Waiting for messages...");

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received (offset=%d, partition=%d): %s%n",
                            record.offset(), record.partition(), record.value());
                }
            }
        } catch (Exception e) {
            System.err.println("Kafka consumer error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println(" Kafka Consumer closed.");
    }
}

