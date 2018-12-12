package de.cdelmonte.fds.datagenerator.orchestrator.serde;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Transaction;
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

  public static Serde<Transaction> transactionSerde() {
    return new TransactionSerde();
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

  static final class TransactionSerde extends WrapperSerde<Transaction> {
    public TransactionSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(Transaction.class));
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

  public static byte[] serializeModel(String key, EventModel model) {
    byte[] data = null;

    if (model instanceof Actor) {
      data = StreamSerdes.actorSerde().serializer().serialize(key, (Actor) model.filter());
    } else if (model instanceof Transaction) {
      data =
          StreamSerdes.transactionSerde().serializer().serialize(key, (Transaction) model.filter());
    } else if (model instanceof Click) {
      data = StreamSerdes.clickSerde().serializer().serialize(key, (Click) model.filter());
    }

    return data;
  }
}
