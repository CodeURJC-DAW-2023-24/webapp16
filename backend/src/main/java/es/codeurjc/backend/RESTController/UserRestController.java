package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.DTOs.UserDTO;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.service.TournamentService;
import es.codeurjc.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDTO>>getAllUsers(){
        List<UserDTO> userDTOS = userService.findAllUsers().stream()
                .map(userService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOS);
    }
    @GetMapping("/{name}")
    public ResponseEntity<UserDTO>getUserByName(@PathVariable String name){
        Optional<User> optionalUser = userService.findUserByName(name);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = userService.convertToDTO(user);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
