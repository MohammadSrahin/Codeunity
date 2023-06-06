package edu.just.codeunity.controllers;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.springframework.web.bind.annotation.*;

@RestController("/course")
public class CourseController {

  private final ObjectMapper objectMapper;

  public CourseController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @GetMapping
  public ObjectNode getCourses() {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: Fetch courses

    return node;
  }

  @GetMapping("/{courseID}/participants")
  public ObjectNode getParticipants(@PathVariable String courseID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: Fetch participants

    return node;
  }

  @GetMapping("/{courseID}/{userID}")
  public ObjectNode getUserInCourse(@PathVariable String courseID, @PathVariable String userID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: Fetch courses

    return node;
  }

  @GetMapping("/{courseID}")
  public ObjectNode getCourse(@PathVariable String courseID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: Fetch course

    return node;
  }

  @PostMapping("/{courseID}/{userID}")
  public ObjectNode updateUserInCourse(@PathVariable String courseID, @PathVariable String userID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: update user

    return node;
  }

  @PutMapping("/{courseID}")
  public ObjectNode putCourse(@PathVariable String courseID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: Fetch course

    return node;
  }

  @DeleteMapping("/{courseID}")
  public ObjectNode deleteCourse(@PathVariable String courseID) {
    ObjectNode node = objectMapper.createObjectNode();

    //TODO: Fetch course

    return node;
  }
}
