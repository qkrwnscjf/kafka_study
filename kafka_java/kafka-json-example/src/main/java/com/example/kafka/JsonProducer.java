package com.example.kafka;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProducer {
    public static void main(String[] args) {
        String topic = "vaccine-topic";
        String apiKey = "API KEY"; // 인증키
        String apiUrl = "https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=10&serviceKey=" + apiKey;

        // Kafka 설정
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            ObjectMapper mapper = new ObjectMapper();

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println(" API 요청 실패. 응답 코드: " + responseCode);
                return;
            }

            try (InputStream input = conn.getInputStream()) {
                JsonNode root = mapper.readTree(input);
                Iterator<JsonNode> iterator = root.get("data").elements();

                while (iterator.hasNext()) {
                    JsonNode center = iterator.next();
                    String jsonString = center.toString();
                    producer.send(new ProducerRecord<>(topic, null, jsonString));
                    System.out.println("Sent: " + jsonString);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


