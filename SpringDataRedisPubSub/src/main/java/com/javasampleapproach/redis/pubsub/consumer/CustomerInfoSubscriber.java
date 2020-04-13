package com.javasampleapproach.redis.pubsub.consumer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class CustomerInfoSubscriber implements MessageListener {

	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out
				.println("Received >> " + message + " " + message.getBody() + ", " + Thread.currentThread().getName());

		String payload = message.toString();
		System.out.println("Data >> " + payload + " type " + message.getBody() + ", " + Thread.currentThread().getName());
		ObjectMapper mapper = new ObjectMapper();

		// Convert JSON to POJO
		try {
			Customers customer = mapper.readValue(message.toString(), Customers.class);
			System.out.println(customer.getFirstName());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(message);
		
		
	}

}
