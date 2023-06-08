package edu.just.codeunity.entities;

import edu.just.codeunity.Types.AccountRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private AccountRole role;
    private Date birthDate;
    private Date joinDate;
    private String profilePicture;
//    private String activationCode;

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
