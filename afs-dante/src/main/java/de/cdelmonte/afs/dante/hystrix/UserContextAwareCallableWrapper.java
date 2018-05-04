package de.cdelmonte.afs.dante.hystrix;

import java.util.concurrent.Callable;
import de.cdelmonte.afs.dante.utils.UserContext;
import de.cdelmonte.afs.dante.utils.UserContextHolder;


public class UserContextAwareCallableWrapper<V> implements Callable<V> {
	private final Callable<V> delegate;
	private UserContext originalUserContext;

	public UserContextAwareCallableWrapper(Callable<V> delegate, UserContext userContext) {
		this.delegate = delegate;
		this.originalUserContext = userContext;
	}

	@Override
	public V call() throws Exception {
		Throwable error = null;
		try {
			UserContextHolder.setContext(originalUserContext);

			return delegate.call();
		} catch (Exception | Error e) {
			error = e;
			throw e;
		} finally {
			this.originalUserContext = null;
		}
	}
}
