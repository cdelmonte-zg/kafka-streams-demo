package de.cdelmonte.afs.kafkastreams.configuration;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import de.cdelmonte.afs.kafkastreams.model.Purchase;
import de.cdelmonte.afs.kafkastreams.util.serde.StreamsSerdes;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamsConfiguration {

  @Value("${spring.kafka.bootstrap-servers}")
  String kafkaServer;

  @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
  public StreamsConfig kStreamsConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "testStreams");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
    props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG,
        WallclockTimestampExtractor.class.getName());

    return new StreamsConfig(props);
  }

  @Bean
  public KStream<Integer, String> kStream(StreamsBuilder kStreamBuilder) {
    Serde<String> stringSerde = Serdes.String();
    Serde<Integer> integerSerde = Serdes.Integer();

    KStream<Integer, String> stream1 =
        kStreamBuilder.stream("strings", Consumed.with(integerSerde, stringSerde));
    KStream<Integer, String> uppercasedStream = stream1.mapValues(String::toUpperCase);

    uppercasedStream.to("strings2");

    uppercasedStream.print(Printed.<Integer, String>toSysOut().withLabel("Hello"));

    return uppercasedStream;
  }

  @Bean
  public KStream<String, Purchase> transactionsStream(StreamsBuilder kStreamBuilder) {
    Serde<Purchase> purchaseSerde = StreamsSerdes.PurchaseSerde();
    Serde<String> stringSerde = Serdes.String();

    KStream<String, Purchase> purchaseKStream =
        kStreamBuilder.stream("transactions", Consumed.with(stringSerde, purchaseSerde));

    KStream<String, Purchase> masked =
        purchaseKStream.mapValues(p -> Purchase.builder(p).maskCreditCard().build());

    masked.print(Printed.<String, Purchase>toSysOut().withLabel("Transactions are coming!!"));
    masked.to("transactions-to-import", Produced.with(stringSerde, purchaseSerde));

    return purchaseKStream;
  }

  @Bean
  public KStream<Integer, String> kStream2(StreamsBuilder kStreamBuilder) {
    KStream<Integer, String> stream = kStreamBuilder.stream("strings2");
    stream.mapValues(String::toLowerCase).to("strings3");

    stream.print(Printed.<Integer, String>toSysOut().withLabel("world"));

    return stream;
  }
}
