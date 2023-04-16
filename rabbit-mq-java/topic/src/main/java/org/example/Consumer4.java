package org.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer4 {
	public static void main(String[] args) throws IOException, TimeoutException {
		  
	    TopicExchange.declareExchange("topic-exchange");
	   
	    
	    TopicExchange.declareQueues("query4queue");
	    TopicExchange.declareBindings("query4queue","topic-exchange","query4routingkey");
	    



	    Thread subscribe = new Thread(() -> {
	      try {
	        TopicExchange.subscribeMessage("query4queue");
	    
	        
	      } catch (IOException | TimeoutException e) {
	        e.printStackTrace();
	      }
	    });
	    subscribe.start();
	    }}
	


