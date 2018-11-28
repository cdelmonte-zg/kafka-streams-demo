package de.cdelmonte.fds.datagenerator.orchestrator.util.serializer;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


public class JsonDeserializer<T> implements Deserializer<T> {
  private ObjectMapper mapper;
  private Class<T> deserializedClass;

  public JsonDeserializer(Class<T> deserializedClass) {
    mapper = new ObjectMapper();
    this.deserializedClass = deserializedClass;
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {}

  @Override
  public T deserialize(String topic, byte[] data) {
    T deserializedObject = null;
    try {
      deserializedObject = mapper.readValue(data, deserializedClass);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return deserializedObject;
  }

  @Override
  public void close() {}
}
