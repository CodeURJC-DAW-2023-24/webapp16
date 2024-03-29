package es.codeurjc.backend.webController;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class UserWebController {
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/loginRequest")
    public String loginData(@RequestParam String nick, @RequestParam String password) {

        return "redirect:/";
    }

    @PostMapping("/signUpRequest")
    public String signUpData(@RequestParam String name, @RequestParam String email,@RequestParam String password, @RequestParam String date, Model model){

        userService.newUser(name,email,password,date);
        model.addAttribute("name", name);
        return "loginSuccesfull";
    }
    @GetMapping("/profile")
    public String profile(Model model, HttpServletRequest request) {

        //get session id
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            String name = principal.getName();
            User userProfile = userRepository.findByName(name).orElseThrow();
            model.addAttribute("userProfile", userProfile);
            if (request.isUserInRole("ADMIN")){
                model.addAttribute("admin", request.isUserInRole("ADMIN"));
                model.addAttribute("user", request.isUserInRole("USER"));
            }else
                model.addAttribute("user", request.isUserInRole("USER"));
        }
        model.addAttribute("pageTitle", "Profile");

        return "showProfile";
    }
    @GetMapping("/profileMod")
    public String profileModify(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            String name = principal.getName();
            User userProfile = userRepository.findByName(name).orElseThrow();
            model.addAttribute("userProfile", userProfile);
            if (request.isUserInRole("ADMIN")){
                model.addAttribute("admin", request.isUserInRole("ADMIN"));
                model.addAttribute("user", request.isUserInRole("USER"));
            }else
                model.addAttribute("user", request.isUserInRole("USER"));

        }
        model.addAttribute("pageTitle", "Profile Modify");

        return "profileMod";
    }
    @PostMapping("/profile/save")
    public String saveUserProfile(Model model, HttpServletRequest request,
                                  @RequestParam(value = "name", required = false) String userName,
                                  @RequestParam(value = "firstName", required = false) String firstName,
                                  @RequestParam(value = "lastName", required = false) String lastName,
                                  @RequestParam(value = "dateOfBirth", required = false) String dateOfBirth,
                                  @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                  @RequestParam(value = "dni", required = false) String dni,
                                  @RequestParam(value = "address", required = false) String address,
                                  @RequestParam(value = "email", required = false) String email,
                                  @RequestParam(value = "gender", required = false) String gender) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            String name = principal.getName();
            User currentUser = userRepository.findByName(name).orElseThrow();

            if (userName != null) {
                currentUser.setName(userName);
            }
            if (firstName != null) {
                currentUser.setFirstName(firstName);
            }
            if (lastName != null) {
                currentUser.setLastName(lastName);
            }
            if (dateOfBirth != null) {
                currentUser.setDateOfBirth(dateOfBirth);
            }
            if (phoneNumber != null) {
                currentUser.setPhoneNumber(phoneNumber);
            }
            if (dni != null) {
                currentUser.setDni(dni);
            }
            if (address != null) {
                currentUser.setAddress(address);
            }
            if (email != null) {
                currentUser.setEmail(email);
            }
            if (gender != null) {
                currentUser.setGender(gender);
            }

            userService.saveUser(currentUser);

            model.addAttribute("userProfile", currentUser);
            model.addAttribute("pageTitle", "Profile");

        }
        return "showProfile";
    }
}