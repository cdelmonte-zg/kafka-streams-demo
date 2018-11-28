package de.cdelmonte.fds.datagenerator.orchestrator.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import net.andreinc.mockneat.MockNeat;


public class Click implements EventModel, Serializable {
  private long id;
  private String clickedFromIp;
  private Date clickedAt;
  private ClickSource source;
  private String network;
  private long referrerId;
  private long merchantId;
  private long userId;
  private String sessionId;
  private List<Transaction> transactions = new ArrayList<>();

  public Click() {}

  private Click(Builder builder) {
    id = builder.getId();
    clickedFromIp = builder.getClickedFromIp();
    clickedAt = builder.getClickedAt();
    source = builder.getSource();
    network = builder.getNetwork();
    referrerId = builder.getReferrer();
    merchantId = builder.getMerchant();
    userId = builder.getUserId();
    sessionId = builder.getSessionId();
  }

  public Click(Filter filter) {
    id = filter.getId();
    clickedFromIp = filter.getClickedFromIp();
    clickedAt = filter.getClickedAt();
    source = filter.getSource();
    network = filter.getNetwork();
    referrerId = filter.getReferrerId();
    merchantId = filter.getMerchantId();
    userId = filter.getUserId();
    sessionId = filter.getSessionId();
  }

  public long getId() {
    return id;
  }

  public String getClickedFromIp() {
    return clickedFromIp;
  }

  public Date getClickedAt() {
    return clickedAt;
  }

  public ClickSource getSource() {
    return source;
  }

  public String getNetwork() {
    return network;
  }

  public long getReferrerId() {
    return referrerId;
  }

  public long getMerchantId() {
    return merchantId;
  }

  public long getUserId() {
    return userId;
  }

  public String getSessionId() {
    return sessionId;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void addTransaction(Transaction transaction) {
    this.transactions.add(transaction);
  }

  public void removeTransaction(Transaction transaction) {
    this.transactions.remove(transaction);
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public static class Builder {
    private long id;
    private String clickedFromIp;
    private Date clickedAt;
    private ClickSource source;
    private String network;
    private long referrerId;
    private long merchant;
    private Actor userId;
    private Session session;
    private MockNeat mock;

    public Builder(MockNeat mock, Actor user, Session session, ClickSource source, long merchant) {
      this.mock = mock;

      id = mock.longs().range(1, Long.MAX_VALUE).val();
      clickedFromIp = mock.ipv4s().val();
      clickedAt =
          mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate().val();
      this.source = source;
      network = "";
      referrerId = 1;
      this.merchant = merchant;
      this.userId = user;
      this.session = session;
    }

    public Click build() {
      return new Click(this);
    }

    public long getId() {
      return id;
    }

    public long getUserId() {
      return userId.getActorId();
    }

    public String getSessionId() {
      return session.getSessionId();
    }

    public String getClickedFromIp() {
      return clickedFromIp;
    }

    public Date getClickedAt() {
      return clickedAt;
    }

    public String getNetwork() {
      return network;
    }

    public long getReferrer() {
      return referrerId;
    }

    public long getMerchant() {
      return merchant;
    }

    public Actor getUser() {
      return userId;
    }

    public ClickSource getSource() {
      return source;
    }
  }

  public static class Filter {
    private long id;
    private String clickedFromIp;
    private Date clickedAt;
    private ClickSource source;
    private String network;
    private long referrerId;
    private long merchantId;
    private long userId;
    private String sessionId;

    public Filter(Click builder) {
      id = builder.id;
      clickedFromIp = builder.clickedFromIp;
      clickedAt = builder.clickedAt;
      source = builder.source;
      network = builder.network;
      referrerId = builder.referrerId;
      merchantId = builder.merchantId;
      userId = builder.userId;
      sessionId = builder.sessionId;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

    public String getClickedFromIp() {
      return clickedFromIp;
    }

    public void setClickedFromIp(String clickedFromIp) {
      this.clickedFromIp = clickedFromIp;
    }

    public Date getClickedAt() {
      return clickedAt;
    }

    public void setClickedAt(Date clickedAt) {
      this.clickedAt = clickedAt;
    }

    public ClickSource getSource() {
      return source;
    }

    public void setSource(ClickSource source) {
      this.source = source;
    }

    public String getNetwork() {
      return network;
    }

    public void setNetwork(String network) {
      this.network = network;
    }

    public long getReferrerId() {
      return referrerId;
    }

    public void setReferrerId(long referrerId) {
      this.referrerId = referrerId;
    }

    public long getMerchantId() {
      return merchantId;
    }

    public void setMerchantId(long merchantId) {
      this.merchantId = merchantId;
    }

    public long getUserId() {
      return userId;
    }

    public void setUserId(long userId) {
      this.userId = userId;
    }

    public String getSessionId() {
      return sessionId;
    }

    public void setSessionId(String sessionId) {
      this.sessionId = sessionId;
    }

    public Click filter() {
      Click click = new Click(this);

      return click;
    }
  }

  public Click filter() {
    return new Filter(this).filter();
  }

  @Override
  public String toString() {
    return "Click [id=" + id + ", clickedFromIp=" + clickedFromIp + ", clickedAt=" + clickedAt
        + ", source=" + source + ", network=" + network + ", referrerId=" + referrerId
        + ", merchantId=" + merchantId + ", userId=" + userId + ", sessionId=" + sessionId
        + ", transactions=" + transactions + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((clickedAt == null) ? 0 : clickedAt.hashCode());
    result = prime * result + ((clickedFromIp == null) ? 0 : clickedFromIp.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + (int) (merchantId ^ (merchantId >>> 32));
    result = prime * result + ((network == null) ? 0 : network.hashCode());
    result = prime * result + (int) (referrerId ^ (referrerId >>> 32));
    result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
    result = prime * result + ((source == null) ? 0 : source.hashCode());
    result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
    result = prime * result + (int) (userId ^ (userId >>> 32));
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
    Click other = (Click) obj;
    if (clickedAt == null) {
      if (other.clickedAt != null)
        return false;
    } else if (!clickedAt.equals(other.clickedAt))
      return false;
    if (clickedFromIp == null) {
      if (other.clickedFromIp != null)
        return false;
    } else if (!clickedFromIp.equals(other.clickedFromIp))
      return false;
    if (id != other.id)
      return false;
    if (merchantId != other.merchantId)
      return false;
    if (network == null) {
      if (other.network != null)
        return false;
    } else if (!network.equals(other.network))
      return false;
    if (referrerId != other.referrerId)
      return false;
    if (sessionId == null) {
      if (other.sessionId != null)
        return false;
    } else if (!sessionId.equals(other.sessionId))
      return false;
    if (source != other.source)
      return false;
    if (transactions == null) {
      if (other.transactions != null)
        return false;
    } else if (!transactions.equals(other.transactions))
      return false;
    if (userId != other.userId)
      return false;
    return true;
  }
}
