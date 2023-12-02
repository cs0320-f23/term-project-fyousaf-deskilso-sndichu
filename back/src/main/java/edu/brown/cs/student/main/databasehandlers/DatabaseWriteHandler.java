package edu.brown.cs.student.main.databasehandlers;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.notpublic.PrivateDatabase;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
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

      responseMap.put("result", "success");
      return mapAdapter.toJson(responseMap);
    } catch (Exception e) {
      responseMap.put("exception", e);
      return mapAdapter.toJson(responseMap);
    }
  }
}
