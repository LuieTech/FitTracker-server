package luitech.java.trainerTracker_server.repository;

import luitech.java.trainerTracker_server.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    //JPA
    List<Exercise> findAllByTrainerId(Integer trainer);
}
