package org.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {
	public static void main(String[] args) throws IOException, TimeoutException {
		  
	    TopicExchange.declareExchange("topic-exchange");
	    

	    TopicExchange.declareQueues("query2queue");
	    TopicExchange.declareBindings("query2queue","topic-exchange","query2routingkey");



	    Thread subscribe = new Thread(() -> {
	      try {

	        TopicExchange.subscribeMessage("query2queue");

	        
	      } catch (IOException | TimeoutException e) {
	        e.printStackTrace();
	      }
	    });
	    subscribe.start();
	    }}
	



