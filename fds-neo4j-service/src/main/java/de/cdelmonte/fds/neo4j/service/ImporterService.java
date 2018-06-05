package de.cdelmonte.fds.neo4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.fds.neo4j.entity.AddressEntity;
import de.cdelmonte.fds.neo4j.entity.BankAccountEntity;
import de.cdelmonte.fds.neo4j.entity.BitcoinAccountEntity;
import de.cdelmonte.fds.neo4j.entity.CID;
import de.cdelmonte.fds.neo4j.entity.Click;
import de.cdelmonte.fds.neo4j.entity.Merchant;
import de.cdelmonte.fds.neo4j.entity.Network;
import de.cdelmonte.fds.neo4j.entity.PaypalAccountEntity;
import de.cdelmonte.fds.neo4j.entity.Person;
import de.cdelmonte.fds.neo4j.entity.SourceIP;
import de.cdelmonte.fds.neo4j.entity.TransactionEntity;
import de.cdelmonte.fds.neo4j.entity.repository.AddressRepository;
import de.cdelmonte.fds.neo4j.entity.repository.BankAccountRepository;
import de.cdelmonte.fds.neo4j.entity.repository.BitcoinAccountRepository;
import de.cdelmonte.fds.neo4j.entity.repository.CIDRepository;
import de.cdelmonte.fds.neo4j.entity.repository.ClickRepository;
import de.cdelmonte.fds.neo4j.entity.repository.MerchantRepository;
import de.cdelmonte.fds.neo4j.entity.repository.NetworkRepository;
import de.cdelmonte.fds.neo4j.entity.repository.PaypalAccountRepository;
import de.cdelmonte.fds.neo4j.entity.repository.PersonRepository;
import de.cdelmonte.fds.neo4j.entity.repository.SourceIPRepository;
import de.cdelmonte.fds.neo4j.entity.repository.TransactionRepository;
import de.cdelmonte.fds.neo4j.model.Transaction;
import de.cdelmonte.fds.neo4j.model.User;

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

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  BankAccountRepository bankAccountRepository;

  @Autowired
  PaypalAccountRepository paypalAccountRepository;

  @Autowired
  BitcoinAccountRepository bitcoinAccountRepository;

  public void importTransactions(String payload) {
    Gson gson = new Gson();

    Transaction tr = gson.fromJson(payload, Transaction.class);

    TransactionEntity transaction = new TransactionEntity();

    transaction.setIdExt(tr.getId());
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
    if (person != null) {
      person.withMerchant(merchant);
      person.withTransaction(transaction);
    }

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
      personRepository.save(person);
    } catch (Exception e) {
      e.getMessage();
    }

    try {
      merchantRepository.save(merchant);
      transactionRepository.save(transaction);
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

    Person person = personRepository.findByIdExt(user.getId());
    if (person == null)
      person = new Person();

    person.setIdExt(user.getId());
    person.setEmail(user.getEmail());
    person.setUsername(user.getUsername());
    person.setName(user.getName());
    person.setBirthdate(user.getBirthdate());
    person.setRegistrationDate(user.getRegistrationDate());
    person.setLastLoginDate(user.getLastLoginDate());
    person.setLastCountry(user.getLastCountry());
    person.setLastIp(user.getLastIp());
    person.setLastCid(user.getLastCid());
    person.setLanguages(user.getLanguages());
    person.setEmailVerified(user.isEmailVerified());
    person.setPaymentsBlocked(user.isPaymentsBlocked());
    person.setBlocked(user.isBlocked());
    person.setDoNotPay(user.isDoNotPay());
    person.setNumberOfTransactions(user.getNumberOfTransactions());
    person.setBalancePending(user.getBalance().getPending());
    person.setBalancePaid(user.getBalance().getPaid());
    person.setBalanceDenied(user.getBalance().getDenied());
    person.setBalanceReceived(user.getBalance().getReceived());

    try {
      personRepository.save(person);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      saveAddress(user, person);
      saveBankAccount(user, person);
      savePaypalAccount(user, person);
      saveBitcoinAccount(user, person);
    }
  }

  private void saveBankAccount(User user, Person person) {
    BankAccountEntity bankAccount =
        bankAccountRepository.findByIBAN(user.getBankAccount().getIBAN());
    if (bankAccount == null)
      bankAccount = new BankAccountEntity();

    bankAccount.setAccountHolder(user.getBankAccount().getAccountHolder());
    bankAccount.setIBAN(user.getBankAccount().getIBAN());
    bankAccount.setBIC(user.getBankAccount().getBIC());
    bankAccount.withPerson(person);

    try {
      bankAccountRepository.save(bankAccount);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void savePaypalAccount(User user, Person person) {
    PaypalAccountEntity paypalAccount =
        paypalAccountRepository.findByAddress(user.getPaypalAccount().getAddress());
    if (paypalAccount == null)
      paypalAccount = new PaypalAccountEntity();

    paypalAccount.setAccountHolder(user.getPaypalAccount().getAccountHolder());
    paypalAccount.setAddress(user.getPaypalAccount().getAddress());
    paypalAccount.withPerson(person);

    try {
      paypalAccountRepository.save(paypalAccount);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void saveBitcoinAccount(User user, Person person) {
    BitcoinAccountEntity bitcoinAccount =
        bitcoinAccountRepository.findByAddress(user.getBitcoinAccount().getAddress());
    if (bitcoinAccount == null)
      bitcoinAccount = new BitcoinAccountEntity();

    bitcoinAccount.setAddress(user.getPaypalAccount().getAddress());
    bitcoinAccount.withPerson(person);

    try {
      bitcoinAccountRepository.save(bitcoinAccount);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void saveAddress(User user, Person person) {
    AddressEntity address = new AddressEntity();
    address.setCountry(user.getAddress().getCountry());
    address.setState(user.getAddress().getState());
    address.setCity(user.getAddress().getCity());
    address.setZipCode(user.getAddress().getZipCode());
    address.setStreetAddress(user.getAddress().getStreetAddress());
    address.setStreetNumber(user.getAddress().getStreetNumber());
    address.withPerson(person);

    try {
      addressRepository.save(address);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
