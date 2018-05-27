package de.cdelmonte.fds.neo4j.util.serializer;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import de.cdelmonte.fds.neo4j.model.User;

public class DeserializerFactory {
  public static Deserializer<User> UserDeserializer() {
    return new UserDeserializer();
  }

  public static final class UserDeserializer extends WrapperDeserializer<User> {
    public UserDeserializer() {
      super(new JsonDeserializer<>(User.class));
    }
  }

  public static class WrapperDeserializer<T> implements Deserializer<T> {
    private JsonDeserializer<T> deserializer;

    WrapperDeserializer(JsonDeserializer<T> deserializer) {
      this.deserializer = deserializer;
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {
      this.deserializer.configure(map, b);
    }

    @Override
    public void close() {
      this.deserializer.close();
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
      return this.deserializer.deserialize(s, bytes);
    }
  }
}
