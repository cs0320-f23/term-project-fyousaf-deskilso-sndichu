package edu.brown.cs.student.databaseunit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import edu.brown.cs.student.main.databasedata.DatabaseDataSource;
import edu.brown.cs.student.main.databasedata.MockDatabase;
import edu.brown.cs.student.main.databasedata.OutfitLog;
import edu.brown.cs.student.main.databasedata.Status;
import edu.brown.cs.student.main.databasehandlers.DatabaseWriteHandler;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Spark;

/** Unit tests for database class and handler. */
public class DatabaseUnitTest {

  private final Type mapStringObject =
      Types.newParameterizedType(Map.class, String.class, Object.class);
  private JsonAdapter<Map<String, Object>> mapAdapter;

  /** Prepare for tests by getting server port. */
  @BeforeAll
  public static void setupOnce() {
    // Pick an arbitrary free port
    Spark.port(0);
    // Eliminate logger spam in console for test suite
    Logger.getLogger("").setLevel(Level.WARNING); // empty name = root
  }

  /** Establishes endpoint before each test. */
  @BeforeEach
  public void setup() {
    Spark.get("/databasewrite", new DatabaseWriteHandler(new MockDatabase()));
    Spark.awaitInitialization(); // don't continue until the server is listening

    Moshi moshi = new Moshi.Builder().build();
    mapAdapter = moshi.adapter(mapStringObject);
  }

  /** Allows Spark to reset after each test. */
  @AfterEach
  public void tearDown() {
    // Gracefully stop Spark listening on the endpoint
    Spark.unmap("/databasewrite");
    Spark.awaitStop(); // don't proceed until the server is stopped
  }

  /**
   * Shuts down the server so maven does not error with other test classes.
   *
   * @throws InterruptedException for an issue clearing up
   */
  @AfterAll
  public static void shutdown() throws InterruptedException {
    Spark.stop();
    Thread.sleep(3000);
  }

  /**
   * Testing the real filtering of a bounding box with nothing in it.
   *
   * @throws IOException for connection issues
   */
  @Test
  public void testDatabaseMock() throws IOException {
    new DatabaseDataSource().deleteAll();
    // Set up the request, make the request
    HttpURLConnection loadConnection = tryRequest("/databasewrite");
    // Get the expected response: a success
    assertEquals(200, loadConnection.getResponseCode());
    Map<String, Object> body =
        mapAdapter.fromJson(new Buffer().readFrom(loadConnection.getInputStream()));
    assertEquals("success", body.get("result"));
    assertEquals(
        new OutfitLog(List.of("pants, shorts"), Status.INSIDE),
        new MockDatabase().readAllOutfits().get(1));
    loadConnection.disconnect();
    new DatabaseDataSource().deleteAll();
  }

  // citation: the below helper method is directly copied from the lecture code.

  /**
   * Helper to start a connection to a specific API endpoint/params
   *
   * @param apiCall the call string, including endpoint (Note: this would be better if it had more
   *     structure!)
   * @return the connection for the given URL, just after connecting
   * @throws IOException if the connection fails for some reason
   */
  private HttpURLConnection tryRequest(String apiCall) throws IOException {
    // Configure the connection (but don't actually send a request yet)
    URL requestURL = new URL("http://localhost:" + Spark.port() + "/" + apiCall);
    HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();
    // The request body contains a Json object
    clientConnection.setRequestProperty("Content-Type", "application/json");
    // We're expecting a Json object in the response body
    clientConnection.setRequestProperty("Accept", "application/json");

    clientConnection.connect();
    return clientConnection;
  }
}
