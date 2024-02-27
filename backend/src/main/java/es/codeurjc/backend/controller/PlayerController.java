package es.codeurjc.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @GetMapping("/players")
    public String showPlayers(Model model){


        List<Player> players = playerService.findAll();

        model.addAttribute("players", players);
        model.addAttribute("pageTitle", "Players");
        return "players";


    }
    @GetMapping("/players/stadistics")
    public String getPlayersStadistics(Model model){

        List<Player> players = playerService.findAll();

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
