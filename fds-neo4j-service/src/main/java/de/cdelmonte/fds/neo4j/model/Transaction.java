package de.cdelmonte.fds.neo4j.model;

import java.util.Date;

public class Transaction extends Mock {
  private Long id;
  private String networkName;
  private Date date;
  private Long amount;
  private Long commission;
  private Long userCommission;
  private String status;
  private Long userId;
  private Click click;
  private Merchant merchant;
  private Date createdAt;
  private Date updatedAt;
  private boolean imported;
  private Date lastImportedAt;
  private String lastCid;
  private boolean testData;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNetworkName() {
    return networkName;
  }

  public void setNetworkName(String networkName) {
    this.networkName = networkName;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public Long getCommission() {
    return commission;
  }

  public void setCommission(Long commission) {
    this.commission = commission;
  }

  public Long getUserCommission() {
    return userCommission;
  }

  public void setUserCommission(Long userCommission) {
    this.userCommission = userCommission;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Click getClick() {
    return click;
  }

  public void setClick(Click click) {
    this.click = click;
  }

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean isImported() {
    return imported;
  }

  public void setImported(boolean imported) {
    this.imported = imported;
  }

  public Date getLastImportedAt() {
    return lastImportedAt;
  }

  public void setLastImportedAt(Date lastImportedAt) {
    this.lastImportedAt = lastImportedAt;
  }

  public String getLastCid() {
    return lastCid;
  }

  public void setLastCid(String lastCid) {
    this.lastCid = lastCid;
  }

  public boolean isTestData() {
    return testData;
  }

  public void setTestData(boolean testData) {
    this.testData = testData;
  }
}
