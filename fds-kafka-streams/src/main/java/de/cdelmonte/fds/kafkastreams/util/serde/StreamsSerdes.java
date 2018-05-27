package de.cdelmonte.fds.kafkastreams.util.serde;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import com.google.gson.reflect.TypeToken;
import de.cdelmonte.fds.kafkastreams.collectors.FixedSizePriorityQueue;
import de.cdelmonte.fds.kafkastreams.model.Purchase;
import de.cdelmonte.fds.kafkastreams.model.ShareVolume;
import de.cdelmonte.fds.kafkastreams.model.StockTransaction;
import de.cdelmonte.fds.kafkastreams.model.Transaction;
import de.cdelmonte.fds.kafkastreams.model.User;
import de.cdelmonte.fds.kafkastreams.util.serializer.JsonDeserializer;
import de.cdelmonte.fds.kafkastreams.util.serializer.JsonSerializer;

public class StreamsSerdes {
  public static Serde<Purchase> PurchaseSerde() {
    return new PurchaseSerde();
  }

  public static Serde<User> UserSerde() {
    return new UserSerde();
  }

  public static Serde<Transaction> transactionSerde() {
    return new TransactionSerde();
  }

  public static Serde<StockTransaction> StockTransactionSerde() {
    return new StockTransactionSerde();
  }

  public static Serde<FixedSizePriorityQueue> FixedSizePriorityQueueSerde() {
    return new FixedSizePriorityQueueSerde();
  }

  public static Serde<ShareVolume> ShareVolumeSerde() {
    return new ShareVolumeSerde();
  }

  public static Serde<List<StockTransaction>> TransactionsListSerde() {
    return new TransactionsListSerde();
  }

  public static final class PurchaseSerde extends WrapperSerde<Purchase> {
    public PurchaseSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(Purchase.class));
    }
  }

  public static final class UserSerde extends WrapperSerde<User> {
    public UserSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(User.class));
    }
  }

  public static final class TransactionSerde extends WrapperSerde<Transaction> {
    public TransactionSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(Transaction.class));
    }
  }

  public static final class StockTransactionSerde extends WrapperSerde<StockTransaction> {
    public StockTransactionSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(StockTransaction.class));
    }
  }

  public static final class FixedSizePriorityQueueSerde
      extends WrapperSerde<FixedSizePriorityQueue> {
    public FixedSizePriorityQueueSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(FixedSizePriorityQueue.class));
    }
  }

  public static final class ShareVolumeSerde extends WrapperSerde<ShareVolume> {
    public ShareVolumeSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(ShareVolume.class));
    }
  }

  public static final class TransactionsListSerde extends WrapperSerde<List<StockTransaction>> {
    private static final Type listType = new TypeToken<List<StockTransaction>>() {}.getType();

    public TransactionsListSerde() {
      super(new JsonSerializer<>(), new JsonDeserializer<>(listType));
    }
  }

  private static class WrapperSerde<T> implements Serde<T> {
    private JsonSerializer<T> serializer;
    private JsonDeserializer<T> deserializer;

    WrapperSerde(JsonSerializer<T> serializer, JsonDeserializer<T> deserializer) {
      this.serializer = serializer;
      this.deserializer = deserializer;
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public void close() {

    }

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
