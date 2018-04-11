package de.cdelmonte.afs.neo4j.entity;

import java.util.Date;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Transaction {

  @Id
  @GeneratedValue
  private Long id;

  private String itemPurchased;
  private int quantity;
  private double price;
  private Date purchaseDate;
  private String zipCode;
  private String storeId;

  public Transaction() {
    // Empty constructor required as of Neo4j API 2.0.5
  }

  public Transaction(String itemPurchased, int quantity, double price, Date purchaseDate,
      String zipCode, String storeId) {
    this.itemPurchased = itemPurchased;
    this.quantity = quantity;
    this.price = price;
    this.purchaseDate = purchaseDate;
    this.zipCode = zipCode;
    this.storeId = storeId;
  }

  public String getItemPurchased() {
    return itemPurchased;
  }

  public void setItemPurchased(String itemPurchased) {
    this.itemPurchased = itemPurchased;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(Date purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }
}
