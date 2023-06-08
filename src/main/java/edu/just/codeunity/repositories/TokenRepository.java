package edu.just.codeunity.repositories;

import edu.just.codeunity.entities.Token;
import edu.just.codeunity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByUser(User user);
}
