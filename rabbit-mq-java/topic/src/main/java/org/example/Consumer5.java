package org.example;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer5 {
	public static void main(String[] args) throws IOException, TimeoutException {
		  
	    TopicExchange.declareExchange("topic-exchange");
	    
	    
	    
	    TopicExchange.declareQueues("query5queue");
	    TopicExchange.declareBindings("query5queue","topic-exchange","query5routingkey");


	    Thread subscribe = new Thread(() -> {
	      try {

	        TopicExchange.subscribeMessage("query5queue");
	        
	      } catch (IOException | TimeoutException e) {
	        e.printStackTrace();
	      }
	    });
	    subscribe.start();
	    }}
	


