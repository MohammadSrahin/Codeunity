package edu.just.codeunity.entities;

import edu.just.codeunity.Types.ProgressStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Course course;

    private ProgressStatus status;
    private Integer currentLesson;

    public void invalidate(){
        if (course.getLessons().size() == currentLesson){
            status = ProgressStatus.COMPLETED;
        }
    }
}
