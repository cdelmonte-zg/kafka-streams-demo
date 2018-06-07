package de.cdelmonte.fds.neo4j.entity;

import java.util.BitSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class TransactionEntity {

  @Id
  @GeneratedValue
  private Long id;
  private Long idExt;
  private Date date;
  private Long amount;
  private Long commission;
  private Long userCommission;
  private String status;
  private Date createdAt;
  private Date updatedAt;
  private boolean imported;
  private Date lastImportedAt;
  private boolean testData;
  private BitSet ratingFactors = new BitSet(3);

  @Relationship(type = "TRANSACTION_WITH_NETWORK", direction = Relationship.UNDIRECTED)
  private Set<Network> network;

  public TransactionEntity() {};

  public Long getIdExt() {
    return idExt;
  }

  public void setIdExt(Long idExt) {
    this.idExt = idExt;
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

  public void withNetwork(Network network) {
    if (this.network == null) {
      this.network = new HashSet<>();
    }
    this.network.add(network);
  }

  public boolean isTestData() {
    return testData;
  }

  public void setTestData(boolean testData) {
    this.testData = testData;
  }

  public BitSet getRatingFactors() {
    return ratingFactors;
  }

  public void setRatingFactors(BitSet ratingFactors) {
    this.ratingFactors = ratingFactors;
  }
}
