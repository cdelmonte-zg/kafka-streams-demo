package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import java.util.Queue;


public class Executor implements Command {

  private Queue<Command> syntaxTree;

  public Executor(Queue<Command> syntaxTree) {
    this.syntaxTree = syntaxTree;
  }

  @Override
  public void interpret(Context co) {
    for (Command command : syntaxTree) {
      command.interpret(co);
    }
  }
}
