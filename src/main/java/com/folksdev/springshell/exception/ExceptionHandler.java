package com.folksdev.springshell.exception;

import java.util.stream.Collectors;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;

public class ExceptionHandler implements CommandExceptionResolver {
  @Override
  public CommandHandlingResult resolve(Exception ex) {
    if (ex instanceof ParameterValidationException) {
      return CommandHandlingResult.of(
          ((ParameterValidationException) ex)
                  .getConstraintViolations().stream()
                      .map(x -> x.getPropertyPath() + " " + x.getMessage())
                      .collect(Collectors.joining(". "))
              + '\n');
    }
    return CommandHandlingResult.of(ex.getMessage() + "\n", 1);
  }
}
