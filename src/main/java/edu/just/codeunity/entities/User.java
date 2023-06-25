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

    public void updateUser(User user) {
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.username = user.username;
        this.birthDate = user.birthDate;
        this.role = user.role;
        this.Id = user.Id;
    }

    @Override
    public String toString() {
        return "User{" +
            "Id=" + Id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", role=" + role +
            ", birthDate=" + birthDate +
            ", joinDate=" + joinDate +
            ", profilePicture='" + profilePicture + '\'' +
            '}';
    }
}
