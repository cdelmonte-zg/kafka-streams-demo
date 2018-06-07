package de.cdelmonte.fds.neo4j.service.importer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.fds.neo4j.entity.CID;
import de.cdelmonte.fds.neo4j.entity.Click;
import de.cdelmonte.fds.neo4j.entity.Merchant;
import de.cdelmonte.fds.neo4j.entity.Network;
import de.cdelmonte.fds.neo4j.entity.Person;
import de.cdelmonte.fds.neo4j.entity.SourceIP;
import de.cdelmonte.fds.neo4j.entity.TransactionEntity;
import de.cdelmonte.fds.neo4j.entity.repository.CIDRepository;
import de.cdelmonte.fds.neo4j.entity.repository.ClickRepository;
import de.cdelmonte.fds.neo4j.entity.repository.MerchantRepository;
import de.cdelmonte.fds.neo4j.entity.repository.NetworkRepository;
import de.cdelmonte.fds.neo4j.entity.repository.PersonRepository;
import de.cdelmonte.fds.neo4j.entity.repository.SourceIPRepository;
import de.cdelmonte.fds.neo4j.entity.repository.TransactionRepository;
import de.cdelmonte.fds.neo4j.model.Transaction;
import de.cdelmonte.fds.neo4j.service.RatingsGeneratorService;

@Service
public class TransactionsImporterService {
  @Autowired
  RatingsGeneratorService ratingsGeneratorService;

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

  public void importTransactions(String payload) throws Exception {
    Transaction transaction = new Gson().fromJson(payload, Transaction.class);

    Person person = personRepository.findByIdExt(transaction.getUserId());
    if (person == null) {
      throw new Exception("The transaction with id " + transaction.getId() + " is unrelated.");
    }

    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setIdExt(transaction.getId());
    transactionEntity.setDate(transaction.getDate());
    transactionEntity.setAmount(transaction.getAmount());
    transactionEntity.setCommission(transaction.getCommission());
    transactionEntity.setUserCommission(transaction.getUserCommission());
    transactionEntity.setStatus(transaction.getStatus());
    transactionEntity.setCreatedAt(transaction.getCreatedAt());
    transactionEntity.setUpdatedAt(transaction.getUpdatedAt());
    transactionEntity.setImported(transaction.isImported());
    transactionEntity.setLastImportedAt(transaction.getLastImportedAt());
    transactionEntity.setTestData(transaction.isTestData());

    Merchant merchant = withMerchant(transaction);
    person.withMerchant(merchant);
    person.withTransaction(transactionEntity);
    try {
      personRepository.save(person);
    } catch (Exception e) {
      e.getMessage();
    }

    saveSourceIp(transaction, transactionEntity, person);
    saveCid(transaction, transactionEntity);
    saveNetwork(transaction, transactionEntity);

    try {
      transactionRepository.save(transactionEntity);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      ratingsGeneratorService.setPersonRatingFactors(transaction.getUserId(), person);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void saveNetwork(Transaction transaction, TransactionEntity transactionEntity) {
    Network network = networkRepository.findByName(transaction.getNetworkName());
    if (network == null) {
      network = new Network(transaction.getNetworkName());
    }
    transactionEntity.withNetwork(network);
    try {
      networkRepository.save(network);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Merchant withMerchant(Transaction tr) {
    Merchant merchant = merchantRepository.findByName(tr.getMerchant().getName());
    if (merchant == null) {
      merchant = new Merchant();
      merchant.setIdExt(tr.getMerchant().getId());
      merchant.setName(tr.getMerchant().getName());
    }
    try {
      merchantRepository.save(merchant);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return merchant;
  }

  private void saveSourceIp(Transaction tr, TransactionEntity transaction, Person person) {
    Click click = clickRepository.findByExtId(tr.getClick().getId());
    if (click == null) {
      click = new Click(tr.getClick().getId(), tr.getClick().getDate(), tr.getClick().getSource());
    }

    SourceIP sourceIp = sourceIPRepository.findByIp(tr.getClick().getIp());
    if (sourceIp == null) {
      sourceIp = new SourceIP(tr.getClick().getIp());
    }
    sourceIp.withClick(click);
    sourceIp.withPerson(person);
    sourceIp.withTransaction(transaction);
    try {
      sourceIPRepository.save(sourceIp);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void saveCid(Transaction tr, TransactionEntity transaction) {
    CID cid = cidRepository.findByCid(tr.getLastCid());
    if (cid == null) {
      cid = new CID();
    }
    cid.setCid(tr.getLastCid());
    cid.withTransaction(transaction);
    try {
      cidRepository.save(cid);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
