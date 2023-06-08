package edu.just.codeunity.services;

import edu.just.codeunity.entities.Lesson;
import edu.just.codeunity.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LessonService {
    @Autowired
    private CourseService courseService;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private ExamService examService;

    public Lesson saveLesson(Lesson lesson){
        return lessonRepository.save(lesson);
    }

    public Lesson getLessonById(Long id){
        boolean isLessonFound = lessonRepository.findById(id).isPresent();
        if (!isLessonFound)
            throw new NoSuchElementException("No such Lesson with the given Id(" + id + ") exists");

        return lessonRepository.findById(id).get();
    }

    public void deleteLesson(Lesson lesson){
        lessonRepository.delete(lesson);
    }

}
