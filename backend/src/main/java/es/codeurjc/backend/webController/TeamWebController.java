package es.codeurjc.backend.webController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.model.*;
import es.codeurjc.backend.repository.UserRepository;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.service.TournamentService;
import es.codeurjc.backend.utils.BlobConverter;
import es.codeurjc.backend.utils.UserInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Controller
public class TeamWebController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoUtil userInfoUtil;
    @Autowired
    private BlobConverter blobConverter;
    @GetMapping("/teams")
    public String showTeams(Model model, HttpServletRequest request) throws SQLException {

        //get session id
        userInfoUtil.addUserInfoToModel(model, request);

        //get list of all teams
        List<Team> teams = teamService.findAll();
        //convert imageFile:blob to String and set it on imagePath on base64
        for(int i=0;i<4;i++){
            teams.get(i).setImagePath(teams.get(i).blobToString(teams.get(i).getImageFile(), teams.get(i)));
        }

        model.addAttribute("teams", teams.subList(0, Math.min(4, teams.size())));
        model.addAttribute("pageTitle", "Teams");
        return "teams";
    }




    @GetMapping("/teams/{name}")
    public String showTeamInfo(@PathVariable String name, Model model , HttpServletRequest request) {


        //get session id
        userInfoUtil.addUserInfoToModel(model, request);

        //get team by name in path
        Team team = teamService.findTeamByName(name);
        if (team == null) {
            model.addAttribute("entityName", "team");
            return "EntityNotFound";
        }
        //get list od players by team.id
        List<Player> players = playerService.findPlayersTeamById(team.getId());
        //add team and players to model
        model.addAttribute("team", team);
        model.addAttribute("players", players);
        //add pageTitle for page_banner
        model.addAttribute("pageTitle", team.getName());

        return "teamInfo";

    }
    @GetMapping("/tournamentCreation/{cup}/teamCreation/{teamNumber}")
    public String createTournamentTeam(@PathVariable String cup, Model model){

        model.addAttribute("cup",cup);
        return "teamCreate";
    }
    @GetMapping("/teams/stadistics")
    public String getTeamsStadistics(Model model, HttpServletRequest request) {
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
        model.addAttribute("pageTitle", "Teams Stadistics");
        return "teamsStadistics";
    }

    @PostMapping("/addTeamToTournament/{cup}")
    public String addTeamToTournament(@PathVariable String cup, Model model, //Request for team creation data
                                      @RequestParam String field_1, //Team name
                                      @RequestParam String field_2, //Coach name
                                      @RequestParam String field_3, //Stadium
                                      @RequestParam String field_4, //Team colors ___USELESS___
                                      //Player 1
                                      @RequestParam String field_5, //Name and surname
                                      @RequestParam String field_6, //Age
                                      @RequestParam String field_7, //T-shirt nº
                                      @RequestParam String field_8, //Nacionality
                                      @RequestParam String field_9, //Weight
                                      @RequestParam String field_10, //Height
                                      @RequestParam String field_11, //Position
                                      //Player 2
                                      @RequestParam String field_12, //Name and surname
                                      @RequestParam String field_13, //Age
                                      @RequestParam String field_14, //T-shirt nº
                                      @RequestParam String field_15, //Nacionality
                                      @RequestParam String field_16, //Weight
                                      @RequestParam String field_17, //Height
                                      @RequestParam String field_18, //Position
                                      //Player 3
                                      @RequestParam String field_19, //Name and surname
                                      @RequestParam String field_20, //Age
                                      @RequestParam String field_21, //T-shirt nº
                                      @RequestParam String field_22, //Nacionality
                                      @RequestParam String field_23, //Weight
                                      @RequestParam String field_24, //Height
                                      @RequestParam String field_25, //Position
                                      //Player 4
                                      @RequestParam String field_26, //Name and surname
                                      @RequestParam String field_27, //Age
                                      @RequestParam String field_28, //T-shirt nº
                                      @RequestParam String field_29, //Nacionality
                                      @RequestParam String field_30, //Weight
                                      @RequestParam String field_31, //Height
                                      @RequestParam String field_32, //Position
                                      //Player 5
                                      @RequestParam String field_33, //Name and surname
                                      @RequestParam String field_34, //Age
                                      @RequestParam String field_35, //T-shirt nº
                                      @RequestParam String field_36, //Nacionality
                                      @RequestParam String field_37, //Weight
                                      @RequestParam String field_38, //Height
                                      @RequestParam String field_39, //Position
                                      //Player 6
                                      @RequestParam String field_40, //Name and surname
                                      @RequestParam String field_41, //Age
                                      @RequestParam String field_42, //T-shirt nº
                                      @RequestParam String field_43, //Nacionality
                                      @RequestParam String field_44, //Weight
                                      @RequestParam String field_45, //Height
                                      @RequestParam String field_46, //Position
                                      //Player 7
                                      @RequestParam String field_47, //Name and surname
                                      @RequestParam String field_48, //Age
                                      @RequestParam String field_49, //T-shirt nº
                                      @RequestParam String field_50, //Nacionality
                                      @RequestParam String field_51, //Weight
                                      @RequestParam String field_52, //Height
                                      @RequestParam String field_53, //Position
                                      @RequestParam MultipartFile photo
    ){
        //Team(String name, String coach, String stadium, Tournament tournament, int gamesPlayed, int wins, int loses, String imagePath)
        Tournament tournament = tournamentService.findTournamentById(Long.parseLong(cup));
        Team newTeam = new Team(field_1,field_2,field_3,tournament,0,0,0,null);
        if (!photo.isEmpty()) {
            try {
                Blob blob = this.blobConverter.fileToBlob(photo.getBytes());
                newTeam.setImageFile(blob);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        field_7=field_7.trim().replaceAll("[^0-9]", "");
        field_14=field_14.trim().replaceAll("[^0-9]", "");
        field_21=field_21.trim().replaceAll("[^0-9]", "");
        field_28=field_28.trim().replaceAll("[^0-9]", "");
        field_35=field_35.trim().replaceAll("[^0-9]", "");
        field_42=field_42.trim().replaceAll("[^0-9]", "");
        field_49=field_49.trim().replaceAll("[^0-9]", "");

        String[] nameArray;
        String name;
        StringBuilder surname = new StringBuilder();
        nameArray = field_5.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            if (i==nameArray.length-1){
                surname.append(nameArray[i]);
            }else{
            surname.append(nameArray[i]+" ");
            }
        }
        Player player1 = new Player(name,surname.toString(),field_6,Integer.parseInt(field_7), field_8, 0, field_11, field_9, field_10, newTeam);
        surname.setLength(0);
        nameArray = field_12.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            if (i==nameArray.length-1){
                surname.append(nameArray[i]);
            }else{
                surname.append(nameArray[i]+" ");
            }
        }
        Player player2 = new Player(name,surname.toString(),field_13,Integer.parseInt(field_14), field_15, 0, field_18, field_16, field_17, newTeam);
        surname.setLength(0);
        nameArray = field_19.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            if (i==nameArray.length-1){
                surname.append(nameArray[i]);
            }else{
                surname.append(nameArray[i]+" ");
            }
        }
        Player player3 = new Player(name,surname.toString(),field_20,Integer.parseInt(field_21), field_22, 0, field_25, field_23, field_24, newTeam);
        surname.setLength(0);
        nameArray = field_26.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            if (i==nameArray.length-1){
                surname.append(nameArray[i]);
            }else{
                surname.append(nameArray[i]+" ");
            }
        }
        Player player4 = new Player(name,surname.toString(),field_27,Integer.parseInt(field_28), field_29, 0, field_32, field_30, field_31, newTeam);
        surname.setLength(0);
        nameArray = field_33.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            if (i==nameArray.length-1){
                surname.append(nameArray[i]);
            }else{
                surname.append(nameArray[i]+" ");
            }
        }
        Player player5 = new Player(name,surname.toString(),field_34,Integer.parseInt(field_35), field_36, 0, field_39, field_37, field_38, newTeam);
        surname.setLength(0);
        nameArray = field_40.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            if (i==nameArray.length-1){
                surname.append(nameArray[i]);
            }else{
                surname.append(nameArray[i]+" ");
            }
        }
        Player player6 = new Player(name,surname.toString(),field_41,Integer.parseInt(field_42), field_43, 0, field_46, field_44, field_45, newTeam);
        surname.setLength(0);
        nameArray = field_47.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            if (i==nameArray.length-1){
                surname.append(nameArray[i]);
            }else{
                surname.append(nameArray[i]+" ");
            }
        }
        Player player7 = new Player(name,surname.toString(),field_48,Integer.parseInt(field_49), field_50, 0, field_53, field_51, field_52, newTeam);
        teamService.save(newTeam);
        playerService.save(player1);
        playerService.save(player2);
        playerService.save(player3);
        playerService.save(player4);
        playerService.save(player5);
        playerService.save(player6);
        playerService.save(player7);
        return ("redirect:/tournamentCreation/1");
    }
    @PostMapping("/addTeam/{tourNumber}")
    public String addTeam(@RequestBody List<ImputData> dataList, @PathVariable String tourNumber){

    return "redirect:/tournamentCreation";
    }
}