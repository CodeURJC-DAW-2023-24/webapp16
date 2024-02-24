package es.codeurjc.backend.model;




import java.util.List;

import jakarta.persistence.*;
import es.codeurjc.backend.model.Tournament;
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String coach;
    private String stadium;

    @ManyToOne
    private Tournament tournament;

    @OneToMany(mappedBy = "team")
    private List<Player> listPlayer;


    public Team(){}
    public Team(String name, String coach, String stadium, Tournament tournament) {
        this.name = name;
        this.coach = coach;
        this.stadium = stadium;
        this.tournament = tournament;

    }

    // Relación de uno a muchos con la clase player
    public Long getId() {
      return this.id;
    }
    public void setId(Long value) {
      this.id = value;
    }

    public String getName() {
      return this.name;
    }
    public void setName(String value) {
      this.name = value;
    }

    public String getCoach() {
      return this.coach;
    }
    public void setCoach(String value) {
      this.coach = value;
    }

    public String getStadium() {
      return this.stadium;
    }
    public void setStadium(String value) {
      this.stadium = value;
    }
}