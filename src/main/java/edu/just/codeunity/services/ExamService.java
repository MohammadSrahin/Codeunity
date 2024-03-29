package edu.just.codeunity.services;

import edu.just.codeunity.entities.*;
import edu.just.codeunity.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public Exam saveExam(Exam exam) {
       return examRepository.save(exam);
    }
}
