package es.codeurjc.backend.DTOs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.codeurjc.backend.model.Team;

public class PlayerDTO {
    private Long id;
    private String name;
    private String lastName;
    //private String teamName;
    private String age;
    private int jerseyNumber;
    private String nationality;
    private int goals;
    private String position;
    private String weight;
    private String height;

    @JsonBackReference
    private Team team;

    // Constructor
    public PlayerDTO(Long id, String name, String lastName, String age, int jerseyNumber,
                     String nationality, int goals, String position, String weight, String height, Team team) {
        this.id = id;
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
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

/*
   // public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
*/

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

