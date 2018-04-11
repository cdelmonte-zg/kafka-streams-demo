package de.cdelmonte.afs.neo4j.entity;

import java.util.Date;

public class Payload {
  private String firstName;
  private String lastName;
  private String customerId;
  private String creditCardNumber;
  private String itemPurchased;
  private String department;
  private String employeeId;
  private int quantity;
  private double price;
  private Date purchaseDate;
  private String zipCode;
  private String storeId;


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public String getItemPurchased() {
    return itemPurchased;
  }

  public void setItemPurchased(String itemPurchased) {
    this.itemPurchased = itemPurchased;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
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

  @Override
  public String toString() {
    return "Payload [firstName=" + firstName + ", lastName=" + lastName + ", customerId="
        + customerId + ", creditCardNumber=" + creditCardNumber + ", itemPurchased=" + itemPurchased
        + ", department=" + department + ", employeeId=" + employeeId + ", quantity=" + quantity
        + ", price=" + price + ", zipCode=" + zipCode + ", storeId=" + storeId + "]";
  }
}
