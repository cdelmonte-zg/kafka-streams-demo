package de.cdelmonte.fds.neo4j.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.cdelmonte.fds.neo4j.entity.Person;
import de.cdelmonte.fds.neo4j.entity.repository.PersonRepository;
import de.cdelmonte.fds.neo4j.events.listener.KafkaConsumer;

@Service
public class RatingsGeneratorService {
  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

  @Autowired
  PersonRepository personRepository;

  public void setPersonRatingFactors(Long userId, Person person) {
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

    LOGGER.debug("paypalInCommonWithHowManyPersons: " + paypalInCommonWithHowManyPersons);
    LOGGER.debug("adressInCommonWithHowManyPersons: " + adressInCommonWithHowManyPersons);
    LOGGER.debug("cidInCommonWithHowManyPersons: " + cidInCommonWithHowManyPersons);
    LOGGER.debug("sourceIPInCommonWithHowManyPersons: " + sourceIPInCommonWithHowManyPersons);
    LOGGER.debug("bankAccountInCommonWithHowManyPersons: " + bankAccountInCommonWithHowManyPersons);
    LOGGER.debug("paypalInCommonWithHowManyPersons: " + bitcoinAccountInCommonWithHowManyPersons);

    if (paypalInCommonWithHowManyPersons > 0)
      person.setRatingFactor(0);

    if (adressInCommonWithHowManyPersons > 0)
      person.setRatingFactor(1);

    if (cidInCommonWithHowManyPersons > 0)
      person.setRatingFactor(2);

    if (sourceIPInCommonWithHowManyPersons > 0)
      person.setRatingFactor(3);

    if (bankAccountInCommonWithHowManyPersons > 0)
      person.setRatingFactor(4);

    if (bitcoinAccountInCommonWithHowManyPersons > 0)
      person.setRatingFactor(5);

    LOGGER.debug("RatingFactor from person: " + person.getRatingFactors().toString());

    try {
      personRepository.save(person);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
