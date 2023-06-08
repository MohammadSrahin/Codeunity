package edu.just.codeunity.entities;

import edu.just.codeunity.Types.AccountRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "`users`")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

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
