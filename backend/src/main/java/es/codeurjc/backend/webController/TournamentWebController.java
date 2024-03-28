package es.codeurjc.backend.webController;


import es.codeurjc.backend.model.*;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.MatchService;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.utils.BlobConverter;
import es.codeurjc.backend.utils.UserInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import es.codeurjc.backend.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Controller
public class TournamentWebController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired MatchService matchService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TeamService teamService;
    @Autowired
    PlayerService playerService;
    @Autowired
    private UserInfoUtil userInfoUtil;
    @Autowired
    private BlobConverter blobConverter;


    @GetMapping("/")
    public String showTournaments(Model model, HttpServletRequest request) throws SQLException {

        userInfoUtil.addUserInfoToModel(model, request);

        List<Tournament> tournaments = tournamentService.findAllTournaments();

         for(int i=0;i<tournaments.size();i++) {
             if (tournaments.get(i).getTournamentImageFile() != null) {
                 tournaments.get(i).setTournamentImagePath(tournaments.get(i).getTournamentImageAsString());
             }
             else
                 tournaments.remove(tournaments.get(i));
         }

        model.addAttribute("tournaments", tournaments);

        model.addAttribute("pageTitle", "Tournaments");
        return "index";


    }
    @GetMapping("/tournament/{cup}")
    public String showBracket(Model model, @PathVariable String cup,HttpServletRequest request){

        //get session id
        userInfoUtil.addUserInfoToModel(model, request);


        //get Tournament by cup name on URL
        Tournament tournament =  tournamentService.findTournamentByCup(cup);
        //get List of matches by rounds.
        List<Matches> roundOne = tournamentService.findRound(1, tournament.getId());
        List<Matches> roundTwo = tournamentService.findRound(2, tournament.getId());
        List<Matches> roundThree = tournamentService.findRound(3, tournament.getId());
        List<Matches> roundFour = tournamentService.findRound(4, tournament.getId());

        //attributes to mustache
        model.addAttribute("cup",cup);
        model.addAttribute("roundOne", roundOne);
        model.addAttribute("roundTwo", roundTwo);
        model.addAttribute("roundThree", roundThree);
        model.addAttribute("roundFour", roundFour);
        model.addAttribute("pageTitle", cup);

        return "tournamentBracket";
    }
    @GetMapping("/tournamentCreation/{tournamentNumber}/{teamNumber}")
    public String newTeam(Model model, @PathVariable String tournamentNumber, @PathVariable String teamNumber, @RequestParam String field_1, @RequestParam String field_2, @RequestParam String field_3){


        tournamentService.newTournament(field_1,field_2,field_3);
        return ("redirect:/tournamentCreation/"+tournamentNumber+"/teamCreation/"+teamNumber);
    }
    @GetMapping("/tournamentCreation/{created}")
    public String newTour(Model model, @PathVariable int created) {

        if(created==0) {
            model.addAttribute("newTourID", tournamentService.countTournaments() + 1);
            model.addAttribute("redirect", "/");
            model.addAttribute("showName1",false);
            model.addAttribute("showName2",false);
            model.addAttribute("showName3",false);
            model.addAttribute("showName4",false);
            model.addAttribute("showName5",false);
            model.addAttribute("showName6",false);
            model.addAttribute("showName7",false);
            model.addAttribute("showName8",false);
        }
        else{
            model.addAttribute("newTourID", tournamentService.countTournaments());
            model.addAttribute("redirect", "/cancelTournamentCreation");
            List<Team> teamList = teamService.findTournamentsByTourName(tournamentService.countTournaments());
            switch (teamList.size()){
                case 0:
                    model.addAttribute("showName1",false);
                    model.addAttribute("showName2",false);
                    model.addAttribute("showName3",false);
                    model.addAttribute("showName4",false);
                    model.addAttribute("showName5",false);
                    model.addAttribute("showName6",false);
                    model.addAttribute("showName7",false);
                    model.addAttribute("showName8",false);
                    break;
                case 1:
                    model.addAttribute("showName1",true);
                    model.addAttribute("name1",teamList.get(0).getName());
                    model.addAttribute("showName2",false);
                    model.addAttribute("showName3",false);
                    model.addAttribute("showName4",false);
                    model.addAttribute("showName5",false);
                    model.addAttribute("showName6",false);
                    model.addAttribute("showName7",false);
                    model.addAttribute("showName8",false);
                    break;
                case 2:
                    model.addAttribute("showName1",true);
                    model.addAttribute("name1",teamList.get(0).getName());
                    model.addAttribute("showName2",true);
                    model.addAttribute("name2",teamList.get(1).getName());
                    model.addAttribute("showName3",false);
                    model.addAttribute("showName4",false);
                    model.addAttribute("showName5",false);
                    model.addAttribute("showName6",false);
                    model.addAttribute("showName7",false);
                    model.addAttribute("showName8",false);
                    break;
                case 3:
                    model.addAttribute("showName1",true);
                    model.addAttribute("name1",teamList.get(0).getName());
                    model.addAttribute("showName2",true);
                    model.addAttribute("name2",teamList.get(1).getName());
                    model.addAttribute("showName3",true);
                    model.addAttribute("name3",teamList.get(2).getName());
                    model.addAttribute("showName4",false);
                    model.addAttribute("showName5",false);
                    model.addAttribute("showName6",false);
                    model.addAttribute("showName7",false);
                    model.addAttribute("showName8",false);
                    break;
                case 4:
                    model.addAttribute("showName1",true);
                    model.addAttribute("name1",teamList.get(0).getName());
                    model.addAttribute("showName2",true);
                    model.addAttribute("name2",teamList.get(1).getName());
                    model.addAttribute("showName3",true);
                    model.addAttribute("name3",teamList.get(2).getName());
                    model.addAttribute("showName4",true);
                    model.addAttribute("name4",teamList.get(3).getName());
                    model.addAttribute("showName5",false);
                    model.addAttribute("showName6",false);
                    model.addAttribute("showName7",false);
                    model.addAttribute("showName8",false);
                    break;
                case 5:
                    model.addAttribute("showName1",true);
                    model.addAttribute("name1",teamList.get(0).getName());
                    model.addAttribute("showName2",true);
                    model.addAttribute("name2",teamList.get(1).getName());
                    model.addAttribute("showName3",true);
                    model.addAttribute("name3",teamList.get(2).getName());
                    model.addAttribute("showName4",true);
                    model.addAttribute("name4",teamList.get(3).getName());
                    model.addAttribute("showName5",true);
                    model.addAttribute("name5",teamList.get(4).getName());
                    model.addAttribute("showName6",false);
                    model.addAttribute("showName7",false);
                    model.addAttribute("showName8",false);
                    break;
                case 6:
                    model.addAttribute("showName1",true);
                    model.addAttribute("name1",teamList.get(0).getName());
                    model.addAttribute("showName2",true);
                    model.addAttribute("name2",teamList.get(1).getName());
                    model.addAttribute("showName3",true);
                    model.addAttribute("name3",teamList.get(2).getName());
                    model.addAttribute("showName4",true);
                    model.addAttribute("name4",teamList.get(3).getName());
                    model.addAttribute("showName5",true);
                    model.addAttribute("name5",teamList.get(4).getName());
                    model.addAttribute("showName6",true);
                    model.addAttribute("name6",teamList.get(5).getName());
                    model.addAttribute("showName7",false);
                    model.addAttribute("showName8",false);
                    break;
                case 7:
                    model.addAttribute("showName1",true);
                    model.addAttribute("name1",teamList.get(0).getName());
                    model.addAttribute("showName2",true);
                    model.addAttribute("name2",teamList.get(1).getName());
                    model.addAttribute("showName3",true);
                    model.addAttribute("name3",teamList.get(2).getName());
                    model.addAttribute("showName4",true);
                    model.addAttribute("name4",teamList.get(3).getName());
                    model.addAttribute("showName5",true);
                    model.addAttribute("name5",teamList.get(4).getName());
                    model.addAttribute("showName6",true);
                    model.addAttribute("name6",teamList.get(5).getName());
                    model.addAttribute("showName7",true);
                    model.addAttribute("name7",teamList.get(6).getName());
                    model.addAttribute("showName8",false);
                    break;
                case 8:
                    model.addAttribute("showName1",true);
                    model.addAttribute("name1",teamList.get(0).getName());
                    model.addAttribute("showName2",true);
                    model.addAttribute("name2",teamList.get(1).getName());
                    model.addAttribute("showName3",true);
                    model.addAttribute("name3",teamList.get(2).getName());
                    model.addAttribute("showName4",true);
                    model.addAttribute("name4",teamList.get(3).getName());
                    model.addAttribute("showName5",true);
                    model.addAttribute("name5",teamList.get(4).getName());
                    model.addAttribute("showName6",true);
                    model.addAttribute("name6",teamList.get(5).getName());
                    model.addAttribute("showName7",true);
                    model.addAttribute("name7",teamList.get(6).getName());
                    model.addAttribute("showName8",true);
                    model.addAttribute("name8",teamList.get(7).getName());
                    break;
                default:
                    break;
            }
        }
        return "tournamentCreate";

    }
    @GetMapping("/cancelTournamentCreation")
    public String cancelTournament(Model model){
        Long tourId = tournamentService.countTournaments();
        for (Team team : teamService.findTournamentsByTourName(tourId)){
            playerService.deletePlayerByTeamId(team.getId());
            teamService.deleteTeam(team);
        }
        tournamentService.delete(tournamentService.findTournamentById(tournamentService.countTournaments()));
        return "redirect:/";
    }
    @PostMapping("/saveTournament")
    public String saveTournament(Model model, @RequestParam MultipartFile photo){
        Long tourId = tournamentService.countTournaments();
        List<Team> teamList = teamService.findTournamentsByTourName(tourId);
        for (int teamN=0; teamN< teamList.size(); teamN+=2){
            Matches matches = new Matches(teamList.get(teamN),teamList.get(teamN+1), tournamentService.findTournamentById(tourId),0,0,1);
            matchService.saveMatch(matches);
        }
        if (!photo.isEmpty()) {
            try {
                Blob blob = this.blobConverter.fileToBlob(photo.getBytes());
                Tournament tournament = tournamentService.findTournamentById(tourId);
                tournament.setTournamentImageFile(blob);
                tournamentService.save(tournament);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }


}
