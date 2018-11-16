package de.cdelmonte.fds.datagenerator.orchestrator.model;

import java.time.LocalDate;
import java.util.Date;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import net.andreinc.mockneat.MockNeat;


public class Click {
  private long id;
  private String clickedFromIp;
  private Date clickedAt;
  private ClickSource source;
  private String network;
  private long referrerId;
  private long merchantId;
  private long userId;
  private String sessionId;

  public Click(Builder builder) {
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
}
