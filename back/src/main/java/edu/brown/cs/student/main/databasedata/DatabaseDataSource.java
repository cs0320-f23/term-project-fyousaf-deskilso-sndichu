package edu.brown.cs.student.main.databasedata;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import edu.brown.cs.student.main.notpublic.PrivateDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * Database datasource class.
 */
public class DatabaseDataSource implements Database {
  // TODO: Choose database library and then add reading/writing functionality here

  private static final List<ObjectId> ids = new ArrayList<>();

  /**
   * Method to write to a database.
   *
   * @param rating
   * @param clothing
   * @param timestamp
   */
  @Override
  public void write(Integer rating, List<String> clothing, String timestamp) {
    String uri = PrivateDatabase.getPrivateDatabase().getDatabaseKey();
    try (MongoClient mongoClient = MongoClients.create(uri)) {
      MongoDatabase database = mongoClient.getDatabase("weather_recommendation");
      MongoCollection<Document> collection = database.getCollection("weather_recommendation");
      ObjectId myObjectId = new ObjectId();
      try {
        // Inserts a sample document describing a movie into the collection
        InsertOneResult result = collection.insertOne(new Document()
            .append("_id", myObjectId)
            .append("Rating", rating)
            .append("Clothing", clothing)
            .append("Timestamp", timestamp));
        // Prints the ID of the inserted document
        System.out.println("Success! Inserted document id: " + result.getInsertedId());
        this.ids.add(myObjectId);

        // Prints a message if any exceptions occur during the operation
      } catch (MongoException me) {
        System.err.println("Unable to insert due to an error: " + me);
      }
    }
  }

  /**
   *
   */
  @Override
  public String read(int index) {
    String uri = PrivateDatabase.getPrivateDatabase().getDatabaseKey();
    try (MongoClient mongoClient = MongoClients.create(uri)) {
      MongoDatabase database = mongoClient.getDatabase("weather_recommendation");
      MongoCollection<Document> collection = database.getCollection("weather_recommendation");
      try {
        Document document = collection.find(eq("_id", ids.get(0))).first();
        if (document == null) {
          //Document does not exist
          System.out.println("not exists");
        } else {
          //We found the document
          System.out.println("exists");

        }
        assert document != null;
        return document.getInteger("Rating").toString();
      } catch (MongoException me) {
        System.err.println("Unable to insert due to an error: " + me);
        return null;
      }
    }
  }

  public void deleteAll() {
    String uri = PrivateDatabase.getPrivateDatabase().getDatabaseKey();
    try (MongoClient mongoClient = MongoClients.create(uri)) {
      MongoDatabase database = mongoClient.getDatabase("weather_recommendation");
      MongoCollection<Document> collection = database.getCollection("weather_recommendation");
      collection.deleteMany(new Document());
    }
  }
}
