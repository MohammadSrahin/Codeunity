package edu.just.codeunity;

import edu.just.codeunity.entities.Course;
import edu.just.codeunity.entities.Progress;
import edu.just.codeunity.repositories.ProgressRepository;
import edu.just.codeunity.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.test.context.*;

import static org.mockito.Mockito.*;

@SpringBootTest
class ProgressServiceTest {

  @Mock
  private ProgressRepository progressRepository;

  @InjectMocks
  private ProgressService progressService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSaveProgress() {
    Progress progress = new Progress();
    when(progressRepository.save(progress)).thenReturn(progress);

    Progress savedProgress = progressService.saveProgress(progress);

    Assertions.assertEquals(progress, savedProgress);
    verify(progressRepository, times(1)).save(progress);
  }

  @Test
  void testGetAllProgressesByCourse() {
    Course course = new Course();
    List<Progress> expectedProgresses = new ArrayList<>();
    when(progressRepository.findAllByCourse(course)).thenReturn(expectedProgresses);

    List<Progress> retrievedProgresses = progressService.getAllProgressesByCourse(course);

    Assertions.assertEquals(expectedProgresses, retrievedProgresses);
    verify(progressRepository, times(1)).findAllByCourse(course);
  }

}
