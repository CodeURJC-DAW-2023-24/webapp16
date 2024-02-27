package es.codeurjc.backend.sampleData;


import es.codeurjc.backend.model.Matches;
import es.codeurjc.backend.model.Team;
import es.codeurjc.backend.model.Player;
import es.codeurjc.backend.model.Tournament;
import es.codeurjc.backend.repository.MatchRepository;
import es.codeurjc.backend.repository.PlayerRepository;
import es.codeurjc.backend.repository.TeamRepository;
import es.codeurjc.backend.repository.TournamentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleAllData {

    @Autowired
    private TournamentRepository tournaments;
    @Autowired
    private PlayerRepository players;
    @Autowired
    private TeamRepository teams;
    @Autowired
    private MatchRepository matches;

    private List<Team>  listTeam;
    private List<Team>  listTeam2;
    private List<Player> listPlayer;

    @PostConstruct
    public void init(){

            //Tournaments creation
            Tournament champions = new Tournament("Madrid", "Futbol 7", "ChampionsLeague");
            tournaments.save(champions);

            Tournament copaDelRey = new Tournament("Sevilla", "Futbol 7", "CopadelRey");
            tournaments.save(copaDelRey);

            Tournament europaLeague = new Tournament("Valencia", "Futbol 7", "EuropaLeague");
            tournaments.save(europaLeague);

            Tournament superCopa = new Tournament("Cantabria", "Futbol 7", "SuperCopa");
            tournaments.save(superCopa);


            //Teams creation
            Team realMadrid = new Team("Real Madrid", "Mourinho", "Santiago Bernabeu", champions, 0 ,0, 0,"https://banner2.cleanpng.com/20180602/psw/kisspng-real-madrid-c-f-uefa-champions-league-la-liga-juv-5b1351b072b362.2456057615279927524698.jpg");


            realMadrid.setImageFile(realMadrid.URLtoBlob(realMadrid.getImagePath()));
             teams.save(realMadrid);

            Team barcelona = new Team("FC Barcelona", "Guardiola", "Camp Nou", champions, 0 ,0, 0, "https://i.pngimg.me/thumb/f/720/m2i8A0A0d3m2G6i8.jpg");

            barcelona.setImageFile(realMadrid.URLtoBlob(barcelona.getImagePath()));
        teams.save(barcelona);


            Team atleticoMadrid = new Team("Atletico de Madrid", "Simeone", "Wanda Metropolitano",champions, 0 ,0, 0, "https://i.pinimg.com/736x/3b/f8/ae/3bf8aefe1389dd4225cdd53b8d5d9dbd.jpg");

            atleticoMadrid.setImageFile(realMadrid.URLtoBlob(atleticoMadrid.getImagePath()));
        teams.save(atleticoMadrid);


            Team sevilla = new Team("Sevilla FC", "Lopetegui", "Estadio Ramón Sánchez-Pizjuán", champions, 0 ,0, 0 , "https://seeklogo.com/images/S/sevilla-logo-782E40BDB6-seeklogo.com.png");

            sevilla.setImageFile(realMadrid.URLtoBlob(sevilla.getImagePath()));
        teams.save(sevilla);

            Team valencia = new Team("Valencia CF", "Javi Gracia", "Mestalla" ,champions, 0 ,0, 0, "https://w1.pngwing.com/pngs/103/895/png-transparent-dream-league-soccer-logo-valencia-cf-uefa-champions-league-football-real-madrid-cf-la-liga-cristiano-ronaldo-yellow.png");

            valencia.setImageFile(realMadrid.URLtoBlob(valencia.getImagePath()));
        teams.save(valencia);

            Team villarreal = new Team("Villarreal CF", "Emery", "Estadio de la Cerámica",champions, 0 ,0, 0, "https://e7.pngegg.com/pngimages/692/211/png-clipart-villarreal-cf-c-villarreal-cf-b-football-football-sport-logo.png");

            villarreal.setImageFile(realMadrid.URLtoBlob(villarreal.getImagePath()));
        teams.save(villarreal);

            Team realSociedad = new Team("Real Sociedad", "Alguacil", "Reale Arena",champions, 0 ,0, 0, "https://banner2.cleanpng.com/20180621/zau/kisspng-real-sociedad-b-la-liga-spain-fc-barcelona-spain-football-5b2b49cf2d5129.0535263415295635991856.jpg");

            realSociedad.setImageFile(realMadrid.URLtoBlob(realSociedad.getImagePath()));
        teams.save(realSociedad);

            Team athleticBilbao = new Team("Athletic Bilbao", "Marcelino", "San Mamés", champions, 0 ,0, 0 , "https://w7.pngwing.com/pngs/144/152/png-transparent-athletic-bilbao-athletic-club-real-sociedad-deportivo-de-la-coruna-fitness-club-emblem-text-logo-thumbnail.png");

            athleticBilbao.setImageFile(realMadrid.URLtoBlob(athleticBilbao.getImagePath()));
        teams.save(athleticBilbao);

            Team levante = new Team("Levante UD", "Paco López", "Estadi Ciutat de València", copaDelRey, 0 ,0, 0 , "https://banner2.cleanpng.com/20180528/llv/kisspng-levante-ud-la-liga-spain-getafe-cf-mlaga-cf-5b0bee0bbb2fe3.1733109815275084917667.jpg");

            levante.setImageFile(realMadrid.URLtoBlob(levante.getImagePath()));
        teams.save(levante);

            Team getafe = new Team("Getafe CF", "Bordalás", "Coliseum Alfonso Pérez",copaDelRey, 0 ,0, 0  , "https://upload.wikimedia.org/wikipedia/an/b/b8/Getafe_CF.png");

            getafe.setImageFile(realMadrid.URLtoBlob(getafe.getImagePath()));
        teams.save(getafe);

            Team eibar = new Team("Eibar", "Mendilibar", "Estadio Municipal de Ipurua",copaDelRey, 0 ,0, 0, "https://www.intervinilo.com/images/stories/virtuemart/product/Eibar.png");

            eibar.setImageFile(realMadrid.URLtoBlob(eibar.getImagePath()));
        teams.save(eibar);

            Team celtaVigo = new Team("Celta de Vigo", "Coudet", "Estadio de Balaídos",copaDelRey, 0 ,0, 0, "https://logowik.com/content/uploads/images/rc-celta-de-vigo6992.jpg");

        celtaVigo.setImageFile(realMadrid.URLtoBlob(celtaVigo.getImagePath()));
        teams.save(celtaVigo);

            Team granada = new Team("Granada CF", "Díaz", "Nuevo Los Cármenes",copaDelRey, 0 ,0, 0, "https://upload.wikimedia.org/wikipedia/commons/a/aa/Escudo_Granada_club_de_f%C3%BAtbol.png");

        granada.setImageFile(realMadrid.URLtoBlob(granada.getImagePath()));
        teams.save(granada);

            Team realBetis = new Team("Real Betis", "Pellegrini", "Estadio Benito Villamarín" ,copaDelRey, 0 ,0, 0, "https://banner2.cleanpng.com/20180616/vec/kisspng-real-betis-real-sociedad-real-madrid-c-f-la-liga-betis-5b25a9819e77f8.2921612415291948816491.jpg");

        realBetis.setImageFile(realMadrid.URLtoBlob(realBetis.getImagePath()));
        teams.save(realBetis);

            Team huesca = new Team("SD Huesca", "Pacheta", "Estadio El Alcoraz", copaDelRey, 0 ,0, 0,"https://statics-maker.llt-services.com/hue/images/2023/03/06/optimized/cb28a1e0-75ee-451c-b810-dc4660d1d4d4-385.png");

        huesca.setImageFile(realMadrid.URLtoBlob(huesca.getImagePath()));
        teams.save(huesca);

            Team osasuna = new Team("Osasuna", "Arrasate", "Estadio El Sadar", copaDelRey, 0 ,0, 0, "https://upload.wikimedia.org/wikipedia/commons/3/39/CA_Osasuna.png" +
                    "");

        osasuna.setImageFile(realMadrid.URLtoBlob(osasuna.getImagePath()));
        teams.save(osasuna);



            //PlayerCreation
            players.save(new Player("Lionel", "Messi", "34", 10, "Argentino", "0", "forward", "72 kg", "1.70",realMadrid));
            players.save(new Player("Neymar", "Jr", "30", 10, "Brasileño", "0", "forward", "68 kg", "1.75",realMadrid));
            players.save(new Player("Kylian", "Mbappé", "23", 7, "Francés", "0", "forward", "73 kg", "1.78",realMadrid));
            players.save(new Player("Robert", "Lewandowski", "33", 9, "Polaco", "0", "forward", "80 kg", "1.84",realMadrid));
            players.save(new Player("Gianluigi", "Donnarumma", "23", 1, "Italiano", "0", "goalkeeper", "90 kg", "1.96",realMadrid));
            players.save(new Player("Alisson", "Becker", "29", 1, "Brasileño", "0", "goalkeeper", "91 kg", "1.91",realMadrid));
            players.save(new Player("Jan", "Oblak", "29", 1, "Esloveno", "0", "goalkeeper", "87 kg", "1.88",realMadrid));
            players.save(new Player("Joshua", "Kimmich", "27", 6, "Alemán", "3", "defender", "73 kg", "1.76",eibar));
            players.save(new Player("Trent", "Alexander-Arnold", "23", 66, "Inglés", "3", "defender", "69 kg", "1.80",eibar));
            players.save(new Player("Marquinhos", "dos Santos", "27", 5, "Brasileño", "1", "defender", "75 kg", "1.83",eibar));
            players.save(new Player("Frenkie", "de Jong", "24", 21, "Holandés", "4", "midfielder", "74 kg", "1.81",eibar));
            players.save(new Player("Casemiro", "Ferreira", "30", 14, "Brasileño", "3", "midfielder", "84 kg", "1.85",eibar));
            players.save(new Player("Paul", "Pogba", "29", 6, "Francés", "1", "midfielder", "84 kg", "1.91",eibar));
            players.save(new Player("Karim", "Benzema", "34", 9, "Francés", "10", "forward", "81 kg", "1.85",eibar));
            players.save(new Player("Sergio", "Ramos", "35", 4, "Español", "1", "defender", "82 kg", "1.84",levante));
            players.save(new Player("Virgil", "van Dijk", "30", 4, "Holandés", "1", "defender", "92 kg", "1.93",levante));
            players.save(new Player("Kevin", "De Bruyne", "30", 17, "Belga", "5", "midfielder", "70 kg", "1.81",levante));
            players.save(new Player("Luka", "Modrić", "36", 10, "Croata", "2", "midfielder", "66 kg", "1.72",levante));
            players.save(new Player("N'Golo", "Kanté", "31", 7, "Francés", "3", "midfielder", "70 kg", "1.68",levante));
            players.save(new Player("Eden", "Hazard", "31", 7, "Belga", "3", "forward", "74 kg", "1.75",levante));
            players.save(new Player("Mohamed", "Salah", "29", 11, "Egipcio", "8", "forward", "71 kg", "1.75",levante));
            players.save(new Player("Raheem", "Sterling", "27", 7, "Inglés", "3", "forward", "69 kg", "1.70",getafe));
            players.save(new Player("Harry", "Kane", "29", 9, "Inglés", "5", "forward", "86 kg", "1.88",getafe));
            players.save(new Player("Erling", "Haaland", "21", 9, "Noruego", "4", "forward", "87 kg", "1.94",getafe));
            players.save(new Player("Manuel", "Neuer", "36", 1, "Alemán", "0", "goalkeeper", "93 kg", "1.93",getafe));
            players.save(new Player("Thibaut", "Courtois", "30", 1, "Belga", "0", "goalkeeper", "96 kg", "1.99",getafe));
            players.save(new Player("Keylor", "Navas", "35", 1, "Costarricense", "0", "goalkeeper", "85 kg", "1.85",getafe));
            players.save(new Player("João", "Cancelo", "28", 27, "Portugués", "2", "defender", "74 kg", "1.82",getafe));
            players.save(new Player("Andrew", "Robertson", "28", 26, "Escocés", "3", "defender", "70 kg", "1.78",osasuna));
            players.save(new Player("David", "Alaba", "29", 4, "Austríaco", "2", "defender", "82 kg", "1.80",osasuna));
            players.save(new Player("Marco", "Verratti", "29", 6, "Italiano", "2", "midfielder", "60 kg", "1.65",osasuna));
            players.save(new Player("Saul", "Niguez", "27", 17, "Español", "1", "midfielder", "76 kg", "1.84",osasuna));
            players.save(new Player("Giovanni", "Lo Celso", "26", 18, "Argentino", "1", "midfielder", "68 kg", "1.77",osasuna));
            players.save(new Player("Mohamed", "Elyounoussi", "27", 22, "Noruego", "6", "forward", "76 kg", "1.79",osasuna));
            players.save(new Player("Memphis", "Depay", "28", 9, "Holandés", "5", "forward", "78 kg", "1.76",osasuna));
            players.save(new Player("Marcus", "Rashford", "24", 10, "Inglés", "2", "forward", "70 kg", "1.80",huesca));
            players.save(new Player("Luis", "Suarez", "35", 9, "Uruguayo", "7", "forward", "86 kg", "1.82",huesca));
            players.save(new Player("Jamie", "Vardy", "35", 9, "Inglés", "3", "forward", "74 kg", "1.79",huesca));
            players.save(new Player("Sadio", "Mane", "30", 10, "Senegalés", "8", "forward", "69 kg", "1.75",huesca));
            players.save(new Player("Romelu", "Lukaku", "29", 9, "Belga", "8", "forward", "94 kg", "1.91",huesca));
            players.save(new Player("Karim", "Adeyemi", "20", 9, "Alemán", "4", "forward", "76 kg", "1.79",huesca));
            players.save(new Player("Ansu", "Fati", "19", 22, "Español", "5", "forward", "66 kg", "1.78",huesca));
            players.save(new Player("Phil", "Foden", "22", 47, "Inglés", "4", "forward", "70 kg", "1.71",huesca));
            players.save(new Player("Vinicius", "Junior", "22", 20, "Brasileño", "7", "forward", "73 kg", "1.76",celtaVigo));




            //Matches Creation

            matches.save(new Matches(osasuna,levante,copaDelRey , 0,0, "2023-12-03",1));
            matches.save(new Matches(getafe,eibar,copaDelRey , 0,0, "2023-12-03",1));
            matches.save(new Matches(granada,celtaVigo,copaDelRey , 0,0, "2023-12-03",1));
            matches.save(new Matches(huesca,realBetis,copaDelRey , 0,0, "2023-12-03",1));
            matches.save(new Matches(realMadrid,barcelona,champions , 0,0, "2023-12-03",1));
            matches.save(new Matches(athleticBilbao,realSociedad,champions , 0,0, "2023-12-03",1));
            matches.save(new Matches(atleticoMadrid,sevilla,champions , 0,0, "2023-12-03",1));
            matches.save(new Matches(villarreal,valencia,champions , 0,0, "2023-12-03",1));
    }


}
