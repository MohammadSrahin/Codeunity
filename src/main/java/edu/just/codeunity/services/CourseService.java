package edu.just.codeunity.services;

import edu.just.codeunity.entities.Course;
import edu.just.codeunity.entities.Progress;
import edu.just.codeunity.entities.User;
import edu.just.codeunity.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProgressService progressService;

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id){
        boolean isCourseFound = courseRepository.findById(id).isPresent();
        if (!isCourseFound)
            throw new NoSuchElementException("no such course with the given Id(" + id + ")");
        return courseRepository.findById(id).get();
    }

    public List<Course> getAllCoursesUserEnrolledIn(User user){
        List<Course> courses = new ArrayList<>();
        for(Progress progress: progressService.getAllProgressesByUser(user))
            courses.add(progress.getCourse());

        return courses;
    }

    public void deleteCourse(Course course){
        courseRepository.delete(course);
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

}
