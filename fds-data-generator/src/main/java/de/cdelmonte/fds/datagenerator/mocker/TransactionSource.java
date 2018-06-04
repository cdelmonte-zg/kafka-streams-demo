package de.cdelmonte.fds.datagenerator.mocker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum TransactionSource {
  UNKNOWN("unknown"), BROWSER("browser"), FRIEND("friend"), TOOLBAR("toolbar"), BOOKMARK(
      "bookmark"), TRAVEL_FINDER("travel_finder"), PARTNER("partner"), BROWSER_DEEPLINK(
          "browser_deeplink"), RFS_LINK("rfs_link"), APP("app"), MOBILE("mobile");

  private final String name;
  private static final List<TransactionSource> VALUES =
      Collections.unmodifiableList(Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  TransactionSource(String n) {
    name = n;
  }

  public String getName() {
    return name;
  }

  public static String getRandomSource() {
    return VALUES.get(RANDOM.nextInt(SIZE)).getName();
  }
}
