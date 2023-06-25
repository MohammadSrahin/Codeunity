package edu.just.codeunity;

import edu.just.codeunity.entities.User;
import edu.just.codeunity.repositories.UserRepository;
import edu.just.codeunity.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.boot.test.context.*;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private CourseService courseService;

  @Mock
  private ProgressService progressService;

  @Mock
  private TokenService tokenService;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveUser() {
    User user = new User();
    when(userRepository.save(user)).thenReturn(user);

    User savedUser = userService.saveUser(user);

    Assertions.assertEquals(user, savedUser);
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void testGetUserById_UserExists() {
    User user = new User();
    user.setId(1L);
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    User retrievedUser = userService.getUserById(user.getId());

    Assertions.assertEquals(user, retrievedUser);
    verify(userRepository, times(2)).findById(user.getId());
  }

  @Test
  void testGetUserById_UserDoesNotExist() {
    Long userId = 1L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    Assertions.assertThrows(NoSuchElementException.class, () -> userService.getUserById(userId));

    verify(userRepository, times(1)).findById(userId);
  }
}

