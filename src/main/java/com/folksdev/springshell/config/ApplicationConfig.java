package com.folksdev.springshell.config;

import com.folksdev.springshell.exception.ExceptionHandler;
import com.folksdev.springshell.util.ShellReader;
import org.jline.reader.LineReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ApplicationConfig {

  @Bean
  ExceptionHandler exceptionHandler() {
    return new ExceptionHandler();
  }

  @Bean
  public ShellReader shellReader(@Lazy LineReader lineReader) {
    return new ShellReader(lineReader);
  }
}
