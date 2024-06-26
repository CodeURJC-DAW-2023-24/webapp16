package es.codeurjc.backend.RESTController;

import es.codeurjc.backend.DTOs.UserDTO;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/me")
public class MeRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get the profile of the currently authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDTO> getMyProfile(HttpServletRequest request) {
        Principal currentUser = request.getUserPrincipal();

        User user = userService.getUserByName(currentUser.getName());
        Hibernate.initialize(user);
        UserDTO userDTO = userService.convertToDTO(user);
        return ResponseEntity.ok(userDTO);
    }
    @PutMapping
    @Operation(summary = "Update the profile of the currently authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDTO> updateMyProfile(HttpServletRequest request, @RequestBody UserDTO updatedUserDTO) throws ServletException {
        Principal currentUser = request.getUserPrincipal();
        User oldUser = userService.getUserByName(currentUser.getName());
        if (oldUser == null){
            return ResponseEntity.badRequest().build();
        }
        User updatedUser = userService.convertToEntity(updatedUserDTO);
        userService.modUser(updatedUser);
        User user = userService.getUserByName(updatedUserDTO.getName());
        Hibernate.initialize(user);
        UserDTO userDTO = userService.convertToDTO(user);
        System.out.println(userDTO);

        // If the username has changed, log out the user
        if (!currentUser.getName().equals(updatedUserDTO.getName())) {
            request.logout();
        }

        return ResponseEntity.ok(userDTO);
    }
    @GetMapping("/usernameExists/{username}")
    public ResponseEntity<Boolean> usernameExists(@PathVariable String username) {
        boolean exists = userService.existsUser(username);
        if (exists) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
}
