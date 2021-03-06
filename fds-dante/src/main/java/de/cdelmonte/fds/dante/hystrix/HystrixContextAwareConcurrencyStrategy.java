package de.cdelmonte.fds.dante.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableLifecycle;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;
import de.cdelmonte.fds.dante.utils.UserContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HystrixContextAwareConcurrencyStrategy extends HystrixConcurrencyStrategy {

	private static final String HYSTRIX_COMPONENT = "hystrix";
	private static final Log log = LogFactory.getLog(HystrixContextAwareConcurrencyStrategy.class);

	private final UserContextHolder contextHolder;
	private HystrixConcurrencyStrategy delegate;

	public HystrixContextAwareConcurrencyStrategy(UserContextHolder contextHolder) {
		this.contextHolder = contextHolder;

		try {
			this.delegate = HystrixPlugins.getInstance().getConcurrencyStrategy();
			if (this.delegate instanceof HystrixContextAwareConcurrencyStrategy) {
				// Welcome to singleton hell...
				return;
			}
			HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();
			HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
			HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
			HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
			logCurrentStateOfHysrixPlugins(eventNotifier, metricsPublisher, propertiesStrategy);
			HystrixPlugins.reset();
			HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
			HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
			HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
			HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
			HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
		} catch (Exception e) {
			log.error("Failed to register User COntext Hystrix Concurrency Strategy", e);
		}
	}

	private void logCurrentStateOfHysrixPlugins(HystrixEventNotifier eventNotifier,
			HystrixMetricsPublisher metricsPublisher, HystrixPropertiesStrategy propertiesStrategy) {
		if (log.isDebugEnabled()) {
			log.debug("Current Hystrix plugins configuration is [" + "concurrencyStrategy [" + this.delegate + "],"
					+ "eventNotifier [" + eventNotifier + "]," + "metricPublisher [" + metricsPublisher + "],"
					+ "propertiesStrategy [" + propertiesStrategy + "]," + "]");
			log.debug("Registering User Context Hystrix Concurrency Strategy.");
		}
	}

	@Override
	public <T> Callable<T> wrapCallable(Callable<T> callable) {
		if (callable instanceof UserContextAwareCallableWrapper) {
			return callable;
		}

		Callable<T> wrappedCallable = this.delegate != null ? this.delegate.wrapCallable(callable) : callable;
		if (wrappedCallable instanceof UserContextAwareCallableWrapper) {
			return wrappedCallable;
		}

		return new UserContextAwareCallableWrapper<>(wrappedCallable, this.contextHolder.getContext());
	}

	@Override
	public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixProperty<Integer> corePoolSize,
			HystrixProperty<Integer> maximumPoolSize, HystrixProperty<Integer> keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		return this.delegate.getThreadPool(threadPoolKey, corePoolSize, maximumPoolSize, keepAliveTime, unit,
				workQueue);
	}

	@Override
	public ThreadPoolExecutor getThreadPool(HystrixThreadPoolKey threadPoolKey,
			HystrixThreadPoolProperties threadPoolProperties) {
		return this.delegate.getThreadPool(threadPoolKey, threadPoolProperties);
	}

	@Override
	public BlockingQueue<Runnable> getBlockingQueue(int maxQueueSize) {
		return this.delegate.getBlockingQueue(maxQueueSize);
	}

	@Override
	public <T> HystrixRequestVariable<T> getRequestVariable(HystrixRequestVariableLifecycle<T> rv) {
		return this.delegate.getRequestVariable(rv);
	}
}
