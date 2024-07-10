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
    private String name;
    private String username;
    private String password;
    private String email;
    private String comment;
    @OneToMany
    private List<Exercise> exerciseList = new ArrayList<>();
    private Integer trainerId;

    public Client(String name, String username, String password, String email, String comment) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.comment = comment;
    }

    public void addExercise(Exercise exercise){
        exerciseList.add(exercise);
    }
}
