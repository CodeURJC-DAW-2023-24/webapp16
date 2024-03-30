package es.codeurjc.backend.webController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.utils.UserInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatisticsWebController {
    @Autowired
    private UserInfoUtil userInfoUtil;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/players/statistics")
    public String getPlayersStatistics (Model model, HttpServletRequest request){
        //get session id
        userInfoUtil.addUserInfoToModel(model, request);
        //get list of all players
        List<Player> players = playerService.findTopPlayersByGoals(10);

        ObjectMapper objectMapper = new ObjectMapper();
        String playersJson = "";
        try {
            playersJson = objectMapper.writeValueAsString(players);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("playersJson", playersJson);
        model.addAttribute("pageTitle", "Players Statistics");
        return "playersStatistics";
    }
    @GetMapping("/teams/statistics")
    public String getTeamsStatistics(Model model, HttpServletRequest request) {
        //get session id
        userInfoUtil.addUserInfoToModel(model, request);

        List<Team> teams = teamService.getTopTeamsByWins(10);

        ObjectMapper objectMapper = new ObjectMapper();
        String teamsJson = "";
        try {
            teamsJson = objectMapper.writeValueAsString(teams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("teamsJson", teamsJson);
        model.addAttribute("pageTitle", "Teams Statistics");
        return "teamsStatistics";
    }
}
