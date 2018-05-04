package de.cdelmonte.afs.dante;

import java.util.Collections;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import de.cdelmonte.afs.dante.utils.UserContextInterceptor;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableNeo4jRepositories
public class Application {
  @Primary
  @Bean
  @LoadBalanced
  public RestTemplate getRestTemplate() {
    RestTemplate template = new RestTemplate();
    List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
    if (interceptors == null) {
      template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
    } else {
      interceptors.add(new UserContextInterceptor());
      template.setInterceptors(interceptors);
    }

    return template;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
