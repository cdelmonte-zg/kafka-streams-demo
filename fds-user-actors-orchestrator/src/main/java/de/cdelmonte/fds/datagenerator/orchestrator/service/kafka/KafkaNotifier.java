package de.cdelmonte.fds.datagenerator.orchestrator.service.kafka;

import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;


public interface KafkaNotifier {

  void notify(Click click);
}
