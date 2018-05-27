package de.cdelmonte.fds.datagenerator.container;

import java.util.List;
import de.cdelmonte.fds.datagenerator.model.Transaction;
import de.cdelmonte.fds.datagenerator.model.User;

public class DataContainer {
  private static List<User> users;
  private static List<Transaction> transactions;

  public static List<User> getUsers() {
    return users;
  }

  public static void setUsers(List<User> users) {
    DataContainer.users = users;
  }

  public static List<Transaction> getTransactions() {
    return transactions;
  }

  public static void setTransactions(List<Transaction> transactions) {
    DataContainer.transactions = transactions;
  }
}
