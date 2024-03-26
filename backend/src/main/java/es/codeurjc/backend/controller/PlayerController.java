package es.codeurjc.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/players")
    public String showPlayers(Model model,HttpServletRequest request){
        //get session id
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            String name_session = principal.getName();
            User user = userRepository.findByName(name_session).orElseThrow();
            model.addAttribute("username", user.getName());
            if (request.isUserInRole("ADMIN")){
                model.addAttribute("admin", request.isUserInRole("ADMIN"));
                model.addAttribute("user", request.isUserInRole("USER"));
            }else
                model.addAttribute("user", request.isUserInRole("USER"));

        }
        //get list of all players
        List<Player> players = playerService.findAll();
        //add attributes to model and page_banner
        model.addAttribute("players", players.subList(0, Math.min(4, players.size())));
        model.addAttribute("pageTitle", "Players");
        return "players";


    }

    @GetMapping("/players/stadistics")
    public String getPlayersStadistics(Model model,HttpServletRequest request){
        //get session id
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            String name_session = principal.getName();
            User user = userRepository.findByName(name_session).orElseThrow();
            model.addAttribute("username", user.getName());
            if (request.isUserInRole("ADMIN")){
                model.addAttribute("admin", request.isUserInRole("ADMIN"));
                model.addAttribute("user", request.isUserInRole("USER"));
            }else
                model.addAttribute("user", request.isUserInRole("USER"));

        }
        //get list of all players
        List<Player> players = playerService.findAll();
        //sort players by goals scored
        players.sort(Comparator.comparingInt(Player::getGoals).reversed());

        if (players.size() > 10) {
            players = players.subList(0, 10);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String playersJson = "";
        try {
            playersJson = objectMapper.writeValueAsString(players);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("playersJson", playersJson);
        model.addAttribute("pageTitle", "Players Stadistics");
        return "playersStadistics";
    }

}
