package de.cdelmonte.afs.kafkastreams.util.serializer;


import java.lang.reflect.Type;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.cdelmonte.afs.kafkastreams.collectors.FixedSizePriorityQueue;


public class JsonDeserializer<T> implements Deserializer<T> {
  private Gson gson;
  private Class<T> deserializedClass;
  private Type reflectionTypeToken;

  public JsonDeserializer(Class<T> deserializedClass) {
    this.deserializedClass = deserializedClass;
    init();

  }

  public JsonDeserializer(Type reflectionTypeToken) {
    this.reflectionTypeToken = reflectionTypeToken;
    init();
  }

  private void init() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(FixedSizePriorityQueue.class,
        new FixedSizePriorityQueueAdapter().nullSafe());
    gson = builder.create();
  }

  public JsonDeserializer() {}

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
      Type deserializeFrom = deserializedClass != null ? deserializedClass : reflectionTypeToken;
      des = gson.fromJson(new String(bytes), deserializeFrom);
    } catch (Exception e) {
      e.getMessage();
      System.out.println("XXX " + s);
    }

    return des;
  }

  @Override
  public void close() {

  }
}
