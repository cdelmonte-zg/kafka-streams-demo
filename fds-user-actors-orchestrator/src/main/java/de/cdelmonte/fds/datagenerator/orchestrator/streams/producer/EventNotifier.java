package de.cdelmonte.fds.datagenerator.orchestrator.streams.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;


public class EventNotifier implements AutoCloseable {
  private KafkaProducer<String, byte[]> producer = null;
  private final String topic;

  public EventNotifier(String bootstrapServers, String clientId, String topic) {
    this.topic = topic;
    Properties props = new Properties();
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.setProperty(ProducerConfig.CLIENT_ID_CONFIG, clientId);

    if (producer == null) {
      this.producer = new KafkaProducer<>(props, new StringSerializer(), new ByteArraySerializer());
    }
  }

  public void sendNotification(String key, byte[] notification) {
    producer.send(new ProducerRecord<>(topic, key, notification));
    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
  }

  public void sendSyncNotification(String key, byte[] notification)
      throws InterruptedException, ExecutionException {
    final ProducerRecord<String, byte[]> record = new ProducerRecord<>(topic, notification);
    RecordMetadata metadata = producer.send(record).get();
    System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d)\n",
        record.key(), record.value(), metadata.partition(), metadata.offset());

    System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
  }

  @Override
  public void close() throws Exception {}
}
