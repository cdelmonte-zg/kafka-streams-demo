package de.cdelmonte.afs.dante.utils;

public class UserContext {
	public static final String CORRELATION_ID = "mw-correlation-id";
	public static final String AUTH_TOKEN = "Authorization";
	public static final String USER_ID = "mw-user-id";

	private static String correlationId = new String();
	private static String authToken = new String();
	private static String userId = new String();

	public static String getCorrelationId() {
		return correlationId;
	}

	public static void setCorrelationId(String cid) {
		correlationId = cid;
	}

	public static String getAuthToken() {
		return authToken;
	}

	public static void setAuthToken(String aToken) {
		authToken = aToken;
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String aUser) {
		userId = aUser;
	}
}
