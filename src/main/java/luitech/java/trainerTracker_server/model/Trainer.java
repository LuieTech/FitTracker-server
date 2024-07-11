package luitech.java.trainerTracker_server.model;

import jakarta.persistence.*;
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
    private Integer id;
    private String name;
    private String username;
    private String email;
    private Integer phoneNumber;

    @OneToMany
    private List<Exercise> exerciseList = new ArrayList<>();

    public Trainer(String name, String username, String email, Integer phoneNumber) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

}
