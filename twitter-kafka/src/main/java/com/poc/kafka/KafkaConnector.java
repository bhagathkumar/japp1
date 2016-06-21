package com.poc.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.twitter.StreamReceiver;

@Component
public class KafkaConnector implements StreamReceiver{

	@Autowired
	Producer producer;
	
	public void process(String message) {
		producer.sendMessage(message);
	}
	
}
