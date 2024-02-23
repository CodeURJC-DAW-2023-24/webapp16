package es.codeurjc.backend.Controller;

import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.repository.PlayerRepository;
import es.codeurjc.backend.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testController")
public class TestController {
    @Autowired
    private PlayerRepository posts;
    @Autowired
    private TeamRepository postT;
    @PostConstruct
    public void init(){
        Team team = new Team();
        team.setCoach("A");
        team.setId(124L);
        team.setName("pepe's coach");
        team.setStadium("Navalcarnero");
        postT.save(team);
        posts.save(new Player("Pepe","García","36",5,"Español","2","centrokampista","89kg","1.80",team));
    }
}
