package edu.brown.cs.student.main;

import static spark.Spark.after;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.databasedata.Database;
import edu.brown.cs.student.main.databasedata.DatabaseDataSource;
import edu.brown.cs.student.main.databasehandlers.DatabaseReadHandler;
import edu.brown.cs.student.main.databasehandlers.DatabaseWriteHandler;
import edu.brown.cs.student.main.recommendationalg.Constants;
import edu.brown.cs.student.main.recommendationalg.RecommendationHandler;
import edu.brown.cs.student.main.recommendationalg.RecommendationSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import spark.Spark;

/** The class representing the server. */
public class Server {

  // spark port for the server
  static final int port = 3232;

  /** Constructor for the server, starting it with Spark Java. */
  public Server(Database database) {
    Constants constants = new Constants();
    // Set up our SparkJava server and allow access:
    Spark.port(port);

    after(
        (request, response) -> {
          response.header("Access-Control-Allow-Origin", "*");
          response.header("Access-Control-Allow-Methods", "*");
        });

    DatabaseDataSource mySource = new DatabaseDataSource();
    // System.out.println(mySource.readAllOutfits());
    RecommendationSource myRecommendation =
        new RecommendationSource(constants.compatibilityRules, mySource, mySource.readAllOutfits());

    // listen on our endpoints
    Spark.get("/recommendation", new RecommendationHandler(myRecommendation));
    Spark.get("/databaseread", new DatabaseReadHandler(database));
    Spark.get("/databasewrite", new DatabaseWriteHandler(database));

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
          responseMap.put("endpoint must be", "recommendation, databaseread, or databasewrite");
          responseMap.put("issue", "no endpoint provided");

          return mapAdapter.toJson(responseMap);
        });
    Spark.get(
        "*",
        (req, res) -> {
          responseMap.put("result", "failure");
          responseMap.put("endpoint must be", "recommendation, databaseread, or databasewrite");
          responseMap.put("issue", "improper endpoint");

          return mapAdapter.toJson(responseMap);
        });

    // Wait until the server has started.
    Spark.init();
    Spark.awaitInitialization();
    System.out.println("Project2 started at http://localhost:" + port);
  }

  /**
   * Main method that starts the server.
   *
   * @param args command line arguments. Not used in this server.
   */
  public static void main(String[] args) {
    Server server = new Server(new DatabaseDataSource());
  }
}
