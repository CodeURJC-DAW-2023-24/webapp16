package es.codeurjc.backend.controller;
import es.codeurjc.backend.model.User;
import org.springframework.ui.Model;
import es.codeurjc.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/loginRequest")
    public String loginData(@RequestParam String nick, @RequestParam String password){
        System.out.println("Recibidos nick: "+nick+" y pass: "+ password);
        if(userService.checkUser(password, nick)){
        return "redirect:/index";
        }else return "redirect:/error";

    }
    @PostMapping("/signUpRequest")
    public String signUpData(@RequestParam String nick, @RequestParam String email,@RequestParam String password, @RequestParam String date){
        System.out.println("Recibidos nick: "+nick+" email: "+ email+" pass: "+password+" date: "+date);
        User user = new User();
        user.setNickname(nick);
        user.setEmail(email);
        user.setPassword(password);
        user.setDateOfBirth(date);
        user.setAddress(null);
        user.setPhoneNumber(null);
        user.setGender(null);
        user.setLastName(null);
        userService.addUser(user);
        return "redirect:/index";
    }
}
