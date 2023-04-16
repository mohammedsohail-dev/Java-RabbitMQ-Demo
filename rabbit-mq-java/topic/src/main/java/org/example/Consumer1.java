package org.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer1 {
	public static void main(String[] args) throws IOException, TimeoutException {
		  
	    TopicExchange.declareExchange("topic-exchange");
	    
	    TopicExchange.declareQueues("query1queue");
	    TopicExchange.declareBindings("query1queue","topic-exchange","query1routingkey");
	    
	   

	    Thread subscribe = new Thread(() -> {
	      try {
	        TopicExchange.subscribeMessage("query1queue");

	        
	      } catch (IOException | TimeoutException e) {
	        e.printStackTrace();
	      }
	    });
	    subscribe.start();
	    }}
	


