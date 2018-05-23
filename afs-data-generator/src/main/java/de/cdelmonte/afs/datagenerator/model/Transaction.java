package de.cdelmonte.afs.datagenerator.model;

import java.util.Date;

public class Transaction extends Mock {
  private Long id;
  private String networkName;
  private String networkTransactionId;
  private String networkStatus;
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

  public String getNetworkTransactionId() {
    return networkTransactionId;
  }

  public void setNetworkTransactionId(String networkTransactionId) {
    this.networkTransactionId = networkTransactionId;
  }

  public String getNetworkStatus() {
    return networkStatus;
  }

  public void setNetworkStatus(String networkStatus) {
    this.networkStatus = networkStatus;
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

  @Override
  public String toString() {
    return "Transaction [id=" + id + ", networkName=" + networkName + ", networkTransactionId="
        + networkTransactionId + ", networkStatus=" + networkStatus + ", date=" + date + ", amount="
        + amount + ", commission=" + commission + ", userCommission=" + userCommission + ", status="
        + status + ", userId=" + userId + ", click=" + click + ", merchant=" + merchant
        + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", imported=" + imported
        + ", lastImportedAt=" + lastImportedAt + ", lastCid=" + lastCid + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    result = prime * result + ((click == null) ? 0 : click.hashCode());
    result = prime * result + ((commission == null) ? 0 : commission.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + (imported ? 1231 : 1237);
    result = prime * result + ((lastCid == null) ? 0 : lastCid.hashCode());
    result = prime * result + ((lastImportedAt == null) ? 0 : lastImportedAt.hashCode());
    result = prime * result + ((merchant == null) ? 0 : merchant.hashCode());
    result = prime * result + ((networkName == null) ? 0 : networkName.hashCode());
    result = prime * result + ((networkStatus == null) ? 0 : networkStatus.hashCode());
    result =
        prime * result + ((networkTransactionId == null) ? 0 : networkTransactionId.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    result = prime * result + ((userCommission == null) ? 0 : userCommission.hashCode());
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Transaction other = (Transaction) obj;
    if (amount == null) {
      if (other.amount != null)
        return false;
    } else if (!amount.equals(other.amount))
      return false;
    if (click == null) {
      if (other.click != null)
        return false;
    } else if (!click.equals(other.click))
      return false;
    if (commission == null) {
      if (other.commission != null)
        return false;
    } else if (!commission.equals(other.commission))
      return false;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (imported != other.imported)
      return false;
    if (lastCid == null) {
      if (other.lastCid != null)
        return false;
    } else if (!lastCid.equals(other.lastCid))
      return false;
    if (lastImportedAt == null) {
      if (other.lastImportedAt != null)
        return false;
    } else if (!lastImportedAt.equals(other.lastImportedAt))
      return false;
    if (merchant == null) {
      if (other.merchant != null)
        return false;
    } else if (!merchant.equals(other.merchant))
      return false;
    if (networkName == null) {
      if (other.networkName != null)
        return false;
    } else if (!networkName.equals(other.networkName))
      return false;
    if (networkStatus == null) {
      if (other.networkStatus != null)
        return false;
    } else if (!networkStatus.equals(other.networkStatus))
      return false;
    if (networkTransactionId == null) {
      if (other.networkTransactionId != null)
        return false;
    } else if (!networkTransactionId.equals(other.networkTransactionId))
      return false;
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    if (updatedAt == null) {
      if (other.updatedAt != null)
        return false;
    } else if (!updatedAt.equals(other.updatedAt))
      return false;
    if (userCommission == null) {
      if (other.userCommission != null)
        return false;
    } else if (!userCommission.equals(other.userCommission))
      return false;
    if (userId == null) {
      if (other.userId != null)
        return false;
    } else if (!userId.equals(other.userId))
      return false;
    return true;
  }
}
