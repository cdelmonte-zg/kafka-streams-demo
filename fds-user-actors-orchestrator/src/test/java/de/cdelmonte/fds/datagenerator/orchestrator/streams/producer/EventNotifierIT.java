package de.cdelmonte.fds.datagenerator.orchestrator.streams.producer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rnorth.ducttape.unreliables.Unreliables;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;


public class EventNotifierIT {
  String topic;
  String key;

  @Rule
  KafkaContainer kafka = new KafkaContainer();

  @BeforeEach
  public void setup() {
    kafka.start();
    topic = "notification";
    key = "notification-key";
  }

  @Test
  public void sendNotification() throws Exception {
    try (
        EventNotifier notifier = new EventNotifier(kafka.getBootstrapServers(), key, topic);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(
            ImmutableMap.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers(),
                ConsumerConfig.GROUP_ID_CONFIG, "tc-" + UUID.randomUUID(),
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"),

            new StringDeserializer(), new StringDeserializer());
    ) {
      consumer.subscribe(Arrays.asList(topic));

      String notificationMessage = "Hello stream world!";
      notifier.sendNotification(key, notificationMessage);

      Unreliables.retryUntilTrue(10, TimeUnit.SECONDS, () -> {
        ConsumerRecords<String, String> records = consumer.poll(100);

        if (records.isEmpty()) {
          return false;
        }

        assertThat(records).hasSize(1)
            .extracting(ConsumerRecord::topic, ConsumerRecord::key, ConsumerRecord::value)
            .containsExactly(tuple(topic, key, notificationMessage));

        return true;
      });

      consumer.unsubscribe();
    }
  }

  @Test
  public void sendWrongNotification() throws Exception {
    try (
        EventNotifier notifier = new EventNotifier(kafka.getBootstrapServers(), key, topic);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(
            ImmutableMap.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers(),
                ConsumerConfig.GROUP_ID_CONFIG, "tc-" + UUID.randomUUID(),
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"),

            new StringDeserializer(), new StringDeserializer());
    ) {
      String notificationMessageSent = "Hello stream world!";
      String notificationMessageAttended = "Hello stream world!XXX";

      consumer.subscribe(Arrays.asList(topic));
      notifier.sendNotification(key, notificationMessageSent);

      Unreliables.retryUntilSuccess(10, TimeUnit.SECONDS, () -> {
        ConsumerRecords<String, String> records = consumer.poll(100);

        if (records.isEmpty()) {
          return false;
        }

        assertThat(records).hasSize(1)
            .extracting(ConsumerRecord::topic, ConsumerRecord::key, ConsumerRecord::value)
            .doesNotContain(tuple(topic, key, notificationMessageAttended));

        return true;
      });

      consumer.unsubscribe();
    }
  }

}
