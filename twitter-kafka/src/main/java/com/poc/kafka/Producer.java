package com.poc.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource({ "classpath:/kafka.properties" })
@Component
public class Producer {

	String topicName;
	org.apache.kafka.clients.producer.Producer<String, String> producer;

	@Autowired
	public Producer(Environment env) {
		configure(env);
	}

	void configure(Environment env) {

		// create instance for properties to access producer configs
		Properties props = new Properties();

		// set Topic name
		topicName = env.getProperty("topic.name");

		// Assign localhost id
		props.put("bootstrap.servers", env.getProperty("bootstrap.servers"));

		// Set acknowledgements for producer requests.
		props.put("acks", env.getProperty("acks"));

		// If the request fails, the producer can automatically retry,
		props.put("retries", env.getProperty("retries"));

		// Specify buffer size in config
		props.put("batch.size", env.getProperty("batch.size"));

		// Reduce the no of requests less than 0
		props.put("linger.ms", env.getProperty("linger.ms"));

		// The buffer.memory controls the total amount of memory available to
		// the producer for buffering.
		props.put("buffer.memory", env.getProperty("buffer.memory"));

		props.put("key.serializer", env.getProperty("key.serializer"));

		props.put("value.serializer", env.getProperty("value.serializer"));

		producer = new KafkaProducer<String, String>(props);
	}

	public void sendMessage(String message) {
		producer.send(new ProducerRecord<String, String>(topicName, message));
		System.out.println("Message sent successfully");
		producer.flush();
	}

	@Override
	protected void finalize() throws Throwable {
		producer.close();
		super.finalize();
	}
}
