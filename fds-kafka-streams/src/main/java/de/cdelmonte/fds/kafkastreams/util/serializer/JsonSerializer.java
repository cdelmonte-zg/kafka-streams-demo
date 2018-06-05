package de.cdelmonte.fds.kafkastreams.util.serializer;

import java.nio.charset.Charset;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.cdelmonte.fds.kafkastreams.collectors.FixedSizePriorityQueue;

public class JsonSerializer<T> implements Serializer<T> {

  private final Gson gson;

  public JsonSerializer() {
    final Gson gson = new GsonBuilder().registerTypeAdapter(FixedSizePriorityQueue.class,
        new FixedSizePriorityQueueAdapter().nullSafe()).create();

    this.gson = gson;
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
