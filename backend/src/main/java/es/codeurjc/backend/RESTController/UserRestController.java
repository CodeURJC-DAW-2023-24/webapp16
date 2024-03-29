package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.UserDTO;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
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
        for (UserDTO userDTO: userDTOS){
            userDTO.setEncodedPassword("NothingToSeeHere");
        }
        return ResponseEntity.ok(userDTOS);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO>getUserByID(@PathVariable long id){
        Optional<User> optionalUser = userService.findUserByID(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = userService.convertToDTO(user);
            userDTO.setEncodedPassword("NothingToSeeHere");
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO userDTO){
        User user = userService.convertToEntity(userDTO);
        URI location = URI.create("/api/users/"+user.getId());
        userService.saveUser(user);
        return ResponseEntity.created(location).body(userDTO);
    }
    @PutMapping
    public ResponseEntity<UserDTO> modUser(@RequestBody UserDTO userDTO){
        User user = userService.convertToEntity(userDTO);
        URI location = URI.create("/api/users/"+user.getId());
        userService.modUser(user);//modified by ID
        return ResponseEntity.created(location).body(userDTO);
    }
    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable long idUser){
        userService.deleteUserByID(idUser);
        //URI location = URI.create("/api/users");
        String msg = "User with id " + idUser+ " deleted .";

        return ResponseEntity.status(HttpStatus.OK).body(msg);
       // return ResponseEntity.ok().location(location).body(this.getAllUsers().getBody());
        //return user list to check delete
    }
}
