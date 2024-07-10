package luitech.java.trainerTracker_server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
    @Getter
    private String username;
    private String email;
    private Integer phoneNumber;

    @OneToMany(mappedBy = "trainerId")
    private List<Client> clientList = new ArrayList<>();

    @OneToMany
    private List<Exercise> exerciseList = new ArrayList<>();

    public Trainer(String name, String username, String email, Integer phoneNumber) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addClient(Client client){
        clientList.add(client);
    }
    public void addExercise(Exercise exercise){
        exerciseList.add(exercise);
    }



}
