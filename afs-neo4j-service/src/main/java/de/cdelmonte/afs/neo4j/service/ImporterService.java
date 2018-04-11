package de.cdelmonte.afs.neo4j.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.afs.neo4j.entity.CreditCard;
import de.cdelmonte.afs.neo4j.entity.Payload;
import de.cdelmonte.afs.neo4j.entity.Person;
import de.cdelmonte.afs.neo4j.entity.Transaction;
import de.cdelmonte.afs.neo4j.entity.repository.PersonRepository;

@Service
public class ImporterService {

  @Autowired
  PersonRepository personRepository;

  public void importTransactions(String payload) {
    Gson gson = new Gson();
    Payload pload = gson.fromJson(payload, Payload.class);

    Person customer = new Person(pload.getFirstName(), pload.getLastName());
    CreditCard creditCard = new CreditCard(pload.getCreditCardNumber(), new Date());
    Transaction transaction = new Transaction(pload.getItemPurchased(), pload.getQuantity(),
        pload.getPrice(), pload.getPurchaseDate(), pload.getZipCode(), pload.getStoreId());

    customer.hasCreditCard(creditCard);
    customer.withTransaction(transaction);

    personRepository.save(customer);
  }
}
