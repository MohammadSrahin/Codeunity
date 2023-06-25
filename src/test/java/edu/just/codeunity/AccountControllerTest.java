package edu.just.codeunity;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.*;
import edu.just.codeunity.controllers.*;
import edu.just.codeunity.entities.*;
import edu.just.codeunity.services.*;
import java.util.*;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private CourseService courseService;

  @MockBean
  private PasswordEncoder passwordEncoder;

  @Test
  void whenGetProfile_thenReturnUser() throws Exception {
    User user = new User();
    user.setId(123L);

    when(userService.getUserById(user.getId())).thenReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.get("/profile/{userID}", user.getId())
            .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.id").value(user.getId()));
  }

  @Test
  void whenGetUserCourses_thenReturnCourses() throws Exception {
    User user = new User();
    user.setId(123L);
    Course course1 = new Course();
    course1.setId(1L);
    Course course2 = new Course();
    course2.setId(2L);
    List<Course> courseList = Arrays.asList(course1, course2);

    when(userService.getUserById(user.getId())).thenReturn(user);
    when(courseService.getAllCoursesUserEnrolledIn(user)).thenReturn(courseList);

    mockMvc.perform(MockMvcRequestBuilders.get("/user/{userID}/courses", user.getId())
            .contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].id").value(course1.getId()))
        .andExpect(jsonPath("$[1].id").value(course2.getId()));
  }

  @Test
  void whenUpdateProfile_thenReturnUpdatedUser() throws Exception {
    User user = new User();
    user.setId(123L);
    User updatedUser = new User();
    updatedUser.setId(user.getId());
    updatedUser.setEmail("updated@example.com");

    when(userService.getUserById(user.getId())).thenReturn(user);
    when(userService.saveUser(user)).thenReturn(updatedUser);

    mockMvc.perform(MockMvcRequestBuilders.post("/profile/{userID}", user.getId())
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(updatedUser)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.email").value("updated@example.com"));
  }

  @Test
  void whenLogin_thenReturnLoggedInUser() throws Exception {
    User user = new User();
    user.setEmail("testuser@gmail.com");
    user.setPassword("testpassword");

    when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
    when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
    when(passwordEncoder.matches(user.getPassword(), "testpassword")).thenReturn(true);
    mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.email").value("testuser@gmail.com"));
  }

  @Test
  void whenRegister_thenReturnRegisteredUser() throws Exception {
    User user = new User();

    when(userService.saveUser(user)).thenReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.post("/register")
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(user)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.joinDate").isNotEmpty());
  }
}
