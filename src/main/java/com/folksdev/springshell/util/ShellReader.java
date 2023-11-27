package com.folksdev.springshell.util;

import org.jline.reader.LineReader;

public class ShellReader {

  private LineReader lineReader;

  public ShellReader(LineReader lineReader) {
    this.lineReader = lineReader;
  }

  public String readLine(String displayText) {
    return lineReader.readLine(displayText + ": ");
  }

  public String readLinePassword(String displayText) {
    return lineReader.readLine(displayText + ": ", '*');
  }
}
