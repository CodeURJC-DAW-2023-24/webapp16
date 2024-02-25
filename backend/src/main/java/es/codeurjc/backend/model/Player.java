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
    private String nationality;
    private String goals;
    private String position;
    private String weight;
    private String height;
    
    @ManyToOne
    private Team team;
    public Player(){}
    public Player( String name, String lastName, String age, int jerseyNumber, String nationality, String goals, String position, String weight, String height, Team team) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.jerseyNumber = jerseyNumber;
        this.nationality = nationality;
        this.goals = goals;
        this.position = position;
        this.weight = weight;
        this.height = height;
        this.team = team;
    }


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

    public String getNationality() {
      return this.nationality;
    }
    public void setNationality(String value) {
      this.nationality = value;
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