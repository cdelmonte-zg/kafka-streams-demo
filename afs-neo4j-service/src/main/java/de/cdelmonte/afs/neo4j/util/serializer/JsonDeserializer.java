package de.cdelmonte.afs.neo4j.util.serializer;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import de.cdelmonte.afs.neo4j.model.payment.BankAccount;
import de.cdelmonte.afs.neo4j.model.payment.BitcoinAccount;
import de.cdelmonte.afs.neo4j.model.payment.PaymentAccount;
import de.cdelmonte.afs.neo4j.model.payment.PaypalAccount;


public class JsonDeserializer<T> implements Deserializer<T> {
  private Gson gson;
  private Class<T> deserializedClass;

  public JsonDeserializer() {}

  public JsonDeserializer(Class<T> deserializedClass) {
    this.deserializedClass = deserializedClass;
    init();
  }

  private void init() {
    final RuntimeTypeAdapterFactory<PaymentAccount> paymentAdapter = RuntimeTypeAdapterFactory
        .of(PaymentAccount.class, "type").registerSubtype(BankAccount.class)
        .registerSubtype(PaypalAccount.class).registerSubtype(BitcoinAccount.class);

    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapterFactory(paymentAdapter);
    gson = builder.create();
  }

  @Override
  public void close() {}

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
      des = gson.fromJson(new String(bytes), deserializedClass);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return des;
  }
}
