package edu.just.codeunity;

import edu.just.codeunity.entities.User;
import edu.just.codeunity.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodeunityApplication {

  public static void main(String[] args) {
    SpringApplication.run(CodeunityApplication.class, args);
  }
}
