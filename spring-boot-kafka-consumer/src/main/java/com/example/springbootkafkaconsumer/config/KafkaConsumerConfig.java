/**
 * 
 */
package com.example.springbootkafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.springbootkafkaconsumer.model.User;

/**
 * @author virens
 *
 */

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${kafka.group-json}")
	private String kafkaGroup;

	@Value("${kafka.auto-offset-reset}")
	private String offsetResetConfig;

	@Bean
	public ConsumerFactory<String, User> consumerFactoryUser() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroup);
		configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetResetConfig);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
				new JsonDeserializer<>(User.class, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactoryUser() {
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactoryUser());
		factory.setErrorHandler((exception, data) -> {
			exception.printStackTrace();
			logger.error("Error in process with Exception {} and the record is {}", exception, data);
		});
		return factory;
	}

}
