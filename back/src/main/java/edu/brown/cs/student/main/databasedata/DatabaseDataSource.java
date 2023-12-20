package edu.brown.cs.student.main.databasedata;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import edu.brown.cs.student.main.notpublic.PrivateDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Database datasource class containing methods for interacting with real database.
 */
public class DatabaseDataSource implements Database {
    public DatabaseDataSource(){}

  /**
   * Returns outfit log objects.
   *
   * @return The outfits logged
   */
  public List<OutfitLog> readAllOutfits() {
    String uri = PrivateDatabase.getPrivateDatabase().getDatabaseKey();
    try (MongoClient mongoClient = MongoClients.create(uri)) {
      MongoDatabase database = mongoClient.getDatabase("weather_recommendation");
      MongoCollection<Document> collection = database.getCollection("weather_recommendation");
      try {
        List<OutfitLog> outfits = new ArrayList<>();
        for (ObjectId id : DatabaseDataSource.ids) {
          Document document = collection.find(eq("_id", id)).first();
          assert document != null;
          String tempStatus = document.getString("Status");
          Status status = null;
          if (tempStatus.equals("inside")) {
            status = Status.INSIDE;
          } else if (tempStatus.equals("outside")) {
            status = Status.OUTSIDE;
          }
          outfits.add(
              new OutfitLog(document.getList("Clothing", String.class), status
              ));
        }
        System.out.println(outfits);
        return outfits;
      } catch (MongoException me) {
        System.err.println("Unable to insert due to an error: " + me);
        return null;
      }
    }
  }

  private static final List<ObjectId> ids = new ArrayList<>();

  /**
   * Method to write to a database.
   *
   * @param rating    a rating of conditions relative feeling.
   * @param clothing  a list of pieces of clothing.
   * @param timestamp a timestamp.
   */
  @Override
  public void write(Integer rating, List<String> clothing, String timestamp, String status) {
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
            .append("Timestamp", timestamp)
            .append("Status", status));
        // Prints the ID of the inserted document
        System.out.println("Success! Inserted document id: " + result.getInsertedId());
        DatabaseDataSource.ids.add(myObjectId);

        // Prints a message if any exceptions occur during the operation
      } catch (MongoException me) {
        System.err.println("Unable to insert due to an error: " + me);
      }
    }
  }

  /**
   * Method to read the rating at a given index.
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

  /**
   * Clean up method to remove all entries from the database.
   */
  public void deleteAll() {
    String uri = PrivateDatabase.getPrivateDatabase().getDatabaseKey();
    try (MongoClient mongoClient = MongoClients.create(uri)) {
      MongoDatabase database = mongoClient.getDatabase("weather_recommendation");
      MongoCollection<Document> collection = database.getCollection("weather_recommendation");
      collection.deleteMany(new Document());
    }
  }
}
