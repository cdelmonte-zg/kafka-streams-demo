package de.cdelmonte.fds.neo4j.service.importer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.fds.neo4j.entity.AddressEntity;
import de.cdelmonte.fds.neo4j.entity.BankAccountEntity;
import de.cdelmonte.fds.neo4j.entity.BitcoinAccountEntity;
import de.cdelmonte.fds.neo4j.entity.CID;
import de.cdelmonte.fds.neo4j.entity.PaypalAccountEntity;
import de.cdelmonte.fds.neo4j.entity.Person;
import de.cdelmonte.fds.neo4j.entity.repository.AddressRepository;
import de.cdelmonte.fds.neo4j.entity.repository.BankAccountRepository;
import de.cdelmonte.fds.neo4j.entity.repository.BitcoinAccountRepository;
import de.cdelmonte.fds.neo4j.entity.repository.CIDRepository;
import de.cdelmonte.fds.neo4j.entity.repository.PaypalAccountRepository;
import de.cdelmonte.fds.neo4j.entity.repository.PersonRepository;
import de.cdelmonte.fds.neo4j.model.User;
import de.cdelmonte.fds.neo4j.service.RatingsGeneratorService;

@Service
public class UserImporterService {

  @Autowired
  RatingsGeneratorService ratingsGeneratorService;

  @Autowired
  PersonRepository personRepository;

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

  private RelatedEntityManager relatedEntityManager;

  UserImporterService() {
    relatedEntityManager = new RelatedEntityManager();
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

    person.setTestData(user.isTestData());
    if (user.isTestData())
      person.setFraudScore(user.getFraudScore());

    try {
      personRepository.save(person);
      relatedEntityManager.saveCid(user, person);
      relatedEntityManager.saveAddress(user, person);
      relatedEntityManager.saveBankAccount(user, person);
      relatedEntityManager.saveBitcoinAccount(user, person);
      relatedEntityManager.savePaypalAccount(user, person);
      ratingsGeneratorService.ratePersonRelations(user.getId(), person);
      ratingsGeneratorService.ratePersonAttributes(person);
    } catch (

    Exception e) {
      e.getMessage();
      e.printStackTrace();
    }
  }


  private class RelatedEntityManager {
    private void saveBankAccount(User user, Person person) throws Exception {
      BankAccountEntity bankAccount =
          bankAccountRepository.findByIban(user.getBankAccount().getIban());

      if (bankAccount == null) {
        bankAccount = new BankAccountEntity();
      }

      bankAccount.setAccountHolder(user.getBankAccount().getAccountHolder());
      bankAccount.setIban(user.getBankAccount().getIban());
      bankAccount.setBic(user.getBankAccount().getBic());
      bankAccount.withPerson(person);

      try {
        bankAccountRepository.save(bankAccount);
      } catch (Exception e) {
        throw new Exception("Couldn't save the bank account related to user id: " + user.getId());
      }
    }

    private void savePaypalAccount(User user, Person person) throws Exception {
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
        throw new Exception("Couldn't save the paypal account related to user id: " + user.getId());
      }
    }

    private void saveBitcoinAccount(User user, Person person) throws Exception {
      BitcoinAccountEntity bitcoinAccount =
          bitcoinAccountRepository.findByAddress(user.getBitcoinAccount().getAddress());
      if (bitcoinAccount == null)
        bitcoinAccount = new BitcoinAccountEntity();

      bitcoinAccount.setAddress(user.getBitcoinAccount().getAddress());
      bitcoinAccount.withPerson(person);

      try {
        bitcoinAccountRepository.save(bitcoinAccount);
      } catch (Exception e) {
        throw new Exception(
            "Couldn't save the bitcoin account related to user id: " + user.getId());
      }
    }

    private void saveCid(User user, Person person) throws Exception {
      CID cid = cidRepository.findByCid(user.getLastCid());
      if (cid == null) {
        cid = new CID();
      }
      cid.setCid(user.getLastCid());
      cid.withPerson(person);

      try {
        cidRepository.save(cid);
      } catch (Exception e) {
        throw new Exception("Couldn't save the cid related to user id: " + user.getId());
      }
    }

    private void saveAddress(User user, Person person) throws Exception {
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
        throw new Exception("Couldn't save the address related to user id: " + user.getId());
      }
    }
  }
}
