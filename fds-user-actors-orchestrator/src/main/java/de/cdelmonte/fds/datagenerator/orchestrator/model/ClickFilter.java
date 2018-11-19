package de.cdelmonte.fds.datagenerator.orchestrator.model;

import java.io.Serializable;
import java.util.Date;


public class ClickFilter implements Serializable {
  private long id;
  private String clickedFromIp;
  private Date clickedAt;
  private ClickSource source;
  private String network;
  private long referrerId;
  private long merchantId;
  private long userId;
  private String sessionId;


  public ClickFilter(Click builder) {
    id = builder.getId();
    clickedFromIp = builder.getClickedFromIp();
    clickedAt = builder.getClickedAt();
    source = builder.getSource();
    network = builder.getNetwork();
    referrerId = builder.getReferrerId();
    merchantId = builder.getMerchantId();
    userId = builder.getUserId();
    sessionId = builder.getSessionId();
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
}
