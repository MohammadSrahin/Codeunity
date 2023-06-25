package edu.just.codeunity.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Exam {
    /* *
    * please notice that the input field will contain more than one input separated by '|', where the same goes for output
    * after separating each input and output, you'll have two lists with equal size.
    * input and output aligned together by index, in other words, in i-th position, i-th output element is the expected from the i-th input
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String question;
    private String input;
    private String output;
    @Override
    public String toString(){
        return question;
    }
}
