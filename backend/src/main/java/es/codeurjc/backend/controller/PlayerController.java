package es.codeurjc.backend.controller;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
