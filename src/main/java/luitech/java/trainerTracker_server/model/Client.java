package luitech.java.trainerTracker_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;
    private String username;
    private String password;
    private String email;
    private String comment;
    @OneToMany
    private List<Exercise> exerciseList = new ArrayList<>();
    private Integer trainerId;

    public Client(String username, String password, String email, String comment, List<Exercise> exerciseList, Integer trainerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.comment = comment;
        this.exerciseList = exerciseList;
        this.trainerId = trainerId;
    }
}