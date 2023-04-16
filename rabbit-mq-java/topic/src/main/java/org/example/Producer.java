package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.bson.Document;




import mongodb.DAOQuery1;
import mongodb.DAOQuery2;
import mongodb.DAOQuery3;
import mongodb.DAOQuery4;
import mongodb.DAOQuery5;



public class Producer {
	  public static void main(String[] args) throws IOException, TimeoutException {
		  
		  TopicExchange.declareExchange("topic-exchange");
		  String fileName = "src/main/resources/Config.txt"; // Update with the actual file path
	        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	            String line;
                TopicExchange.declareQueues("query1queue");
        	    TopicExchange.declareBindings("query1queue","topic-exchange","query1routingkey");
        	    
        	    TopicExchange.declareQueues("query2queue");
        	    TopicExchange.declareBindings("query2queue","topic-exchange","query2routingkey");
        	    
        	    TopicExchange.declareQueues("query3queue");
        	    TopicExchange.declareBindings("query3queue","topic-exchange","query3routingkey");
        	    
        	    TopicExchange.declareQueues("query4queue");
         	    TopicExchange.declareBindings("query4queue","topic-exchange","query4routingkey");
         	    
         	   TopicExchange.declareQueues("query5queue");
       	       TopicExchange.declareBindings("query5queue","topic-exchange","query5routingkey");
	            
	            
	            while ((line = reader.readLine()) != null) {
	                String[] elements = line.split("\\s+"); // Split the line by whitespace
	                
	                if(elements[0].equals("query1routingkey")) {
	        	         
	                	DAOQuery1 query1 = new DAOQuery1();
	        	         List<Document> queryy1 = query1.getCosts(elements[1], elements[2], elements[3], elements[4], elements[5]);
	        	         String query1message= "{"+ "\"value\":" + queryy1.get(0).get("value").toString()+ "," + "\"year\":" + elements[1] + "," + "\"state\":" +elements[2]+ "," + "\"Type\":" +elements[3]+ "," + "\"Length\":" +elements[4] +"}"; // Do final touch up here
	        	         TopicExchange.publishMessage(query1message,elements[6],elements[0]);
	        	         System.out.println("Query 1 was Published");
	                }
	                
	                else if(elements[0].equals("query2routingkey")) {
	                	
	             	     DAOQuery2 query2 = new DAOQuery2();
	             	     List<String[]> queryy2 = query2.getExpensivestates(elements[1], elements[2], elements[3]);
	             	     String query2message= "{"+ "\"state1\":" + queryy2.get(4)[0]+ "," + "\"state2\":" + queryy2.get(3)[0] + "," +  "\"state3\":" + queryy2.get(2)[0] + "," +  "\"state4\":" + queryy2.get(1)[0] + "," +  "\"state5\":" + queryy2.get(0)[0] +  "," + "\"year\":" + elements[1] + "," + "\"Type\":" +elements[2] +","+ "\"Length\":" +elements[3]+ "}"; 
	             	     TopicExchange.publishMessage(query2message,elements[4],elements[0]);
	             	    System.out.println("Query 2 was Published");
	                }
	                
	                
	                else if(elements[0].equals("query3routingkey")) {
	                	 
	             	     DAOQuery3 query3 = new DAOQuery3();
	             	     List<String[]> queryy3 = query3.getEconomicstates(elements[1], elements[2], elements[3]);
	              	     String query3message= "{"+ "\"state1\":" + queryy3.get(4)[0]+ "," + "\"state2\":" + queryy3.get(3)[0] + "," +  "\"state3\":" + queryy3.get(2)[0] + "," +  "\"state4\":" + queryy3.get(1)[0] + "," +  "\"state5\":" + queryy3.get(0)[0]+   "," + "\"year\":" + elements[1] + "," + "\"Type\":" +elements[2] + "," + "\"Length\":" +elements[3]+  "}";  
	              	     TopicExchange.publishMessage(query3message,elements[4],elements[0]);
	              	     System.out.println("Query 3 was Published"); 
	                }
	                
	                else if(elements[0].equals("query4routingkey")) {
             	        
	                	DAOQuery4 query4 = new DAOQuery4();
	             	    List<String[]> queryy4 = query4.getgrowthstates(elements[1], elements[2], elements[3],elements[4]);
	             	    String query4message= "{"+ "\"state1\":" + queryy4.get(4)[0]+ "," + "\"state2\":" + queryy4.get(3)[0] + "," +  "\"state3\":" + queryy4.get(2)[0] + "," +  "\"state4\":" + queryy4.get(1)[0] + "," +  "\"state5\":" + queryy4.get(0)[0] +  "," + "\"year1\":" + elements[1] + "," + "\"year2\":" +elements[2]+  "}"; 
	             	    TopicExchange.publishMessage(query4message,elements[5],elements[0]);
	             	   System.out.println("Query 4 was Published");
	                }
	                
	                
	                else if(elements[0].equals("query5routingkey")) {
	                	
	                	
	            	    DAOQuery5 query5 = new DAOQuery5();
	            	    List<String[]> queryy5 = query5.getAveragesstates(elements[1], elements[2], elements[3]);
	            	    String query5message= "{"+ "\"Northeast\":" + queryy5.get(0)[1]+ "," + "\"Southeast\":" + queryy5.get(1)[1] + "," +  "\"Midwest\":" + queryy5.get(2)[1] + "," +  "\"southwest\":" + queryy5.get(3)[1] + "," +  "\"west\":" + queryy5.get(4)[1] + "," + "\"year\":" + elements[1] + "," + "\"Type\":" +elements[2] + "\"Length\":" +elements[3] + "}";
	        	        TopicExchange.publishMessage(query5message,elements[4],elements[0]);
	        	        System.out.println("Query 5 was Published");
	                	
	                }
	                
	                
	                
	            }} catch (IOException e) {
	                e.printStackTrace();
	            }
   
	  }
}
