package edu.brown.cs.student.main;

import static spark.Spark.after;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import spark.Spark;

/**
 * The class representing the server.
 */
public class Server {

  // spark port for the server
  static final int port = 3232;

  /**
   * Constructor for the server, starting it with Spark Java.
   */
  public Server() {
    // Set up our SparkJava server:
    Spark.port(port);
    after(
        (request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Methods", "*");
        });

    // listen on our endpoints

    // moshi building
    Moshi moshi = new Moshi.Builder().build();
    Type mapStringObject = Types.newParameterizedType(Map.class, String.class, Object.class);
    JsonAdapter<Map<String, Object>> mapAdapter = moshi.adapter(mapStringObject);
    Map<String, Object> responseMap = new HashMap<>();

    // error handling for no endpoint provided or incorrect endpoint
    Spark.get(
        "/",
        (req, res) -> {
          responseMap.put("result", "failure");
          responseMap.put("endpoint must be", "one of our endpoints");
          responseMap.put("issue", "no endpoint provided");

          return mapAdapter.toJson(responseMap);
        });
    Spark.get(
        "*",
        (req, res) -> {
          responseMap.put("result", "failure");
          responseMap.put("endpoint must be", "one of our endpoints");
          responseMap.put("issue", "improper endpoint");

          return mapAdapter.toJson(responseMap);
        });

    // Wait until the server has started.
    Spark.awaitInitialization();
  }

  /**
   * Main method that starts the server.
   *
   * @param args command line arguments. Not used in this server.
   */
  public static void main(String[] args) {
    Server server =
        new Server();
  }
}
