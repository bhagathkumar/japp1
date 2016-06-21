package com.poc.runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.poc.twitter.TwitterStreamer;

public class MainRunner {

	static ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	
	static void execute(){
		TwitterStreamer twitterStreamer=context.getBean(TwitterStreamer.class);
		try {
			twitterStreamer.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		execute();
	}
	
}
