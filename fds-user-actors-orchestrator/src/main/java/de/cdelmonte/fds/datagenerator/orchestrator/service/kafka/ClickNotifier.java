package de.cdelmonte.fds.datagenerator.orchestrator.service.kafka;

import org.junit.Rule;
import org.testcontainers.containers.KafkaContainer;

import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.serde.StreamSerdes;
import de.cdelmonte.fds.datagenerator.orchestrator.streams.producer.EventNotifier;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class ClickNotifier implements KafkaNotifier {
  @Rule
  KafkaContainer kafka = new KafkaContainer();

  @Override
  public void notify(Click click) {
    kafka.start();
    String topic = "notification";
    String key = "notification-key";

    byte[] data = StreamSerdes.clickSerde().serializer().serialize("test", click.filter());

    EventNotifier notifier = new EventNotifier(kafka.getBootstrapServers(), key, topic);

    notifier.sendNotification("test", data);

    Click desClick = StreamSerdes.clickSerde().deserializer().deserialize("test", data);
    Logger.log("Sending click to kafka producer with json: \n" + desClick.toString());
  }
}
