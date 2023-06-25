package edu.just.codeunity.services;

import edu.just.codeunity.entities.Course;
import edu.just.codeunity.entities.Progress;
import edu.just.codeunity.entities.Token;
import edu.just.codeunity.entities.User;
import edu.just.codeunity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private CourseService courseService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private TokenService tokenService;

    public User saveUser(User user){
        return userRepository.save(user);
    }


    public User getUserById(Long id){
        boolean isUserFound = userRepository.findById(id).isPresent();

        if (!isUserFound)
            throw new NoSuchElementException("No such user with the given id (" + id + ")");

        return userRepository.findById(id).get();
    }

    public User getUserByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByToken(Long tokenId){
        Token token = tokenService.getTokenById(tokenId);
        // TODO: [optional] set a specific period of time for each token(hint: here we can call tokenService to check the token)

        return token.getUser();
    }

    public List<User> getAllUsersEnrolledInCertainCourse(Long courseId){
        List<User> users = new ArrayList<>();

        Course course = courseService.getCourseById(courseId);
        for(Progress progress: progressService.getAllProgressesByCourse(course))
            users.add(progress.getUser());

        return users;
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
