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
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String comment;
    @Embedded
    private ClientInfo clientInfo;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;
//    @OneToMany(mappedBy = "client")
//    private List<Exercise> exerciseList = new ArrayList<>();

    public Client(String username, String password, String comment, ClientInfo clientInfo) {
        this.username = username;
        this.password = password;
        this.comment = comment;
        this.clientInfo = clientInfo;
    }
}
