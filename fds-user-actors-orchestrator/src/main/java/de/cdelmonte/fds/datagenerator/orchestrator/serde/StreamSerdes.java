package de.cdelmonte.fds.datagenerator.orchestrator.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.util.serializer.JsonDeserializer;
import de.cdelmonte.fds.datagenerator.orchestrator.util.serializer.JsonSerializer;


public class StreamSerdes {
  public static Serde<Actor> actorSerde() {
    return new ActorSerde();
  }

  public static Serde<Click> clickSerde() {
    return new ClickSerde();
  }

  static final class ActorSerde extends WrapperSerde<Actor> {
    public ActorSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(Actor.class));
    }
  }

  static final class ClickSerde extends WrapperSerde<Click> {
    public ClickSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(Click.class));
    }
  }

  private static class WrapperSerde<T> implements Serde<T> {
    private JsonSerializer<T> serializer;
    private JsonDeserializer<T> deserializer;

    public WrapperSerde(JsonSerializer<T> serializer, JsonDeserializer<T> deserializer) {
      this.serializer = serializer;
      this.deserializer = deserializer;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public void close() {}

    @Override
    public Serializer<T> serializer() {
      return serializer;
    }

    @Override
    public Deserializer<T> deserializer() {
      return deserializer;
    }
  }
}
