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
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DetailsDAO {

    ConnectionString connectionString = new ConnectionString("mongodb+srv://sohail179:URGr3Q!AyR4kyq4@cluster0.ggny9n6.mongodb.net/skiers_resorts?retryWrites=true&w=majority");
MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .applyToSocketSettings(builder -> builder.connectTimeout(60000, TimeUnit.MILLISECONDS).build())
        .build();
MongoClient mongoClient = MongoClients.create(settings);
MongoDatabase database = mongoClient.getDatabase("skiers_resorts");


    public void insertCollection(String Year,String State,String Type,String Length,String Expense,String Value) {
		
		MongoCollection<Document> collection = database.getCollection("EduCoststat");

        
		Document doc1 = new Document().append("year",Year).append("state",State).append("type",Type).append("length",Length).append("expense",Expense).append("value",Value);
		collection.insertOne(doc1);

		
		
		
	}

    public void insertAll(){

    MongoCollection<Document> collection = database.getCollection("EduCoststat");

    String csvFile="src\\main\\java\\mongodb\\nces330_20.csv";
        String line="";
        String csvDelimiter=",";
        List<String[]> data = new ArrayList<String[]>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] row = line.split(csvDelimiter);
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Document> accounts =  new ArrayList<>();

        for(int i=0 ;i < (data.size()-1); i++ ){

        String Year = data.get(i+1)[0];
        String State = data.get(i+1)[1];
        String Type = data.get(i+1)[2];
        String Length = data.get(i+1)[3];
        String Expense = data.get(i+1)[4];
        String Value = data.get(i+1)[5];

        Document doc1 = new Document().append("year",Year).append("state",State).append("type",Type).append("length",Length).append("expense",Expense).append("value",Value);


        

        accounts.add(doc1);



        }
    collection.insertMany(accounts);
    }
    
}
