package de.cdelmonte.afs.datagenerator;

import static org.junit.Assert.assertTrue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import de.cdelmonte.afs.datagenerator.mocker.MocksGenerator;

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
  public void simpleJson() throws Exception {
    String result = mock.generateMocks(100, 30);

    assertTrue("Is Json Valid?", isJSONValid(result));

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
