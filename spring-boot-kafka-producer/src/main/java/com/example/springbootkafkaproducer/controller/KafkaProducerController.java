/**
 * 
 */
package com.example.springbootkafkaproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootkafkaproducer.model.User;
import com.example.springbootkafkaproducer.service.KafkaProducerService;

/**
 * @author virens
 *
 */

@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

	@Autowired
	private KafkaProducerService producerService;

	@GetMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam String message) {
		producerService.sendMessage(message);
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestBody User user) {
		producerService.sendUserObject(user);
	}
}
