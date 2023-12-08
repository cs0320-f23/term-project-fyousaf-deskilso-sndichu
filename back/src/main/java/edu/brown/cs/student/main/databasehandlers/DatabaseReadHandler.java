package edu.brown.cs.student.main.databasehandlers;

import com.mongodb.client.result.InsertManyResult;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.databasedata.Database;
import edu.brown.cs.student.main.notpublic.PrivateDatabase;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;


import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseReadHandler implements Route {

  private Database state;

  /**
   *
   *
   * @param toUse
   */

  public DatabaseReadHandler(Database toUse) {
    this.state = toUse;
  }

  /**
   * Invoked when a request is made on this route's corresponding path e.g. '/hello'
   *
   * @param request  The request object providing information about the HTTP request
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
      // TODO: code for appropriate reading from database here
      // database docs:
      // mongodb basics connection:
      // https://www.mongodb.com/docs/drivers/java/sync/v4.3/fundamentals/connection/connect/#std-label-connect-to-mongodb
      // firebase connection basics: https://firebase.google.com/docs/admin/setup/

//      String databaseKey = PrivateDatabase.getPrivateDatabase().getDatabaseKey();
//      MongoClient mongoClient = MongoClients.create(databaseKey);
//      MongoDatabase database = mongoClient.getDatabase("sample_mflix");
//      MongoCollection<Document> collection = database.getCollection("movies");
//      Document doc = collection.find(eq("title", "Back to the Future")).first();
//      if (doc != null) {
//        System.out.println(doc.toJson());
//      } else {
//        System.out.println("No matching documents found.");
//      }

      responseMap.put("result", "success");
      return mapAdapter.toJson(responseMap);
    } catch (Exception e) {
      responseMap.put("exception message", e.getMessage());
      responseMap.put("exception", e);
      responseMap.put("exception string", e.toString());
      return mapAdapter.toJson(responseMap);
    }
  }
}
