package de.cdelmonte.fds.neo4j.util.serializer;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonDeserializer<T> implements Deserializer<T> {
  private Gson gson;
  private Class<T> deserializedClass;

  public JsonDeserializer() {}

  public JsonDeserializer(Class<T> deserializedClass) {
    this.deserializedClass = deserializedClass;
    init();
  }

  private void init() {
    GsonBuilder builder = new GsonBuilder();
    gson = builder.create();
  }

  @Override
  public void close() {}

  @Override
  @SuppressWarnings("unchecked")
  public void configure(Map<String, ?> map, boolean b) {
    if (deserializedClass == null) {
      deserializedClass = (Class<T>) map.get("serializedClass");
    }
  }

  @Override
  public T deserialize(String s, byte[] bytes) {
    T des = null;

    if (bytes == null) {
      return null;
    }

    try {
      des = gson.fromJson(new String(bytes), deserializedClass);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return des;
  }
}
