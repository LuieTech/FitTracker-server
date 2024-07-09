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
public class Exercise {
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
