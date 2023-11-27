package com.folksdev.springshell.command;

import com.folksdev.springshell.util.ShellPrinter;
import com.folksdev.springshell.util.ShellReader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.command.annotation.Command;

@RequiredArgsConstructor
@Command(group = "Auth Command")
public class AuthCommand {

  private final AuthenticationManager authenticationManager;
  private final ShellPrinter shellPrinter;
  private final ShellReader shellReader;

  @Command(command = "login")
  public void login() {
    var username = shellReader.readLine("username");
    var password = shellReader.readLinePassword("pass");
    Authentication result =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
    SecurityContextHolder.getContext().setAuthentication(result);
    shellPrinter.printSuccess("successfully authenticated");
  }

  @Command(command = "logout")
  public void logout() {
    SecurityContextHolder.clearContext();
    shellPrinter.printSuccess("successfully logged out");
  }
}
