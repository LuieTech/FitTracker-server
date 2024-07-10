package luitech.java.trainerTracker_server.repository;

import luitech.java.trainerTracker_server.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {


}
