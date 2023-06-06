package edu.just.codeunity.controllers;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {

  private final ObjectMapper objectMapper;

  public LessonController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @GetMapping
  public ObjectNode getLessons() {
    ObjectNode node = objectMapper.createObjectNode();

    return node;
  }

  @GetMapping("/{lessonID}")
  public ObjectNode getLesson(@PathVariable String lessonID) {
    ObjectNode node = objectMapper.createObjectNode();

    return node;
  }

  @PostMapping("/{lessonID}")
  public ObjectNode updateLesson(@PathVariable String lessonID) {
    ObjectNode node = objectMapper.createObjectNode();

    return node;
  }


  @PutMapping("/{lessonID}")
  public ObjectNode putLesson(@PathVariable String lessonID) {
    ObjectNode node = objectMapper.createObjectNode();

    return node;
  }

  @DeleteMapping("/{lessonID}")
  public ObjectNode deleteLesson(@PathVariable String lessonID) {
    ObjectNode node = objectMapper.createObjectNode();

    return node;
  }

}
