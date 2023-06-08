package edu.just.codeunity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Token {
    /* *
    * this table is corresponding for session validation, to check if the user is logged in
    * By using the id as a Token, where the user will send the Id in every request to validate the request
    * and assign in it regarding the user who owns this token
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    @ManyToOne
    private User user;

    private Date createDate;
}
