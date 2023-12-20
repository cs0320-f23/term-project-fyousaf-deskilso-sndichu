package edu.brown.cs.student.main.recommendationalg;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;
import spark.Route;

/** Handler for the endpoint of our server to generate recommendations. */
public class RecommendationHandler implements Route {

  public static RecommendationSource getMySource() {
    return mySource;
  }

  /**
   * Invoked when a request is made on this route's corresponding path e.g. '/hello'
   *
   * @param request The request object providing information about the HTTP request
   * @param response The response object providing functionality for modifying the response
   * @return The content to be set in the response
   */
  private static RecommendationSource mySource;

  public RecommendationHandler(RecommendationSource mySource) {
    this.mySource = mySource;
  }

  @Override
  public Object handle(Request request, Response response) {
    // String CurrentWeather = request.queryParams("temp");
    Moshi moshi = new Moshi.Builder().build();
    Type mapStringObject = Types.newParameterizedType(Map.class, String.class, Object.class);
    JsonAdapter<Map<String, Object>> mapAdapter = moshi.adapter(mapStringObject);
    Map<String, Object> responseMap = new HashMap<>();

    try {
      // TODO: code for our algorithm here
      System.out.println(mySource.getOutfitLogs());
      List<List<String>> Recommendation = this.mySource.generateRecommendations();
      responseMap.put("result", "success");
      responseMap.put("recommendation", Recommendation);
      return mapAdapter.toJson(responseMap);
    } catch (CustomException e) {
      e.printStackTrace();
      responseMap.put("type", "error");
      responseMap.put("error_type", "datasource");
      responseMap.put("details", e.getMessage());
      return mapAdapter.toJson(responseMap);
    } catch (Exception e) {
      responseMap.put("type", "error");
      responseMap.put("error_type", "datasource");
      responseMap.put("details", e.getMessage());
      return mapAdapter.toJson(responseMap);
    }
  }
}
