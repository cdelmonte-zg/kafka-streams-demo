package de.cdelmonte.fds.dante.hystrix;

import com.netflix.hystrix.HystrixCommand;
import de.cdelmonte.fds.dante.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@AutoConfigureAfter(UserContextHolder.class)
@ConditionalOnClass(HystrixCommand.class)
@ConditionalOnProperty(value = "spring.hystrix.usercontext.strategy.enabled", matchIfMissing = true)
public class HystrixContextAutoConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(HystrixContextAutoConfiguration.class);
	
	@Autowired
	UserContextHolder userContextHolder;
	
	@Primary
	@Bean
	HystrixContextAwareConcurrencyStrategy hystrixContextAwareConcurrencyStrategy() {
		logger.debug("Setting soncurrency strategy with User Context Holder {}", userContextHolder);
		
		return new HystrixContextAwareConcurrencyStrategy(userContextHolder);
	}
}