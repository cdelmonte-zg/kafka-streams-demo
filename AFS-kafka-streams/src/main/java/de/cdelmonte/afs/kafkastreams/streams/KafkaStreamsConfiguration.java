package de.cdelmonte.afs.kafkastreams.streams;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;

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
    props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
    props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
    props.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG,
        WallclockTimestampExtractor.class.getName());

    return new StreamsConfig(props);
  }

  @Bean
  public KStream<Integer, String> kStream(StreamsBuilder kStreamBuilder) {
    KStream<Integer, String> stream = kStreamBuilder.stream("strings");
    stream.mapValues(String::toUpperCase).to("strings2");

    stream.print(Printed.<Integer, String>toSysOut().withLabel("Hello"));

    return stream;
  }

  @Bean
  public KStream<Integer, String> kStream2(StreamsBuilder kStreamBuilder) {
    KStream<Integer, String> stream = kStreamBuilder.stream("strings2");
    stream.mapValues(String::toUpperCase).to("strings3");

    stream.print(Printed.<Integer, String>toSysOut().withLabel("Hello"));

    return stream;
  }
}
