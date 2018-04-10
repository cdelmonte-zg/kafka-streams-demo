package de.cdelmonte.afs.neo4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.afs.neo4j.entity.Transaction;
import de.cdelmonte.afs.neo4j.entity.repository.TransactionRepository;

@Service
public class ImporterService {

  @Autowired
  TransactionRepository transactionRepository;

  public void importTransactions(String payload) {
    Gson gson = new Gson();
    Transaction ttn = gson.fromJson(payload, Transaction.class);
    transactionRepository.save(ttn);
  }
}
