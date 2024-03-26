package es.codeurjc.backend.DTOs;

public class TeamDTO {

    private Long id;
    private String name;
    private String coach;
    private String stadium;
    private int gamesPlayed;
    private int wins;
    private int loses;
    private String imagePath;

    // Constructor, getters y setters
    // Constructor vacío
    public TeamDTO() {}

    // Constructor con todos los campos
    public TeamDTO(Long id, String name, String coach, String stadium, int gamesPlayed, int wins, int loses, String imagePath) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.stadium = stadium;
        this.gamesPlayed = gamesPlayed;
        this.wins = wins;
        this.loses = loses;
        this.imagePath = imagePath;
    }

    // Getters y setters para todos los campos
    // También puedes generarlos automáticamente en tu IDE si lo prefieres

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

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
