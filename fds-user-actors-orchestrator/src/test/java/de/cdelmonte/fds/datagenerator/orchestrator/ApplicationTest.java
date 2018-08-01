package de.cdelmonte.fds.datagenerator.orchestrator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.DynamicContainer.*;
import static org.junit.jupiter.api.DynamicTest.*;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.Duration;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;


class ApplicationTest {
  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {}

  @AfterEach
  void tearDown() throws Exception {}

  @Test
  void testStandardAssertions() throws Exception {
    assertTrue("".isEmpty(), "Should be empty.");
    assertTrue("".isEmpty(), () -> evaluateFailureMessage());
  }

  private String evaluateFailureMessage() {
    return "Evaluated Message: " + "Should be empty.";
  }

  @Test
  void testException() throws Exception {
    Exception exception =
        assertThrows(StringIndexOutOfBoundsException.class, () -> printSubstring(-1));

    assertEquals(exception.getMessage(), "String index out of range: -1");
  }

  private void printSubstring(int a) {
    System.out.println("".substring(a));
  }

  // useful methods to validate some preconditions
  @EnabledOnOs(OS.MAC) // also the annotation can set some assumptions
  @Test
  @Tag("slow")
  void testOnlyOnMac() throws Exception {
    assumeTrue("Mac OS X".equals(System.getProperty("os.name")), () -> {
      return "Aborting test: requires macOS, found " + System.getProperty("os.name");
    });

    assertTrue(new File("/Volumes").exists());
    assertFalse(false);
  }

  @SlowTest // using composite tag (see at the bottom of this file)
  void testOnAllSystems() throws Exception {
    assumingThat("mac OS X".equals(System.getProperty("os.name")), () -> {
      assertTrue(new File("/Volumes/").exists()); // executed only if the assumption is true
    });

    // test code for all systems
    assertTrue(1 + 1 == 2);
  }

  @DisplayName("some fancy name with special chars")
  @Test
  void testName() throws Exception {
    assertTrue(true);
  }

  @Test
  @Disabled
  void testTimeoutExceeded() {
    assertTimeout(Duration.ofMillis(10), () -> {
      Thread.sleep(50); // task taking more than 10 ms
    });
  }

  @Test
  @Disabled
  void testTimeoutExceededAndAborted() {
    assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
      Thread.sleep(50); // task taking more than 10 ms
    });
  }

  @Test
  @Disabled
  void testMain() {
    fail("Not yet implemented");
  }

  // "test_f" + < CTRL + SPACE>
  @TestFactory
  @Disabled
  Stream<DynamicNode> dynamicTests() throws Exception {
    return Stream.of("A", "B").map(input -> dynamicContainer("Container " + input, Stream.of(1, 2)
        .map(number -> dynamicTest("Dynamic Test for " + number, () -> isEven(number)))));
  }

  private void isEven(int i) {
    assertTrue(i % 2 == 0, () -> i + " is not an even number.");
  }

  @Test
  @DisplayName("TestInfo demo")
  @Tag("my-tag")
  void testDependencyInjection(TestInfo testInfo) throws Exception {
    assertEquals("TestInfo demo", testInfo.getDisplayName());
    assertTrue(testInfo.getTags().contains("my-tag"));
  }

  @Test
  @DisplayName("TestReporter demo")
  void testDependencyInjection(TestReporter testReporter) throws Exception {
    testReporter.publishEntry("a key", "a value");
  }

  @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
  @DisplayName("Repeat!")
  void repeatedTest(RepetitionInfo repetitionInfo) throws Exception {
    assertEquals(5, repetitionInfo.getTotalRepetitions());
  }

  // @ParameterizedTest
  // @ValueSource(strings = {"X", "Y"})
  // @EnumSource(value = TimeUnit.class, names = {"HOURS", "DAYS"})
  // @MethodSource("stringProvider")
  // @CsvSource({"A, 1", "'B, C', 2"})
  // // @CsvFileSource(resources = "/two-columm.csv")
  // @ArgumentsSource(MyArgumentsProvider.class)
  // void parameterizedTest(Object argument) throws Exception {
  // assertNotNull(argument);
  // }

  // static Stream<String> stringProvider() {
  // return Stream.of("foo", "bar");
  // }
  //
  // static class MyArgumentsProvider implements ArgumentsProvider {
  // @Override
  // public Stream<? extends Arguments> provideArguments(ExtensionContext arg0) throws Exception {
  // return Stream.of("one", "two").map(Arguments::of);
  // }
  // }

  // example of composed annotation
  @Target({ElementType.TYPE, ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  @Test
  @Tag("slow")
  @interface SlowTest {
  }
}
