package de.cdelmonte.afs.kafkastreams.util.serializer;

import java.nio.charset.Charset;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.cdelmonte.afs.kafkastreams.collectors.FixedSizePriorityQueue;

public class JsonSerializer<T> implements Serializer<T> {

  private Gson gson;

  public JsonSerializer() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(FixedSizePriorityQueue.class,
        new FixedSizePriorityQueueAdapter().nullSafe());
    gson = builder.create();
  }

  @Override
  public void configure(Map<String, ?> map, boolean b) {

  }

  @Override
  public byte[] serialize(String topic, T t) {
    return gson.toJson(t).getBytes(Charset.forName("UTF-8"));
  }

  @Override
  public void close() {

  }
}
