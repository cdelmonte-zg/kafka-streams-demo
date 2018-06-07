package de.cdelmonte.fds.neo4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.cdelmonte.fds.neo4j.service.importer.PayloadType;
import de.cdelmonte.fds.neo4j.service.importer.TransactionsImporterService;
import de.cdelmonte.fds.neo4j.service.importer.UserImporterService;

@Service
public class ImporterFactory {

  @Autowired
  UserImporterService userImporterService;

  @Autowired
  TransactionsImporterService transactionsImporterService;

  public void importPayload(PayloadType type, String payload) {
    if (type.equals(PayloadType.TRANSACTION))
      try {
        transactionsImporterService.importTransactions(payload);
      } catch (Exception e) {
        e.getMessage();
      }
    else if (type.equals(PayloadType.USER))
      userImporterService.importUsers(payload);
  }
}
