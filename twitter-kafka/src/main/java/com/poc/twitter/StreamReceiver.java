package com.poc.twitter;

public interface StreamReceiver {

	void process(String message);
}
