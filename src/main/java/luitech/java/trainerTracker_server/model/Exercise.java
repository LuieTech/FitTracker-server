package luitech.java.trainerTracker_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String bodyPart;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Exercise(String name, String description, String bodyPart) {
        this.name = name;
        this.description = description;
        this.bodyPart = bodyPart;
    }

}
