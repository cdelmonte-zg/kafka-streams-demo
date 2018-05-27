package de.cdelmonte.fds.dante.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import de.cdelmonte.fds.dante.config.ServiceConfig;
import de.cdelmonte.fds.dante.dto.ResponseDTO;
import de.cdelmonte.fds.dante.entity.Transaction;
import de.cdelmonte.fds.dante.entity.repository.TransactionRepository;

@Service
public class DanteService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DanteService.class);

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  ServiceConfig config;

  @HystrixCommand(fallbackMethod = "buildGeneralFallback", commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")})
  public ResponseDTO getFraudsStatus() {
    List<Transaction> transactions = transactionRepository.getAllTransactions();
    ResponseDTO response = new ResponseDTO();

    LOGGER.debug(transactions.toString());
    response.setTransactions(transactions);

    return response;
  }

  @SuppressWarnings("unused")
  private ResponseDTO buildGeneralFallback() {
    // TODO Auto-generated method stub
    return null;
  }
}
