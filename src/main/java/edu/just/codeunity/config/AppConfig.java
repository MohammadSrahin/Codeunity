package edu.just.codeunity.config;

import com.fasterxml.jackson.databind.*;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {


  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
