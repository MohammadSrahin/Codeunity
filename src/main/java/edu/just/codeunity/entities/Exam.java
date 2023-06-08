package edu.just.codeunity.entities;

import edu.just.codeunity.Types.ExamType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String name;
    private String description;
    private String input;
    private String ouput;
    @Override
    public String toString(){
        return name;
    }
}
