package luitech.java.trainerTracker_server.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import luitech.java.trainerTracker_server.model.Trainer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerResponseDTO {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;

    public TrainerResponseDTO(Trainer trainer) {
        this.id = trainer.getId();
        this.name = trainer.getName();
        this.username = trainer.getActualUsername();
        this.email = trainer.getEmail();
        this.phoneNumber = trainer.getPhoneNumber();
    }

}
