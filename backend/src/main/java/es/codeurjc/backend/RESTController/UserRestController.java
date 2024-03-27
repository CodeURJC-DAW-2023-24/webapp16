package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.TournamentDTO;
import es.codeurjc.backend.DTOs.UserDTO;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.service.TournamentService;
import es.codeurjc.backend.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserRestController { //Añadir usuario, modificar usuario y borrar usuario, hacer que se modifiquen campos indicados solo y no contraseña
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
    @PostMapping
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO userDTO){
        User user = userService.convertToEntity(userDTO);
        URI location = URI.create("/api/users/"+user.getName());
        userService.saveUser(user);
        return ResponseEntity.created(location).body(userDTO);
    }
    @PutMapping
    public ResponseEntity<UserDTO> modUser(@RequestBody UserDTO userDTO){
        User user = userService.convertToEntity(userDTO);
        URI location = URI.create("/api/users/"+user.getName());
        userService.modUser(user);
        return ResponseEntity.created(location).body(userDTO);
    }
    @DeleteMapping
    public void deleteUser(@RequestBody long idUser){
        userService.deleteUserByID(idUser);
    }
}
