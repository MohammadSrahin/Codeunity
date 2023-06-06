package edu.just.codeunity.controllers;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
  private final ObjectMapper objectMapper;

  public AccountController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @GetMapping("/profile/{userID}")
  public ObjectNode getProfile(@PathVariable String userID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: Fetch user

    return node;
  }

  @GetMapping("/user/{userID}/courses")
  public ObjectNode getUserCourses(@PathVariable String userID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: Fetch user

    return node;
  }

  @PostMapping("/profile/{userID}")
  public ObjectNode updateProfile(@PathVariable String userID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: update user

    return node;
  }


  @PostMapping("/login")
  public ObjectNode login() {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: login

    return node;
  }

  @PostMapping("/logout")
  public ObjectNode logout() {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: logout

    return node;
  }

  @PostMapping("/register")
  public ObjectNode register() {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: register

    return node;
  }
}
