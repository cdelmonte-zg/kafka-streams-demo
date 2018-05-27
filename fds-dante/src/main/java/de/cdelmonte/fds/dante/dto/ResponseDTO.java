package de.cdelmonte.fds.dante.dto;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.cdelmonte.fds.dante.entity.Transaction;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String testResponse = "Hello world!";
  private List<Transaction> transactions;

  public String getTestResponse() {
    return testResponse;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  @Override
  public String toString() {
    return "ResponseDTO [testResponse=" + testResponse + ", transactions=" + transactions + "]";
  }
}
