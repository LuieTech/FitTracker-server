package luitech.java.trainerTracker_server.model;

import jakarta.persistence.*;
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
    private Integer Id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToMany
    private List<Exercise> exerciseList = new ArrayList<>();

    public Client(String name, String username, String password, String email, String comment) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.comment = comment;
    }
}
