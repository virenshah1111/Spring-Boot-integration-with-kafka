/**
 * 
 */
package com.example.springbootkafkaproducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * @author virens
 * 
 *         we can create topics by command-line and programmatically as well.
 */

@Configuration
public class KafkaTopicConfig {

	@Value(value = "${kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Value(value = "${kafka.topic}")
	private String kafkaTopicString;

	@Value(value = "${kafka.topic-json}")
	private String kafkaTopicJson;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic kafkaTopicString() {
		return new NewTopic(kafkaTopicString, 2, (short) 1);
	}

	@Bean
	public NewTopic kafkaTopicJson() {
		return new NewTopic(kafkaTopicJson, 1, (short) 1);
	}

}
