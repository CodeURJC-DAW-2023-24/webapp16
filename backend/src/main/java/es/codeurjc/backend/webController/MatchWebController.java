package es.codeurjc.backend.webController;

import es.codeurjc.backend.model.*;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.ReportService;
import es.codeurjc.backend.utils.UserInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Controller
public class MatchWebController {
    @Autowired
    private MatchService matchService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoUtil userInfoUtil;

    @GetMapping("/tournament/{cup}/{id}")
    public String showMatch(Model model, @PathVariable String cup, @PathVariable  Long id, HttpServletRequest request) throws SQLException {
        //get session id
        userInfoUtil.addUserInfoToModel(model, request);

        //get tournament by cup in path
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        if (tournament == null) {
            model.addAttribute("entityName", "tournament");
            return "EntityNotFound";
        }
        //get Match by id in path
        Matches match = matchService.findMatchById(id);
        if (match == null) {
            model.addAttribute("entityName", "match");
            return "EntityNotFound";
        }
        //get local and visitin teams of that match
        Team localTeam = match.getLocalTeam();
        Team visitingTeam = match.getVisitingTeam();
        //get list of players of both teams by team_id
        List <Player> playerListLocal = playerService.findPlayersTeamById(localTeam.getId());
        List <Player> playerListVisiting = playerService.findPlayersTeamById(visitingTeam.getId());
        //set images in base64
        localTeam.setImagePath(localTeam.getImageAsString());
        visitingTeam.setImagePath(visitingTeam.getImageAsString());

        //check if report exists
        Report report =  reportService.findReportByMatchId(id);
        if (report != null) {
            model.addAttribute("report", TRUE);
        }
        //add attributes to Model
        model.addAttribute("localTeam",localTeam);
        model.addAttribute("visitingTeam", visitingTeam);
        model.addAttribute("playerListLocal", playerListLocal);
        model.addAttribute("playerListVisiting", playerListVisiting);
        //add pageTitle to page_banner
        String pagePath = localTeam.getName() + " vs " + visitingTeam.getName();
        model.addAttribute("pageTitle", pagePath);

        return "matchScreen";
    }


}
