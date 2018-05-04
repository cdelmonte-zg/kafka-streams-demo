package de.cdelmonte.afs.dante.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserContextHolder {
	private static UserContext userContext = new UserContext();

	public static final UserContext getContext() {
		if (userContext == null) {
			userContext = createEmptyContext();
		}
		return userContext;
	}

	public static final void setContext(UserContext context) {
		Assert.notNull(context, "Only non-null UserContext instances are permitted");
		userContext = context;
	}

	public static final UserContext createEmptyContext() {
		return new UserContext();
	}
}
