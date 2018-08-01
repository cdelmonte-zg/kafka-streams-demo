package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import java.util.Stack;


public class Parser {

  public static Command parse(final String expression) {
    Command syntaxTree = null;
    Stack<Command> expressionStack = new Stack<>();

    for (String token : expression.split("\\r?\\n")) {

      if (token.equals(CommandName.DO_CLICKS.toString())) {
        Command expr = new DoClick();
        expressionStack.push(expr);
      } else if (token.equals(CommandName.DO_TRANSACTIONS.toString())) {
        Command expr = new DoTransaction();
        expressionStack.push(expr);
      } else if (token.equals(CommandName.START_SESSION.toString())) {
        Command expr = new StartSession();
        expressionStack.push(expr);
      } else if (token.equals(CommandName.CLOSE_SESSION.toString())) {
        Command expr = new CloseSession();
        expressionStack.push(expr);
      } else if (!"".equals(token)) {
        // Command whatToDo = expressionStack.pop();
        // whatToDo.setAction(token);
        // expressionStack.push(whatToDo);
      }
    }
    syntaxTree = expressionStack.pop();
    return syntaxTree;
  }
}
