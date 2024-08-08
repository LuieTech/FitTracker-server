package luitech.java.trainerTracker_server.controller.impl;
import luitech.java.trainerTracker_server.controller.dto.ClientCommentDTO;
import luitech.java.trainerTracker_server.controller.dto.ClientEmailDTO;
import luitech.java.trainerTracker_server.controller.dto.ClientPasswordDTO;
import luitech.java.trainerTracker_server.controller.dto.ClientUsernameDTO;
import luitech.java.trainerTracker_server.model.Client;
import luitech.java.trainerTracker_server.model.Exercise;
import luitech.java.trainerTracker_server.service.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    IClientService clientService;

    @GetMapping("/clients")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody Client clientBody){
        clientService.saveClient(clientBody);
    }

    @PatchMapping("/clients/email/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClientEmail(@RequestBody ClientEmailDTO clientEmailDTO, @PathVariable Integer id){
        clientService.updateClientEmail(clientEmailDTO.getEmail(), id);
    }

    @DeleteMapping("/clients/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Integer clientId){
        clientService.deleteClient(clientId);
    }

    @GetMapping("/clients/exercises/{clientId}")
    public List<Exercise> getAllExercisesByClientId(@PathVariable Integer clientId){
        return clientService.getAllExercisesByClientId(clientId);
    }

    @PatchMapping("/clients/{id}/comment")
    public void updateClientComment(@RequestBody ClientCommentDTO clientCommentDTO, @PathVariable Integer id){
        clientService.updateClientComment(clientCommentDTO.getComment(), id);
    }


}
