package edu.just.codeunity.services;

import edu.just.codeunity.entities.User;
import edu.just.codeunity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }
    public User updateUser(User user){
        return createUser(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
