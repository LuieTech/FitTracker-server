package luitech.java.trainerTracker_server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String gifUrl;
    private String name;
    @ElementCollection
    private List<String> instructions;
    private String bodyPart;
    @ManyToOne // @JsonIgnore
    @JoinColumn(name = "client_id")
    private Client client;

    public Exercise(String name, List<String> instructions, String bodyPart) {
        this.name = name;
        this.instructions = instructions;
        this.bodyPart = bodyPart;
    }
}
