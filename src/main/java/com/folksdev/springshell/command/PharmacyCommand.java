package com.folksdev.springshell.command;

import java.util.Objects;

import com.folksdev.springshell.util.PharmacyFormatter;
import com.folksdev.springshell.remote.PharmacyApiClient;
import com.folksdev.springshell.util.ShellPrinter;
import lombok.RequiredArgsConstructor;
import org.fusesource.jansi.Ansi;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

@RequiredArgsConstructor
@Command(group = "Pharmacy Commands")
public class PharmacyCommand {

  private final PharmacyApiClient pharmacyApiClient;
  private final ShellPrinter printer;
  private final PharmacyFormatter pharmacyFormatter;

  @CommandAvailability(provider = "userLoggedInProvider")
  @Command(command = "pharmacy")
 public void pharmacy(
      @Option(required = true, shortNames = 'c', longNames = "city") String city,
      @Option(required = false, shortNames = 'd', longNames = "district") String district) {
    var data =
        Objects.requireNonNull(pharmacyApiClient.getPharmacies(city, district).getBody()).result();
    printer.print(pharmacyFormatter.convertToTable(data), Ansi.Color.CYAN);
  }
}
