package edu.just.codeunity.repositories;

import edu.just.codeunity.entities.Course;
import edu.just.codeunity.entities.Progress;
import edu.just.codeunity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findAllByCourse(Course course);
    List<Progress> findAllByUser(User user);
    Progress findByCourseAndUser(Course course, User user);
}
