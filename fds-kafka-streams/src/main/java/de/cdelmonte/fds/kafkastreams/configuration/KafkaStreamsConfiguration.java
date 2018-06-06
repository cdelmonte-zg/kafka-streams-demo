package de.cdelmonte.fds.kafkastreams.configuration;

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
import de.cdelmonte.fds.kafkastreams.model.Transaction;
import de.cdelmonte.fds.kafkastreams.model.User;
import de.cdelmonte.fds.kafkastreams.util.serde.StreamsSerdes;

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
  public KStream<String, Transaction> transactionStream(StreamsBuilder kStreamBuilder) {
    Serde<Transaction> transactionSerde = StreamsSerdes.transactionSerde();
    Serde<String> stringSerde = Serdes.String();

    KStream<String, Transaction> transactionKStream = kStreamBuilder.stream("training-transactions",
        Consumed.with(stringSerde, transactionSerde));

    KStream<String, Transaction> tests =
        transactionKStream.mapValues(p -> Transaction.builder(p).setTestData().build());

    tests.print(
        Printed.<String, Transaction>toSysOut().withLabel("Training transactions are coming!!"));
    tests.to("training-transactions-to-import", Produced.with(stringSerde, transactionSerde));

    return transactionKStream;
  }

  @Bean
  public KStream<String, User> userStream(StreamsBuilder kStreamBuilder) {
    Serde<User> userSerde = StreamsSerdes.UserSerde();
    Serde<String> stringSerde = Serdes.String();

    KStream<String, User> userKStream =
        kStreamBuilder.stream("training-users", Consumed.with(stringSerde, userSerde));

    KStream<String, User> rated =
        userKStream.mapValues(u -> User.builder(u).setTestData().setFraudScore(0.0f).build());

    rated.print(Printed.<String, User>toSysOut().withLabel("Training users are coming!!"));
    rated.to("training-users-to-import", Produced.with(stringSerde, userSerde));

    return userKStream;
  }

  @Bean
  public KStream<String, User> suspiciousUserStream(StreamsBuilder kStreamBuilder) {
    Serde<User> userSerde = StreamsSerdes.UserSerde();
    Serde<String> stringSerde = Serdes.String();

    KStream<String, User> userKStream =
        kStreamBuilder.stream("training-suspicious-users", Consumed.with(stringSerde, userSerde));

    KStream<String, User> rated =
        userKStream.mapValues(u -> User.builder(u).setTestData().setFraudScore(1.0f).build());

    rated.print(Printed.<String, User>toSysOut().withLabel("Streaming training suspicious users."));
    rated.to("training-users-to-import", Produced.with(stringSerde, userSerde));

    return userKStream;
  }
}
