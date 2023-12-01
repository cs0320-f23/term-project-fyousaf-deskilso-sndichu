package edu.brown.cs.student.main.recommendationalg;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

/** Handler for the endpoint of our server to generate recommendations. */
public class RecommendationHandler implements Route {

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
      // TODO: code for our algorithm here
      // refer to our spec doc for specifics

      responseMap.put("result", "success");
      return mapAdapter.toJson(responseMap);
    } catch (Exception e) {
      responseMap.put("exception", e);
      return mapAdapter.toJson(responseMap);
    }
  }
}
