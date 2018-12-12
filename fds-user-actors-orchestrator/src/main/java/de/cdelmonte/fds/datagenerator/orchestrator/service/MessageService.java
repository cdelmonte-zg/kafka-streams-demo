package de.cdelmonte.fds.datagenerator.orchestrator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.service.kafka.KafkaMessenger;


public class MessageService {
  private List<BlockingQueue<EventModel>> messengerQueues = new ArrayList<>(10);

  public MessageService() {
    BlockingQueue<EventModel> kafkaQueue = new LinkedBlockingQueue<>(1000);
    Runnable kS = new KafkaMessenger<EventModel>(kafkaQueue);
    new Thread((Runnable) kS).start();
    messengerQueues.add(kafkaQueue);
  }

  public void registerMessenger(BlockingQueue<EventModel> m) {
    messengerQueues.add(m);
  }

  public void removeMessenger(BlockingQueue<EventModel> m) {
    messengerQueues.remove(m);
  }

  public void sendTo(EventModel model) {
    for (BlockingQueue<EventModel> q : messengerQueues) {
      try {
        q.put(model);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
