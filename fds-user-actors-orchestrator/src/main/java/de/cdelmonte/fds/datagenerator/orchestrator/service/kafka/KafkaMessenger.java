package de.cdelmonte.fds.datagenerator.orchestrator.service.kafka;

import java.util.concurrent.BlockingQueue;

import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.serde.StreamSerdes;
import de.cdelmonte.fds.datagenerator.orchestrator.streams.producer.EventNotifier;


public class KafkaMessenger<T extends EventModel> implements Runnable {
  private BlockingQueue<T> queue;
  private EventNotifier notifier;

  public KafkaMessenger(BlockingQueue<T> kafkaQueue) {
    this.notifier = new EventNotifier("localhost:29092", "notification-key", "events");
    this.queue = kafkaQueue;
  }

  @Override
  public void run() {
    while (true) {
      try {
        T model = queue.take();
        sendMessage(model);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void sendMessage(T model) throws Exception {
    notifier.sendSyncNotification("test", StreamSerdes.serializeModel("test", model.filter()));
  }
}
