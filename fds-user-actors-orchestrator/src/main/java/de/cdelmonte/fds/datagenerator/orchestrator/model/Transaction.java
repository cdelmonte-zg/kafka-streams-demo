package de.cdelmonte.fds.datagenerator.orchestrator.model;

import java.util.Date;

import org.testcontainers.shaded.com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.types.enums.MarkovChainType;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction implements EventModel {
  private long id;
  private int subTransactionId;
  private long clickId;
  private Date date;
  private Date created;

  private long amount;
  private long userCommission;
  private TransactionStatus status;
  private long enquiryId;
  private boolean review;
  private String warnings;

  private Transaction() {

  }

  private Transaction(Builder builder) {
    id = builder.getId();
    subTransactionId = builder.getSubTransactionId();
    clickId = builder.getClickId();
    date = builder.getDate();
    created = builder.getCreated();
    amount = builder.getAmount();
    userCommission = builder.getUserCommission();
    status = builder.getStatus();
    enquiryId = builder.getEnquiryId();
    review = builder.isReview();
    warnings = builder.getWarnings();
  }

  public Transaction(Filter filter) {
    id = filter.getId();
    subTransactionId = filter.getSubTransactionId();
    clickId = filter.getClickId();
    date = filter.getDate();
    created = filter.getCreated();
    amount = filter.getAmount();
    userCommission = filter.getUserCommission();
    status = filter.getStatus();
    enquiryId = filter.getEnquiryId();
    review = filter.isReview();
    warnings = filter.getWarnings();
  }

  @Override
  public Transaction filter() {
    return new Filter(this).filter();
  }

  public static class Builder {
    private long id;
    private int subTransactionId;
    private Click click;
    private Date date;
    private Date created;
    private long amount;
    private long userCommission;
    private TransactionStatus status;
    private long enquiryId;
    private boolean review;
    private String warnings;

    public Builder(MockNeat mock, int subTransactionId, Click click) {
      id = mock.longs().range(1, Long.MAX_VALUE).val();
      this.subTransactionId = subTransactionId;
      this.click = click;
      date = new Date();
      created = new Date();
      amount = mock.longs().range(1000, 999999).val();
      userCommission = amount - (amount * (mock.ints().range(1, 50).val() / 100));
      status = TransactionStatus.values()[mock.ints().range(1, 9).val()];
      enquiryId = mock.longs().range(1, Long.MAX_VALUE).val();
      review = mock.bools().val();
      warnings = mock.markovs().size(32).type(MarkovChainType.LOREM_IPSUM).val();
    }

    public long getClickId() {
      return click.getId();
    }

    public Transaction build() {
      return new Transaction(this);
    }

    public long getId() {
      return id;
    }

    public int getSubTransactionId() {
      return subTransactionId;
    }

    public Click getClick() {
      return click;
    }

    public Date getDate() {
      return date;
    }

    public Date getCreated() {
      return created;
    }

    public long getAmount() {
      return amount;
    }

    public long getUserCommission() {
      return userCommission;
    }

    public TransactionStatus getStatus() {
      return status;
    }

    public long getEnquiryId() {
      return enquiryId;
    }

    public boolean isReview() {
      return review;
    }

    public String getWarnings() {
      return warnings;
    }
  }

  public static class Filter {
    private long id;
    private int subTransactionId;
    private long clickId;
    private Date date;
    private Date created;
    private long amount;
    private long userCommission;
    private TransactionStatus status;
    private long enquiryId;
    private boolean review;
    private String warnings;


    public Filter(Transaction builder) {
      id = builder.getId();
      subTransactionId = builder.getSubTransactionId();
      clickId = builder.getClickId();
      date = builder.getDate();
      created = builder.getCreated();
      amount = builder.getAmount();
      userCommission = builder.getUserCommission();
      status = builder.getStatus();
      enquiryId = builder.getEnquiryId();
      review = builder.isReview();
      warnings = builder.getWarnings();
    }

    public long getId() {
      return id;
    }

    public int getSubTransactionId() {
      return subTransactionId;
    }

    public long getClickId() {
      return clickId;
    }

    public Date getDate() {
      return date;
    }

    public Date getCreated() {
      return created;
    }

    public long getAmount() {
      return amount;
    }

    public long getUserCommission() {
      return userCommission;
    }

    public TransactionStatus getStatus() {
      return status;
    }

    public long getEnquiryId() {
      return enquiryId;
    }

    public boolean isReview() {
      return review;
    }

    public String getWarnings() {
      return warnings;
    }

    public Transaction filter() {
      Transaction trasaction = new Transaction(this);
      return trasaction;
    }
  }


  public long getId() {
    return id;
  }

  public int getSubTransactionId() {
    return subTransactionId;
  }

  public long getClickId() {
    return clickId;
  }

  public Date getDate() {
    return date;
  }

  public Date getCreated() {
    return created;
  }

  public long getAmount() {
    return amount;
  }

  public long getUserCommission() {
    return userCommission;
  }

  public TransactionStatus getStatus() {
    return status;
  }

  public long getEnquiryId() {
    return enquiryId;
  }

  public boolean isReview() {
    return review;
  }

  public String getWarnings() {
    return warnings;
  }
}
