package de.cdelmonte.fds.datagenerator.orchestrator.streams.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;


public class EventNotifier implements AutoCloseable {
  private final KafkaProducer<String, byte[]> producer;
  private final String topic;

  public EventNotifier(String bootstrapServers, String clientId, String topic) {
    this.topic = topic;

    Properties props = new Properties();
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, clientId);

    this.producer = new KafkaProducer<>(props, new StringSerializer(), new ByteArraySerializer());
  }

  public void sendNotification(String key, byte[] notification) {
    producer.send(new ProducerRecord<>(topic, key, notification));
  }

  @Override
  public void close() throws Exception {}
}
