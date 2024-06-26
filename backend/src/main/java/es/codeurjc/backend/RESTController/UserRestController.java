package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.UserDTO;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found users", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<List<UserDTO>>getAllUsers(){
        List<UserDTO> userDTOS = userService.findAllUsers().stream()
                .map(userService::convertToDTO)
                .collect(Collectors.toList());
      /*  for (UserDTO userDTO: userDTOS){
            userDTO.setEncodedPassword("NothingToSeeHere");
        }*/
        return ResponseEntity.ok(userDTOS);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDTO>getUserByID(@PathVariable long id){
        Optional<User> optionalUser = userService.findUserByID(id);
        if (optionalUser.isPresent()) {
            UserDTO userDTO = userService.convertToDTO(optionalUser.get());
            userDTO.setEncodedPassword("NothingToSeeHere");
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/")
    @Operation(summary = "Create a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created User", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
    })
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO userDTO){
        if (userService.existsUser(userDTO.getName())) {
            return ResponseEntity.badRequest().body(new UserDTO("Username already exists"));
        }
        User user = userService.convertToEntity(userDTO);
        user.setEncodedPassword(passwordEncoder.encode(userDTO.getPassword()));
        userService.saveUser(user);
        URI location = URI.create("/api/users/"+user.getId());
        return ResponseEntity.created(location).body(userDTO);
    }

    @PutMapping
    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDTO> modUser(@RequestBody UserDTO userDTO){
        User user = userService.convertToEntity(userDTO);
        URI location = URI.create("/api/users/"+user.getId());
        userService.modUser(user);//modified by ID
        return ResponseEntity.created(location).body(userDTO);
    }


    @DeleteMapping("/{idUser}")
    @Operation(summary = "Delete a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content)
    })
    public ResponseEntity<?> deleteUser(@PathVariable long idUser){
        userService.deleteUserByID(idUser);
        String msg = "User with id " + idUser+ " deleted .";
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

}
