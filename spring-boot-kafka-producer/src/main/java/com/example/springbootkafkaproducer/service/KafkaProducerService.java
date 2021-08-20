/**
 * 
 */
package com.example.springbootkafkaproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.springbootkafkaproducer.model.User;

/**
 * @author virens
 *
 */

@Service
public class KafkaProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplateString;

	@Value("${kafka.topic}")
	private String kafkaTopic;

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplateUser;

	@Value("${kafka.topic-json}")
	private String kafkaTopicJson;

	public void sendMessage(final String message) {

		logger.info("send message: {}", message);
		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplateString.send(kafkaTopic, message);

		future.addCallback((SendResult<String, String> result) -> {
			logger.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
		}, (Throwable ex) -> {
			logger.info("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
		});
	}

	public void sendUserObject(final User user) {
		logger.info("send User Object: {}", user);

		ListenableFuture<SendResult<String, User>> future = this.kafkaTemplateUser.send(kafkaTopicJson, user);

		future.addCallback((SendResult<String, User> result) -> {
			logger.info("Sent User Object=[ {} ] with offset=[ {} ]", user, result.getRecordMetadata().offset());
		}, (Throwable ex) -> {
			logger.info("Unable to send User Object=[ {} ] due to : {}", user, ex.getMessage());
		});

	}

}
