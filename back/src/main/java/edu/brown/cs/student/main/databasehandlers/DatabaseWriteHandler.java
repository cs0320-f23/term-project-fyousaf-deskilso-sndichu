package edu.brown.cs.student.main.databasehandlers;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.MongoDatabase;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.notpublic.PrivateDatabase;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.bson.conversions.Bson;
import spark.Request;
import spark.Response;
import spark.Route;

/** */
public class DatabaseWriteHandler implements Route {

  /**
   * Invoked when a request is made on this route's corresponding path e.g. '/hello'
   *
   * @param request The request object providing information about the HTTP request
   * @param response The response object providing functionality for modifying the response
   * @return The content to be set in the response
   */
  @Override
  public Object handle(Request request, Response response) {
    Moshi moshi = new Moshi.Builder().build();
    Type mapStringObject = Types.newParameterizedType(Map.class, String.class, Object.class);
    JsonAdapter<Map<String, Object>> mapAdapter = moshi.adapter(mapStringObject);
    Map<String, Object> responseMap = new HashMap<>();

    try {
      // TODO: code for appropriate writing to database here
      // database docs:
      // mongodb basics connection:
      // https://www.mongodb.com/docs/drivers/java/sync/v4.3/fundamentals/connection/connect/#std-label-connect-to-mongodb
      // firebase connection basics: https://firebase.google.com/docs/admin/setup/

      String databaseKey = PrivateDatabase.getPrivateDatabase().getDatabaseKey();
      MongoClient mongoClient = MongoClients.create(databaseKey);
      MongoDatabase db = mongoClient.getDatabase("weather_recommendation");
      List<Document> documents = new ArrayList<>();
      MongoCollection<Document> coll = db.getCollection("weather_recommendation");

      Document doc1 = new Document("name", "Halley's Comet").append("officialName", "1P/Halley").append("orbitalPeriod", 75).append("radius", 3.4175).append("mass", 2.2e14);
      Document doc2 = new Document("name", "Wild2").append("officialName", "81P/Wild").append("orbitalPeriod", 6.41).append("radius", 1.5534).append("mass", 2.3e13);
      Document doc3 = new Document("name", "Comet Hyakutake").append("officialName", "C/1996 B2").append("orbitalPeriod", 17000).append("radius", 0.77671).append("mass", 8.8e12);
      documents.add(doc1);
      documents.add(doc2);
      documents.add(doc3);
      InsertManyResult result = coll.insertMany(documents);
      result.getInsertedIds().values().forEach(doc -> System.out.println(doc.asObjectId().getValue()));

      /*Bson query = eq("name", "Comet Hyakutake");
      try {
        DeleteResult deleted = coll.deleteOne(query);
        System.out.println("Deleted document count: " + deleted.getDeletedCount());
        result.getInsertedIds().values().forEach(doc -> System.out.println(doc.asObjectId().getValue()));
      } catch (MongoException me) {
        System.err.println("Unable to delete due to an error: " + me);
      }
      */

      responseMap.put("result", "success");
      return mapAdapter.toJson(responseMap);
    } catch (Exception e) {
      responseMap.put("exception", e);
      return mapAdapter.toJson(responseMap);
    }
  }
}
