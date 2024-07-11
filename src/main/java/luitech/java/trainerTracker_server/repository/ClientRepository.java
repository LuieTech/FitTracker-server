package luitech.java.trainerTracker_server.repository;

import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    //JPA
    List<Client> findAllByTrainerId(Integer trainer);

}
