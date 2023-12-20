package edu.brown.cs.student.main.databasehandlers;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.databasedata.Database;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Handler to write to the backend database.
 */
public class DatabaseWriteHandler implements Route {

  private final Database state;

  /**
   * Constructor for handler with dependency injected database.
   *
   * @param toUse the database to use.
   */
  public DatabaseWriteHandler(Database toUse) {
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
      // TODO: code for appropriate writing to database here
      // database docs:
      // mongodb basics connection:
      // https://www.mongodb.com/docs/drivers/java/sync/v4.3/fundamentals/connection/connect/#std-label-connect-to-mongodb
      // firebase connection basics: https://firebase.google.com/docs/admin/setup/

      String ratingTemp = request.queryParams("rating");
      Integer rating = null;
      if (ratingTemp != null) {
        try {
          rating = Integer.parseInt(ratingTemp);
        } catch (Exception e) {
          rating = rating;
        }
      }

      String clothingTemp = request.queryParams("clothing");
      List<String> clothing = List.of();
      if (clothingTemp != null) {
        clothing = List.of(clothingTemp.split("!"));
      }

      String statusTemp = request.queryParams("status");
      String status = null;
      if (statusTemp == null) {
        status = "outside";
      } else {
        if (statusTemp.equals("inside")) {
          status = "inside";
        } else {
          status = "outside";
        }
      }

      this.state.write(rating, clothing, LocalDateTime.now().toString(), status);
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
