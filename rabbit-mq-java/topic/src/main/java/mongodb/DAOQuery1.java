
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
import com.mongodb.client.model.FindOneAndUpdateOptions;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.logging.Logger;


public class DAOQuery1 {
    

    ConnectionString connectionString = new ConnectionString("mongodb+srv://sohail179:URGr3Q!AyR4kyq4@cluster0.ggny9n6.mongodb.net/skiers_resorts?retryWrites=true&w=majority");
MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .applyToSocketSettings(builder -> builder.connectTimeout(60000, TimeUnit.MILLISECONDS).build())
        .build();
MongoClient mongoClient = MongoClients.create(settings);
MongoDatabase database = mongoClient.getDatabase("skiers_resorts");

public List<Document> getCosts(String Year, String State,String Type,String Length,String Expense) {
    MongoCollection<Document> collection = database.getCollection("EduCoststat");
    MongoCollection<Document> collection2 = database.getCollection("EduCostStatQueryOne");
    Document doc1 = new Document().append("year",Year).append("state",State).append("type",Type).append("length",Length).append("expense",Expense);
    Document projection = new Document("value",1).append("_id",0);
    FindIterable<Document> iterable = collection.find(doc1).projection(projection);
    List<Document> users = new ArrayList<>();
    for (Document user : iterable) {
        users.add(user.append("year",Year).append("state",State).append("type",Type).append("length",Length).append("expense",Expense));
    }
    
    for(int i=0;i<users.size();i++) {
    	if(collection2.find(users.get(i)).limit(1).first()==null) {
    		
    		collection2.insertOne(users.get(i));
    		
    		
    		
    	}
    }
    
    

        
        


    

    return users;

    
}


   

    
    
}
