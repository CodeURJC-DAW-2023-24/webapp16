package es.codeurjc.backend.model;


import jakarta.persistence.*;
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    // private String team;
    private String age;
    private int jerseyNumber;
    private String nacionality;
    private String goals;
    private String position;
    private String weight;
    private String height;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


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

    public String getLastName() {
      return this.lastName;
    }
    public void setLastName(String value) {
      this.lastName = value;
    }

    public Team getTeam() {
      return this.team;
    }
    public void setTeam(Team value) {
      this.team = value;
    }

    public String getAge() {
      return this.age;
    }
    public void setAge(String value) {
      this.age = value;
    }

    public int getJerseyNumber() {
      return this.jerseyNumber;
    }
    public void setJerseyNumber(int value) {
      this.jerseyNumber = value;
    }

    public String getNacionality() {
      return this.nacionality;
    }
    public void setNacionality(String value) {
      this.nacionality = value;
    }

    public String getGoals() {
      return this.goals;
    }
    public void setGoals(String value) {
      this.goals = value;
    }

    public String getPosition() {
      return this.position;
    }
    public void setPosition(String value) {
      this.position = value;
    }

    public String getWeight() {
      return this.weight;
    }
    public void setWeight(String value) {
      this.weight = value;
    }

    public String getHeight() {
      return this.height;
    }
    public void setHeight(String value) {
      this.height = value;
    }
}