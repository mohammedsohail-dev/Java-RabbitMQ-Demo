package mongodb;


import com.mongodb.ConnectionString;
import com.mongodb.DBCollection;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.logging.Logger;

// make 5 list add get avg and sort
public class DAOQuery5 {
    

    ConnectionString connectionString = new ConnectionString("mongodb+srv://sohail179:URGr3Q!AyR4kyq4@cluster0.ggny9n6.mongodb.net/skiers_resorts?retryWrites=true&w=majority");
MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .applyToSocketSettings(builder -> builder.connectTimeout(60000, TimeUnit.MILLISECONDS).build())
        .build();
MongoClient mongoClient = MongoClients.create(settings);
MongoDatabase database = mongoClient.getDatabase("skiers_resorts");

public List<String[]> getAveragesstates(String Year,String Type,String Length) {
    MongoCollection<Document> collection = database.getCollection("EduCoststat");
    MongoCollection<Document> collection2 = database.getCollection("EduCostStatQueryFive");
    
ArrayList<String> northeastStates = new ArrayList<>();
northeastStates.add("Connecticut");
northeastStates.add("Maine");
northeastStates.add("Maryland");
northeastStates.add("Massachusetts");
northeastStates.add("New Hampshire");
northeastStates.add("New Jersey");
northeastStates.add("New York");
northeastStates.add("Pennsylvania");
northeastStates.add("Rhode Island");
northeastStates.add("Vermont");


ArrayList<String> midwestStates = new ArrayList<>();
midwestStates.add("Illinois");
midwestStates.add("Indiana");
midwestStates.add("Iowa");
midwestStates.add("Kansas");
midwestStates.add("Michigan");
midwestStates.add("Minnesota");
midwestStates.add("Missouri");
midwestStates.add("North Dakota");
midwestStates.add("Ohio");
midwestStates.add("South Dakota");
midwestStates.add("Wisconsin");
midwestStates.add("Nebraska");






ArrayList<String> southeastStates = new ArrayList<>();
southeastStates.add("Alabama");
southeastStates.add("Arkansas");
northeastStates.add("Delaware");
northeastStates.add("District of Columbia");
southeastStates.add("Florida");
southeastStates.add("Georgia");
southeastStates.add("Kentucky");
southeastStates.add("Louisiana");
southeastStates.add("Mississipi");
southeastStates.add("North Carolina");
southeastStates.add("South Carolina");
southeastStates.add("Tennessee");
southeastStates.add("Virginia");
southeastStates.add("West Virginia");









ArrayList<String> southwestStates = new ArrayList<>();
southwestStates.add("Arizona");
southwestStates.add("New Mexico");
southwestStates.add("Oklahoma");
southwestStates.add("Texas");


ArrayList<String> westStates = new ArrayList<>();
westStates.add("Alaska");
westStates.add("California");
westStates.add("Colorado");
westStates.add("Hawaii");
westStates.add("Idaho");
westStates.add("Montana");
westStates.add("Nevada");
westStates.add("Oregon");
westStates.add("Utah");
westStates.add("Washington");
westStates.add("Wyoming");

    
    
    
    List<String[]> listfinal = new ArrayList<>();
    List<String[]> listfinala = new ArrayList<>();
    List<String[]> listfinalb = new ArrayList<>();
    List<String[]> listfinalc = new ArrayList<>();
    List<String[]> listfinald = new ArrayList<>();
    List<String[]> listfinal2 = new ArrayList<>();
    List<String[]> sortedlist = new ArrayList<>();
    



    for(int i=0;i<northeastStates.size();i++){
    Document doc1 = new Document().append("year",Year).append("type",Type).append("length",Length).append("state",northeastStates.get(i));
    Document projection = new Document("value",1).append("_id",0);
    FindIterable<Document> iterable = collection.find(doc1).projection(projection);
    List<Document> users = new ArrayList<>();
    for (Document user : iterable) {
        users.add(user);
    }

    Integer total=0;

    for(int j=0;j<users.size();j++){
       total=Integer.parseInt((users.get(j).get("value")).toString())+total;



    }
    

    String[] row = new String[] {northeastStates.get(i),total.toString()};

    listfinal.add(row);

    
        


    }
    Integer totall=0;

    for(int g=0;g<listfinal.size();g++){
        totall= Integer.parseInt(listfinal.get(g)[1])+totall;
 
    }

    totall=totall/10;
    
    String[] row2 = new String[] {"northeast",totall.toString()};
    listfinal2.add(row2);




        
    

    for(int i=0;i<southeastStates.size();i++){
        Document doc1 = new Document().append("year",Year).append("type",Type).append("length",Length).append("state",southeastStates.get(i));
        Document projection = new Document("value",1).append("_id",0);
        
        FindIterable<Document> iterable = collection.find(doc1).projection(projection);
        List<Document> users = new ArrayList<>();
        for (Document user : iterable) {
            users.add(user);
        }
    
        Integer totala=0;
    
        for(int j=0;j<users.size();j++){
           totala=Integer.parseInt((users.get(j).get("value")).toString())+totala;
    
    
    
        }
    
        String[] row = new String[] {southeastStates.get(i),totala.toString()};
    
        listfinala.add(row);
    
        
            
    
    
        }
        Integer totall2=0;
    
        for(int g=0;g<listfinala.size();g++){
            totall2= Integer.parseInt(listfinala.get(g)[1])+totall2;
    
        }
        totall2=totall2/12;
        String[] row3 = new String[] {"southeast",totall2.toString()};
        listfinal2.add(row3);




        for(int i=0;i<midwestStates.size();i++){
            Document doc1 = new Document().append("year",Year).append("type",Type).append("length",Length).append("state",midwestStates.get(i));
            Document projection = new Document("value",1).append("_id",0);
            FindIterable<Document> iterable = collection.find(doc1).projection(projection);
            List<Document> users = new ArrayList<>();
            for (Document user : iterable) {
                users.add(user);
            }
        
            Integer totalb=0;
        
            for(int j=0;j<users.size();j++){
               totalb=Integer.parseInt((users.get(j).get("value")).toString())+totalb;
        
        
        
            }
        
            String[] row = new String[] {midwestStates.get(i),totalb.toString()};
        
            listfinalb.add(row);
        
            
                
        
        
            }
            Integer totall3=0;
        
            for(int g=0;g<listfinalb.size();g++){
                totall3= Integer.parseInt(listfinalb.get(g)[1])+totall3;
        
            }
            totall3=totall3/14;
            String[] row4 = new String[] {"midwest",totall3.toString()};
            listfinal2.add(row4);



            for(int i=0;i<southwestStates.size();i++){
                Document doc1 = new Document().append("year",Year).append("type",Type).append("length",Length).append("state",southwestStates.get(i));
                Document projection = new Document("value",1).append("_id",0);
                FindIterable<Document> iterable = collection.find(doc1).projection(projection);
                List<Document> users = new ArrayList<>();
                for (Document user : iterable) {
                    users.add(user);
                }
            
                Integer totalc=0;
            
                for(int j=0;j<users.size();j++){
                   totalc=Integer.parseInt((users.get(j).get("value")).toString())+totalc;
            
            
            
                }
            
                String[] row = new String[] {southwestStates.get(i),totalc.toString()};
            
                listfinalc.add(row);
            
                
                    
            
            
                }
                Integer totall4=0;
            
                for(int g=0;g<listfinalc.size();g++){
                    totall4= Integer.parseInt(listfinalc.get(g)[1])+totall4;
            
                }
                totall4 = totall4/4;
                String[] row5 = new String[] {"southwest",totall4.toString()};
                listfinal2.add(row5);

                for(int i=0;i<westStates.size();i++){
                    Document doc1 = new Document().append("year",Year).append("type",Type).append("length",Length).append("state",westStates.get(i));
                    Document projection = new Document("value",1).append("_id",0);
                    FindIterable<Document> iterable = collection.find(doc1).projection(projection);
                    List<Document> users = new ArrayList<>();
                    for (Document user : iterable) {
                        users.add(user);
                    }
                
                    Integer totald=0;
                
                    for(int j=0;j<users.size();j++){
                       totald=Integer.parseInt((users.get(j).get("value")).toString())+totald;
                
                
                
                    }
                
                    String[] row = new String[] {westStates.get(i),totald.toString()};
                
                    listfinald.add(row);
                
                    
                        
                
                
                    }
                    Integer totall5=0;
                
                    for(int g=0;g<listfinald.size();g++){
                        totall5= Integer.parseInt(listfinald.get(g)[1])+totall5;
                
                    }
                    totall5=totall5/11;

                    String[] row6 = new String[] {"west",totall5.toString()};
                    listfinal2.add(row6);
                    
                    Integer flag=0;
                    for(int u=0;u<listfinal2.size();u++) {
                    	
                    	flag=Integer.parseInt(listfinal2.get(u)[1])+flag;
                    	
                    	
                    }
                    
                    if(flag==0) {
                    	
                    	Document doc = new Document().append("year_low",Year).append("type",Type).append("length",Length).append("Northeast", "Not Found").append("Southeast", "Not Found").append("Midwest", "Not Found").append("Southwest", "Not Found").append("West", "Not Found");
                    	if(collection2.find(doc).limit(1).first()==null) {
                    		
                   		 collection2.insertOne(doc);
                   		
                   		
                   		
                   	}
                        
                        for(String[] array : listfinal2) {
                        	array[1]="Not Found";
                        }
                        


                        return listfinal2;
                    	
                    }
                    else {
                    
                    	Document doc = new Document().append("year_low",Year).append("type",Type).append("length",Length).append("Northeast", listfinal2.get(0)[1]).append("Southeast", listfinal2.get(1)[1]).append("Midwest", listfinal2.get(2)[1]).append("Southwest", listfinal2.get(3)[1]).append("West", listfinal2.get(4)[1]);
                    	if(collection2.find(doc).limit(1).first()==null) {
                    		
                   		 collection2.insertOne(doc);
                   		
                   		
                   		
                   	}
                   
                        


                        return listfinal2;

                    }
            
        }

    }


    

