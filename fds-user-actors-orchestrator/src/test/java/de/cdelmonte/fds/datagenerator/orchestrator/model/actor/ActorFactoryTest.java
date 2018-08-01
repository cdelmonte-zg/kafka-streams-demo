package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.function.Supplier;
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
import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.Behavior;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class ActorFactoryTest {

  @Mock
  Behavior goodBehavior;
  @Mock
  Behavior badBehavior;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {}

  @AfterEach
  void tearDown() throws Exception {}

  @Mock
  private List<Integer> list;

  @Test
  public void shouldDoSomething() {
    list.add(100);
  }

  @Test
  void testGetActor() throws Exception {
    Supplier<ActorFactory> actorFactory = ActorFactory::new;
    assertAll("Should return the correct actor types",
        () -> assertTrue(
            (actorFactory.get().createActor(ActorType.BAD_USER, badBehavior) instanceof BadUser),
            () -> "Actor type Bad User is related to the wrong enum type"),
        () -> assertTrue(
            (actorFactory.get().createActor(ActorType.GOOD_USER, goodBehavior) instanceof GoodUser),
            () -> "Actor type Good User is related to the wrong enum type"));
  }
}
