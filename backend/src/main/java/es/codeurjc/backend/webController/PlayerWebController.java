package es.codeurjc.backend.webController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.User;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.utils.UserInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class PlayerWebController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoUtil userInfoUtil;
    @GetMapping("/players")
    public String showPlayers(Model model,HttpServletRequest request){
        //get session id
        userInfoUtil.addUserInfoToModel(model, request);
        //get list of all players
        List<Player> players = playerService.findAll();
        //add attributes to model and page_banner
        model.addAttribute("players", players.subList(0, Math.min(4, players.size())));
        model.addAttribute("pageTitle", "Players");
        return "players";
    }
    @GetMapping("/players/{name}/{lastName}")
    public String showPlayerInfo(@PathVariable String name,
                                 @PathVariable String lastName,
                                 Model model,
                                 HttpServletRequest request) {
        // get session id
        userInfoUtil.addUserInfoToModel(model, request);

        // Search player
        Player player = playerService.findPlayerByNameAndLastName(name, lastName);

        // if null redirection to template
        if (player == null) {
            model.addAttribute("entityName", "player");
            return "EntityNotFound";
        }
        // Add to model
        model.addAttribute("player", player);

        String pagePath = player.getName() + " " + player.getLastName();
        model.addAttribute("pageTitle", pagePath);

        return "playerInfo";
    }


}
