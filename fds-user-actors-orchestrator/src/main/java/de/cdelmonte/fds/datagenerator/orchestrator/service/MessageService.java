package de.cdelmonte.fds.datagenerator.orchestrator.service;

import java.io.Serializable;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.serde.StreamSerdes;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class MessageService implements Serializable {
  private static final long serialVersionUID = 1L;

  public void sendTransactionToKafka(Event e) {
    Logger.log("Sending transaction to kafka producer");
  }

  public void sendClickToKafka(Event e) {
    byte[] data =
        StreamSerdes.clickSerde().serializer().serialize("test", ((Click) e.getModel()).filter());

    Click click = StreamSerdes.clickSerde().deserializer().deserialize("test", data);

    Logger.log("Sending click to kafka producer with json: \n" + click.toString());
  }

  public void sendActorToKafka(Event e) {
    String string = e.getModel().toString();
    System.out.println("Sending actor update to kafka producer with json: \n" + string);
  }
}
