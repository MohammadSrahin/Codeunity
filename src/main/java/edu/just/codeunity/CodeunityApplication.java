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
  @Bean
  public CommandLineRunner demo(UserService userService) {
    /*
    * Test Bean
    * */
    return (args) -> {
      User firstUser = new User();
      firstUser.setFirstName("Iyaad");
      firstUser.setLastName("Al-Azzam");
      User secondUser = new User();
      secondUser.setFirstName("Mohammad");
      secondUser.setLastName("Sreheen");
      userService.saveUser(firstUser);
      userService.saveUser(secondUser);
      secondUser.setFirstName("MOH");
      userService.saveUser(secondUser);
      for(User user: userService.getAllUsers()){
        System.out.println(user + " " + user.getId());
      }
      System.out.println(userService.getUserById(1L));
    };
  }

}
