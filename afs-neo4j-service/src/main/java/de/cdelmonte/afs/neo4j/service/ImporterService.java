package de.cdelmonte.afs.neo4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.afs.neo4j.entity.CID;
import de.cdelmonte.afs.neo4j.entity.Click;
import de.cdelmonte.afs.neo4j.entity.Merchant;
import de.cdelmonte.afs.neo4j.entity.Network;
import de.cdelmonte.afs.neo4j.entity.Person;
import de.cdelmonte.afs.neo4j.entity.SourceIP;
import de.cdelmonte.afs.neo4j.entity.TransactionEntity;
import de.cdelmonte.afs.neo4j.entity.repository.CIDRepository;
import de.cdelmonte.afs.neo4j.entity.repository.ClickRepository;
import de.cdelmonte.afs.neo4j.entity.repository.MerchantRepository;
import de.cdelmonte.afs.neo4j.entity.repository.NetworkRepository;
import de.cdelmonte.afs.neo4j.entity.repository.PersonRepository;
import de.cdelmonte.afs.neo4j.entity.repository.SourceIPRepository;
import de.cdelmonte.afs.neo4j.entity.repository.TransactionRepository;
import de.cdelmonte.afs.neo4j.model.Transaction;
import de.cdelmonte.afs.neo4j.model.User;

@Service
public class ImporterService {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  ClickRepository clickRepository;

  @Autowired
  MerchantRepository merchantRepository;

  @Autowired
  NetworkRepository networkRepository;

  @Autowired
  SourceIPRepository sourceIPRepository;

  @Autowired
  CIDRepository cidRepository;

  public void importTransactions(String payload) {
    Gson gson = new Gson();

    Transaction tr = gson.fromJson(payload, Transaction.class);

    TransactionEntity transaction = new TransactionEntity();

    transaction.setIdExt(tr.getId());
    transaction.setNetworkTransactionId(tr.getNetworkTransactionId());
    transaction.setNetworkStatus(tr.getNetworkStatus());
    transaction.setDate(tr.getDate());
    transaction.setAmount(tr.getAmount());
    transaction.setCommission(tr.getCommission());
    transaction.setUserCommission(tr.getUserCommission());
    transaction.setStatus(tr.getStatus());
    transaction.setCreatedAt(tr.getCreatedAt());
    transaction.setUpdatedAt(tr.getUpdatedAt());
    transaction.setImported(tr.isImported());
    transaction.setLastImportedAt(tr.getLastImportedAt());

    Merchant merchant = merchantRepository.findByName(tr.getMerchant().getName());
    if (merchant == null) {
      merchant = new Merchant();
      merchant.setIdExt(tr.getMerchant().getId());
      merchant.setName(tr.getMerchant().getName());
    }

    Click click = clickRepository.findByExtId(tr.getClick().getId());
    if (click == null) {
      click = new Click(tr.getClick().getId(), tr.getClick().getDate(), tr.getClick().getSource());
    }

    Person person = personRepository.findByIdExt(tr.getUserId());
    person.withMerchant(merchant);
    person.withTransaction(transaction);

    SourceIP sourceIp = sourceIPRepository.findByIp(tr.getClick().getIp());
    if (sourceIp == null) {
      sourceIp = new SourceIP(tr.getClick().getIp());
    }
    sourceIp.withClick(click);
    sourceIp.withPerson(person);
    sourceIp.withTransaction(transaction);

    CID cid = cidRepository.findByCid(tr.getLastCid());
    if (cid == null) {
      cid = new CID(tr.getLastCid());
    }

    Network network = networkRepository.findByName(tr.getNetworkName());
    if (network == null) {
      network = new Network(tr.getNetworkName());
    }

    transaction.withCID(cid);
    transaction.withNetwork(network);

    try {
      merchantRepository.save(merchant);
      transactionRepository.save(transaction);
      personRepository.save(person);
      sourceIPRepository.save(sourceIp);
      networkRepository.save(network);
      cidRepository.save(cid);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void importUsers(String payload) {
    Gson gson = new Gson();
    User user = gson.fromJson(payload, User.class);

    Person person = new Person(user.getId(), user.getEmail(), user.getUsername(), user.getName(),
        user.getBirthdate(), user.getRegistrationDate(), user.getLastLoginDate(),
        user.getLastCountry(), user.getLastIp(), user.getLastCid(), user.getLanguages(),
        user.getPaymentsBlockedTill(), user.isEmailVerified(), user.isPaymentsBlocked(),
        user.isBlocked(), user.isDoNotPay(), user.isIgnoreCountry(), user.isAutomaticPayment(),
        user.isAdsEnabled(), user.isToolbarUser(), user.isMobileAppUser(),
        user.getNumberOfTransactions());
    try {
      personRepository.save(person);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
