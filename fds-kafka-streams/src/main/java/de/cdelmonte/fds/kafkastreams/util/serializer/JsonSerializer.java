package de.cdelmonte.fds.kafkastreams.util.serializer;

import java.nio.charset.Charset;
import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import de.cdelmonte.fds.kafkastreams.collectors.FixedSizePriorityQueue;
import de.cdelmonte.fds.kafkastreams.model.payment.BankAccount;
import de.cdelmonte.fds.kafkastreams.model.payment.BitcoinAccount;
import de.cdelmonte.fds.kafkastreams.model.payment.PaymentAccount;
import de.cdelmonte.fds.kafkastreams.model.payment.PaypalAccount;

public class JsonSerializer<T> implements Serializer<T> {

  private final Gson gson;

  public JsonSerializer() {
    final RuntimeTypeAdapterFactory<PaymentAccount> paymentAdapter = RuntimeTypeAdapterFactory
        .of(PaymentAccount.class, "type").registerSubtype(BankAccount.class)
        .registerSubtype(PaypalAccount.class).registerSubtype(BitcoinAccount.class);
    final Gson gson =
        new GsonBuilder().registerTypeAdapterFactory(paymentAdapter).registerTypeAdapter(
            FixedSizePriorityQueue.class, new FixedSizePriorityQueueAdapter().nullSafe()).create();

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
