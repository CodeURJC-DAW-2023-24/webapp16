package es.codeurjc.backend.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.codeurjc.backend.model.ImputData;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.service.PlayerService;
import es.codeurjc.backend.service.TeamService;
import es.codeurjc.backend.service.TournamentService;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Blob;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Base64;

@Controller
public class TeamControler {

    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TournamentService tournamentService;
    @GetMapping("/teams")
    public String showTeams(Model model) throws SQLException {

        List<Team> teams = teamService.findAll();

        //poner en el html "src= "data:image/png, base64; {{teamImage}}"
        for(int i=0;i<4;i++){
           // System.out.println("Este es el equipo, " + teams.get(i).getName()+ "   "+ teams.get(i).getImagePath());
            teams.get(i).setImagePath(teams.get(i).blobToString(teams.get(i).getImageFile(), teams.get(i)));
          //  System.out.println("Este es el equipo, " + teams.get(i).getName()+ "  este es el path modificado  "+ teams.get(i).getImagePath());

        }

        model.addAttribute("teams", teams.subList(0, Math.min(4, teams.size())));
        model.addAttribute("pageTitle", "Teams");
        return "teams";
    }


    @GetMapping("/api/teams")
    @ResponseBody
    public List<Team> getTeams(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "4") int pageSize) throws SQLException {
        Page<Team> teamsPage = teamService.findAllTeams(PageRequest.of(page, pageSize));

        for (Team currentTeam : teamsPage.getContent()) {
            currentTeam.setImagePath(currentTeam.blobToString(currentTeam.getImageFile(), currentTeam));
        }

        return teamsPage.getContent();
    }


    @GetMapping("/teams/{name}")
    public String showTeamInfo(@PathVariable String name, Model model) {
        Team team = teamService.findTeamByName(name);

        List<Player> players = playerService.findPlayerTeamById(team.getId());

        model.addAttribute("team", team);
        model.addAttribute("players", players);

        model.addAttribute("pageTitle", team.getName());

        return "teamInfo";

    }
    @GetMapping("/teams/{name}/{playerName}/{lastName}")
    public String showPlayerInfo(@PathVariable String name, @PathVariable String playerName,@PathVariable String lastName, Model model) {

        Team team = teamService.findTeamByName(name);
        Player player = playerService.findPlayerByNameAndLastName(playerName,lastName);


        model.addAttribute("team", team);
        model.addAttribute("player", player);

        String pagePath = team.getName() + " / " + player.getName() + " " + player.getLastName();
        model.addAttribute("pageTitle", pagePath);

        return "playerInfo";

    }
    @GetMapping("/tournamentCreation/{cup}/teamCreation/{teamNumber}")
    public String createTournamentTeam(@PathVariable String cup, Model model){
        System.out.println("cup: "+cup);
        model.addAttribute("cup",cup);
        return "teamCreate";
    }
    @GetMapping("/teams/stadistics")
    public String getTeamsStadistics(Model model) {


        List<Team> teams = teamService.findAll();

        teams.sort(Comparator.comparingInt(Team::getWins).reversed());

        if (teams.size() > 10) {
            teams = teams.subList(0, 10);
        }

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
                                      @RequestParam String field_53 //Position
    ){
        //Team(String name, String coach, String stadium, Tournament tournament, int gamesPlayed, int wins, int loses, String imagePath)
        Tournament tournament = tournamentService.findTournamentById(Long.parseLong(cup));
        Team newTeam = new Team(field_1,field_2,field_3,tournament,0,0,0,null);

        String[] nameArray;
        String name;
        StringBuilder surname = new StringBuilder();
        // public Player( String name, String lastName, String age, int jerseyNumber, String nationality, int goals, String position, String weight, String height, Team team)
        nameArray = field_5.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            surname.append(nameArray[i]);
        }
        field_7=field_7.trim().replaceAll("[^0-9]", "");
        field_14=field_7.trim().replaceAll("[^0-9]", "");
        field_21=field_7.trim().replaceAll("[^0-9]", "");
        field_28=field_7.trim().replaceAll("[^0-9]", "");
        field_35=field_7.trim().replaceAll("[^0-9]", "");
        field_42=field_7.trim().replaceAll("[^0-9]", "");
        field_49=field_7.trim().replaceAll("[^0-9]", "");
        System.out.println(field_7);
        Player player1 = new Player(name,surname.toString(),field_6,Integer.parseInt(field_7), field_8, 0, field_11, field_9, field_10, newTeam);
        nameArray = field_12.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            surname.append(nameArray[i]);
        }
        Player player2 = new Player(name,surname.toString(),field_13,Integer.parseInt(field_14), field_15, 0, field_18, field_16, field_17, newTeam);
        nameArray = field_19.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            surname.append(nameArray[i]);
        }
        Player player3 = new Player(name,surname.toString(),field_20,Integer.parseInt(field_21), field_22, 0, field_25, field_23, field_24, newTeam);
        nameArray = field_26.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            surname.append(nameArray[i]);
        }
        Player player4 = new Player(name,surname.toString(),field_27,Integer.parseInt(field_28), field_29, 0, field_32, field_30, field_31, newTeam);
        nameArray = field_33.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            surname.append(nameArray[i]);
        }
        Player player5 = new Player(name,surname.toString(),field_34,Integer.parseInt(field_35), field_36, 0, field_39, field_37, field_38, newTeam);
        nameArray = field_40.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            surname.append(nameArray[i]);
        }
        Player player6 = new Player(name,surname.toString(),field_41,Integer.parseInt(field_42), field_43, 0, field_46, field_44, field_45, newTeam);
        nameArray = field_47.split(" ");
        name=nameArray[0];
        for (int i=1;i<nameArray.length ;i++){
            surname.append(nameArray[i]);
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