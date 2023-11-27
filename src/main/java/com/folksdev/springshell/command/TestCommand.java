package com.folksdev.springshell.command;

import com.folksdev.springshell.util.ShellPrinter;
import com.folksdev.springshell.util.ShellReader;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

@RequiredArgsConstructor
@Command(group = "Test Commands")
public class TestCommand {

  private final ShellReader reader;
  private final ShellPrinter printer;

  @Command(command = "hi", description = "this will print 'hello!'")
  public void hello(
      @NotBlank
          @Size(min = 3, max = 7)
          @Option(shortNames = 'n', longNames = "name", description = "name input")
          String name,
      @NotBlank
          @Size(min = 3, max = 7)
          @Option(shortNames = 's', longNames = "surname", description = "surname input")
          String surname) {
    printer.printSuccess("hello! %s %s".formatted(name, surname));
    printer.printError("error!!!");
  }

  @CommandAvailability(provider = "userLoggedInProvider")
  @Command(command = "inputs", description = "this will print 'hello!'")
  public void inputs() {
    var name = reader.readLine("adiniz");
    var surname = reader.readLine("soyadiniz");
    //    var password = reader.readLinePassword("password");
    printer.print(name + " " + surname);
  }
}
