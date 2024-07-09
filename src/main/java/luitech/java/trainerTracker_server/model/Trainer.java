package luitech.java.trainerTracker_server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainerId;
    private String name;
    private String email;
    private Integer phoneNumber;

    @OneToMany(mappedBy = "trainerId")
    private List<Client> clients = new ArrayList<>();

    @OneToMany
    private List<Exercise> exercises = new ArrayList<>();

    public Trainer(String name, String email, Integer phoneNumber, List<Client> clients, List<Exercise> exercises) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.clients = clients;
        this.exercises = exercises;
    }
}
