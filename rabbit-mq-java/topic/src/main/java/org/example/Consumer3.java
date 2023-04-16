package org.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer3 {
	public static void main(String[] args) throws IOException, TimeoutException {
		  
	    TopicExchange.declareExchange("topic-exchange");
	    
	   
	    
	    TopicExchange.declareQueues("query3queue");
	    TopicExchange.declareBindings("query3queue","topic-exchange","query3routingkey");
	    


	    Thread subscribe = new Thread(() -> {
	      try {

	        TopicExchange.subscribeMessage("query3queue");

	      } catch (IOException | TimeoutException e) {
	        e.printStackTrace();
	      }
	    });
	    subscribe.start();
	    }}
	



