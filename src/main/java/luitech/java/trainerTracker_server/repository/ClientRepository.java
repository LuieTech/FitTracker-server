package luitech.java.trainerTracker_server.repository;

import luitech.java.trainerTracker_server.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
