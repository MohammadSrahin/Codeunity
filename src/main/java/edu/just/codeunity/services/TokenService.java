package edu.just.codeunity.services;

import edu.just.codeunity.entities.Token;
import edu.just.codeunity.entities.User;
import edu.just.codeunity.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public Token saveToken(Token token){
        return tokenRepository.save(token);
    }

    public Token getTokenById(Long id){
        boolean isTokenFound = tokenRepository.findById(id).isPresent();
        if (!isTokenFound){
            throw new NoSuchElementException("No such token with the given id(" + id + ")");
        }
        return tokenRepository.findById(id).get();
    }

    public Token getTokenByUser(User user){
        return tokenRepository.findByUser(user);
    }

    public void deleteToken(Token token){
        tokenRepository.delete(token);
    }
}
