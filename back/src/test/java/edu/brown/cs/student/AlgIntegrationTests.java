package edu.brown.cs.student;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.brown.cs.student.main.databasedata.DatabaseDataSource;
import edu.brown.cs.student.main.databasedata.OutfitLog;
import edu.brown.cs.student.main.databasedata.Status;
import edu.brown.cs.student.main.recommendationalg.Constants;
import edu.brown.cs.student.main.recommendationalg.CustomException;
import edu.brown.cs.student.main.recommendationalg.RecommendationHandler;
import edu.brown.cs.student.main.recommendationalg.RecommendationSource;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import spark.Spark;

/**
 * Integration tests: send real web requests to our server as it is running. Note that for these, we
 * prefer to avoid sending many real API requests to the NWS, and use "mocking" to avoid it. (There
 * are many other reasons to use mock data here. What are they?)
 *
 * <p>In short, there are two new techniques demonstrated here: integration testing; and testing
 * with mock data / mock objects.
 */
public class AlgIntegrationTests {

    @BeforeAll
    public static void setupOnce() {
        // Pick an arbitrary free port
        Spark.port(0);
        // Eliminate logger spam in console for test suite
        Logger.getLogger("").setLevel(Level.WARNING); // empty name = root
    }

    // Helping Moshi serialize Json responses; see the gearup for more info.
    private final Type mapStringObject =
            Types.newParameterizedType(Map.class, String.class, Object.class);
    private JsonAdapter<Map<String, Object>> adapter;
    private JsonAdapter<List> dataAdapter;
    private RecommendationSource mockedSource;

    @BeforeEach
    public void setup() {
        Constants constants = new Constants();
        OutfitLog log1 =
                new OutfitLog(
                        Arrays.asList("hat", "sunglasses", "t-shirt", "jeans", "belt"), Status.OUTSIDE);
        OutfitLog log2 =
                new OutfitLog(
                        Arrays.asList("dress shirt", "tie", "coat", "umbrella", "belt"), Status.OUTSIDE);
        OutfitLog indoorOutfit1 =
                new OutfitLog(Arrays.asList("sweater", "jeans", "sneakers", "watch"), Status.INSIDE);
        OutfitLog indoorOutfit2 =
                new OutfitLog(Arrays.asList("blouse", "skirt", "flats", "necklace"), Status.INSIDE);
        OutfitLog indoorOutfit3 =
                new OutfitLog(Arrays.asList("cardigan", "leggings", "boots", "bracelet"), Status.INSIDE);
        OutfitLog indoorOutfit4 =
                new OutfitLog(Arrays.asList("turtleneck", "slacks", "loafers", "watch"), Status.INSIDE);
        OutfitLog indoorOutfit5 =
                new OutfitLog(Arrays.asList("blazer", "dress pants", "heels", "earrings"), Status.INSIDE);

        OutfitLog outdoorOutfit1 =
                new OutfitLog(Arrays.asList("jacket", "jeans", "sneakers", "hat", "belt"), Status.OUTSIDE);
        OutfitLog outdoorOutfit2 =
                new OutfitLog(Arrays.asList("raincoat", "pants", "boots", "umbrella"), Status.OUTSIDE);
        OutfitLog outdoorOutfit3 =
                new OutfitLog(Arrays.asList("hoodie", "shorts", "sandals", "sunglasses"), Status.OUTSIDE);

        List<OutfitLog> outfitLogs = new ArrayList<>();
        outfitLogs.add(log1);
        outfitLogs.add(log2);
        outfitLogs.add(indoorOutfit1);
        outfitLogs.add(indoorOutfit2);
        outfitLogs.add(indoorOutfit3);
        outfitLogs.add(indoorOutfit4);
        outfitLogs.add(indoorOutfit5);
        outfitLogs.add(outdoorOutfit1);
        outfitLogs.add(outdoorOutfit2);
        outfitLogs.add(outdoorOutfit3);
        this.mockedSource = new RecommendationSource(constants.compatibilityRules,new DatabaseDataSource(),outfitLogs);
        Spark.get("recommendation", new RecommendationHandler(mockedSource));
        Spark.awaitInitialization(); // don't continue until the server is listening

        // New Moshi adapter for responses (and requests, too; see a few lines below)
        //   For more on this, see the Server gearup.
        Moshi moshi = new Moshi.Builder().build();
        adapter = moshi.adapter(mapStringObject);
        dataAdapter = moshi.adapter(List.class);
    }

    @AfterEach
    public void tearDown() {
        // Gracefully stop Spark listening on both endpoints
        Spark.unmap("/recommendation");
        Spark.awaitStop(); // don't proceed until the server is stopped
    }

  /*
  Recall that the "throws" clause doesn't matter -- JUnit will fail if an
  exception is thrown that hasn't been declared as a parameter to @Test.
   */

    /**
     * Helper to start a connection to a specific API endpoint/params
     *
     * @param apiCall the call string, including endpoint (Note: this would be better if it had more
     *     structure!)
     * @return the connection for the given URL, just after connecting
     * @throws IOException if the connection fails for some reason
     */
    private HttpURLConnection tryRequest(String apiCall) throws IOException {
        try {
            // Configure the connection (but don't actually send a request yet)
            URL requestURL = new URL(apiCall);
            HttpURLConnection clientConnection = (HttpURLConnection) requestURL.openConnection();
            // The request body contains a Json object
            clientConnection.setRequestProperty("Content-Type", "application/json");
            // We're expecting a Json object in the response body
            clientConnection.setRequestProperty("Accept", "application/json");

            clientConnection.connect();
            return clientConnection;
        } catch (IOException e) {
            System.err.println("Error connecting to: " + apiCall);
            e.printStackTrace();
            throw e; // rethrow the exception after logging
        }
    }

    /**Test to ensure a failed request to the API does not crash the program, but returns an informative message
     *
     * @throws IOException
     */
    @Test
    public void testAPIRequestFail() throws IOException {
        /////////// LOAD DATASOURCE ///////////
        // Set up the request, make the request
        HttpURLConnection loadConnection =
                tryRequest("http://localhost:3232/recommendation");
        // Get an OK response (the *connection* worked, the *API* provides an error response)
        assertEquals(200, loadConnection.getResponseCode());
        // Get the expected response: a success
        Map<String, Object> body =
                adapter.fromJson(new Buffer().readFrom(loadConnection.getInputStream()));
        showDetailsIfError(body);
        assertEquals("error", body.get("type"));

        // Mocked data: correct temp? We know what it is, because we mocked.
        List<String> list = List.of("hoodie", "shorts", "sandals", "sunglasses");
//
        System.out.println(list);
        loadConnection.disconnect();
    }

    /**
     * Test to ensure that a succesful request to the API returns the expected result
     * @throws IOException
     * @throws CustomException
     */
    @Test
    public void testAPIRequestSuccessReal() throws IOException, CustomException {
        /////////// LOAD DATASOURCE ///////////
        // Set up the request, make the request
        HttpURLConnection loadConnection =
                tryRequest("http://localhost:3232/recommendation");
        // Get an OK response (the *connection* worked, the *API* provides an error response)
        assertEquals(200, loadConnection.getResponseCode());
        // Get the expected response: a success
        Map<String, Object> body =
                adapter.fromJson(new Buffer().readFrom(loadConnection.getInputStream()));
        showDetailsIfError(body);
        assertEquals("success", body.get("result"));

        // Mocked data: correct temp? We know what it is, because we mocked.
        List<String> list = List.of("hoodie", "shorts", "sandals", "sunglasses");
//
        assertEquals(list,body.get("recommendation"));
        loadConnection.disconnect();
    }
//
    private void showDetailsIfError(Map<String, Object> body) {
        if (body.containsKey("type") && "error".equals(body.get("type"))) {
            System.out.println(body.toString());
        }
    }
}

