package de.cdelmonte.fds.datagenerator.orchestrator.model.action;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import de.cdelmonte.fds.datagenerator.orchestrator.event.ClickEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.event.SessionEvent;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class SessionTest {
  SessionEvent session;

  @Mock
  ClickEvent click;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    session = new SessionEvent(null);
  }

  @AfterEach
  void tearDown() throws Exception {
    session = null;
  }

  @Test
  void testSession() {
    // fail("Not yet implemented");
  }

  @Test
  void testGetSessionId() {
    assertTrue(session instanceof SessionEvent, () -> "Something weird is happening");
  }
}
