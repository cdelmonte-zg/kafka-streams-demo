package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import java.util.LinkedList;
import java.util.Queue;


public class Parser {

  public static Queue<Command> parse(final String expression) {
    Queue<Command> syntaxTree = new LinkedList<>();

    for (String token : expression.split("\\r?\\n")) {

      if (token.equals(CommandName.DO_CLICKS.toString())) {
        Command expr = new DoClick();
        syntaxTree.add(expr);
      } else if (token.equals(CommandName.DO_TRANSACTIONS.toString())) {
        Command expr = new DoTransaction();
        syntaxTree.add(expr);
      } else if (token.equals(CommandName.START_SESSION.toString())) {
        Command expr = new StartSession();
        syntaxTree.add(expr);
      } else if (token.equals(CommandName.CLOSE_SESSION.toString())) {
        Command expr = new CloseSession();
        syntaxTree.add(expr);
      } else if (token.equals(CommandName.SAY_HELLO.toString())) {
        Command expr = new SayHello();
        syntaxTree.add(expr);
      } else if (!"".equals(token)) {
        // Command whatToDo = expressionStack.pop();
        // whatToDo.setAction(token);
        // expressionStack.push(whatToDo);
      }
    }
    return syntaxTree;
  }
}
