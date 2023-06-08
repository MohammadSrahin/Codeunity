package edu.just.codeunity.services;

import edu.just.codeunity.entities.Course;
import edu.just.codeunity.entities.Progress;
import edu.just.codeunity.entities.User;
import edu.just.codeunity.repositories.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;

    public Progress saveProgress(Progress progress){
        return progressRepository.save(progress);
    }
    public List<Progress> getAllProgressesByCourse(Course course){
        return progressRepository.findAllByCourse(course);
    }
    public List<Progress> getAllProgressesByUser(User user){
        return progressRepository.findAllByUser(user);
    }
    public Progress getProgressByCourseAndUser(Course course, User user){
        return progressRepository.findByCourseAndUser(course, user);
    }

    public void deleteProgress(Progress progress){
        progressRepository.delete(progress);
    }
}
