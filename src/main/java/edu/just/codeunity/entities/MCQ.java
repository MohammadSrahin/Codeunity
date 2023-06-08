package edu.just.codeunity.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MCQ extends Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;

}
