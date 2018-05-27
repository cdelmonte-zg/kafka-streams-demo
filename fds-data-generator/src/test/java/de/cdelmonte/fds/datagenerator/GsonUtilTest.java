package de.cdelmonte.fds.datagenerator;

import static org.junit.Assert.assertTrue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;
import de.cdelmonte.fds.datagenerator.mocker.MocksGenerator;

public class GsonUtilTest {
  private MocksGenerator mock;

  @Before
  public void setUp() {
    mock = new MocksGenerator();
  }

  @After
  public void tearDown() {
    mock = null;
  }

  @Test
  public void testMockedUsers() throws Exception {
    Gson gson = new Gson();
    String results = gson.toJson(mock.generateMocks("user", 30));
    assertTrue("Is Json Valid?", isJSONValid(results));
  }

  @Test
  public void testMockedTransactions() throws Exception {
    mock.generateMocks("user", 30);

    Gson gson = new Gson();
    String results = gson.toJson(mock.generateMocks("transaction", 30));
    assertTrue("Is Json Valid?", isJSONValid(results));
  }

  public boolean isJSONValid(String test) {
    try {
      new JSONObject(test);
    } catch (JSONException ex) {
      try {
        new JSONArray(test);
      } catch (JSONException ex1) {
        return false;
      }
    }

    return true;
  }
}
