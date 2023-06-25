package edu.just.codeunity.controllers;

import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

  private final UserService userService;
  private final CourseService courseService;
  private final PasswordEncoder passwordEncoder;

  public AccountController(UserService userService, CourseService courseService,
      PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.courseService = courseService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping(value = "/profile/{userID}", consumes = "application/json")
  public User getProfile(@PathVariable Long userID) {
    return userService.getUserById(userID);
  }

  @GetMapping(value = "/user/{userID}/courses", consumes = "application/json")
  public List<Course> getUserCourses(@PathVariable Long userID) {
    User user = userService.getUserById(userID);
    return courseService.getAllCoursesUserEnrolledIn(user);
  }

  @PostMapping( value = "/profile/{userID}", consumes = "application/json")
  public User updateProfile(@PathVariable Long userID, @RequestBody User updatedUser) {
    User user = userService.getUserById(userID);

    user.updateUser(updatedUser);
    userService.saveUser(user);
    return user;
  }

  @PostMapping(value = "/login", consumes = "application/json")
  public ResponseEntity<User> login(@RequestBody User user) {
    User registeredUser = userService.getUserByEmail(user.getEmail());

    if (registeredUser != null && passwordEncoder.matches(user.getPassword(), registeredUser.getPassword())) {
      return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }

  @PostMapping(value = "/register", consumes = "application/json")
  public User register(@RequestBody User user) {
    user.setJoinDate(new Date());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.saveUser(user);

    return user;
  }
}
