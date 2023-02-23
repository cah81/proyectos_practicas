package com.mycompany.myappconsumer.consumer;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.topic}")
    public ResponseEntity<String> listen(Message<String>msg) {
        System.out.println(msg.getPayload());
        return ResponseEntity.ok("Message received");
    }

}
