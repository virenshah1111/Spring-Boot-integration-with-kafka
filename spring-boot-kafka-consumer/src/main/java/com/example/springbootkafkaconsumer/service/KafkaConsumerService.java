/**
 * 
 */
package com.example.springbootkafkaconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.springbootkafkaconsumer.model.User;

/**
 * @author virens
 *
 */

@Service
public class KafkaConsumerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
	public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
			@Header(KafkaHeaders.OFFSET) int offset) {
		logger.info("Consumed message: {}, partition: {}, offset: {}", message, partition, offset);
	}

	// If we don’t specify the containerFactory attribute it set defaults
	@KafkaListener(topics = "${kafka.topic-json}", groupId = "${kafka.group-json}", containerFactory = "kafkaListenerContainerFactoryUser")
	public void consumeObject(@Payload User user, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
			@Header(KafkaHeaders.OFFSET) int offset) {
		logger.info("Consumed User Object: {}, partition: {}, offset: {}", user, partition, offset);
	}

}
