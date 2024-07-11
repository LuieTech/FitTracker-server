package luitech.java.trainerTracker_server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    private String description;
    private String bodyPart;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public Exercise(String name, String description, String bodyPart) {
        this.name = name;
        this.description = description;
        this.bodyPart = bodyPart;
    }

}
