package es.codeurjc.backend.model;


import java.util.List;

import jakarta.persistence.*;
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String coach;
    private String stadium;
    
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @OneToMany(mappedBy = "localTeam", cascade = CascadeType.ALL)
    private List<Matches> homeMatches;

    @OneToMany(mappedBy = "visitingTeam", cascade = CascadeType.ALL)
    private List<Matches> awayMatches;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> playersList;
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