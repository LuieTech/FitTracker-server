package luitech.java.trainerTracker_server.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClientInfo {
    private String name;
    private String Address;
    private Integer phoneNumber;
    private String email;
}
