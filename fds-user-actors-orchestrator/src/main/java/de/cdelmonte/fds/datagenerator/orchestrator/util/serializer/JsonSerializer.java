package de.cdelmonte.fds.datagenerator.orchestrator.util.serializer;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


public class JsonSerializer<T> implements Serializer<T> {
  private ObjectMapper mapper;

  public JsonSerializer() {
    mapper = new ObjectMapper();
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {}

  @Override
  public byte[] serialize(String topic, T data) {
    try {
      return mapper.writeValueAsBytes(data);
    } catch (JsonProcessingException | RuntimeException e) {
      throw new SerializationException("Error serializing to JSON with Jackson", e);
    }
  }

  @Override
  public void close() {}
}
