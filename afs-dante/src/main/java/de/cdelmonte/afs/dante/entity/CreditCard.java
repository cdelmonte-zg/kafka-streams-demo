package de.cdelmonte.afs.dante.entity;

import java.util.Date;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class CreditCard {

  @Id
  @GeneratedValue
  public Long id;
  private String creditCardNumber;
  private Date expirationDate;

  public CreditCard() {}

  public CreditCard(String creditCardNumber, Date expirationDate) {
    this.creditCardNumber = creditCardNumber;
    this.expirationDate = expirationDate;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }



}
