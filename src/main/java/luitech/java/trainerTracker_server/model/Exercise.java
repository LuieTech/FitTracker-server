package luitech.java.trainerTracker_server.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Exercise {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exerciseId;
    private String name;
    private String description;
    private String bodyPart;

    public Exercise(String name, String description, String bodyPart) {
        this.name = name;
        this.description = description;
        this.bodyPart = bodyPart;
    }

}
