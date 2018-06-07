package de.cdelmonte.fds.neo4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.cdelmonte.fds.neo4j.entity.Person;
import de.cdelmonte.fds.neo4j.entity.repository.PersonRepository;

@Service
public class RatingsGeneratorService {
  @Autowired
  PersonRepository personRepository;

  public void ratePersonRelations(Long userId, Person person) {
    int paypalInCommonWithHowManyPersons =
        personRepository.getPaypalInCommonWithHowManyPersons(userId);
    int adressInCommonWithHowManyPersons =
        personRepository.getAdressInCommonWithHowManyPersons(userId);
    int cidInCommonWithHowManyPersons = personRepository.getCidInCommonWithHowManyPersons(userId);
    int sourceIPInCommonWithHowManyPersons =
        personRepository.getSourceIPInCommonWithHowManyPersons(userId);
    int bankAccountInCommonWithHowManyPersons =
        personRepository.getBankAccountInCommonWithHowManyPersons(userId);
    int bitcoinAccountInCommonWithHowManyPersons =
        personRepository.getBitcoinAccountInCommonWithHowManyPersons(userId);

    if (paypalInCommonWithHowManyPersons > 0)
      person.setRelationsRatingFactor(0);

    if (adressInCommonWithHowManyPersons > 0)
      person.setRelationsRatingFactor(1);

    if (cidInCommonWithHowManyPersons > 0)
      person.setRelationsRatingFactor(2);

    if (sourceIPInCommonWithHowManyPersons > 0)
      person.setRelationsRatingFactor(3);

    if (bankAccountInCommonWithHowManyPersons > 0)
      person.setRelationsRatingFactor(4);

    if (bitcoinAccountInCommonWithHowManyPersons > 0)
      person.setRelationsRatingFactor(5);

    try {
      personRepository.save(person);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void ratePersonAttributes(Person person) {
    if (!person.getLastCountry().equals("DE"))
      person.setAttributesRatingFactor(0);

    if (!person.isEmailVerified())
      person.setAttributesRatingFactor(1);

    if (person.isPaymentsBlocked())
      person.setAttributesRatingFactor(2);

    if (person.isBlocked())
      person.setAttributesRatingFactor(3);

    if (person.isDoNotPay())
      person.setAttributesRatingFactor(4);

    if (person.getBalanceDenied() > 0.0)
      person.setAttributesRatingFactor(5);

    try {
      personRepository.save(person);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
