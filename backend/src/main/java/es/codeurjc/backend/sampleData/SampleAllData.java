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

import java.sql.SQLException;
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


    @PostConstruct
    public void init() throws SQLException {

            //Tournaments creation
            Tournament champions = new Tournament("Madrid", "Futbol 7", "Champions League", "https://cdmosconia.es/wp-content/uploads/sites/167/2022/03/fuY4djdEU2SR.png");
            champions.setTournamentImageFile(champions.URLtoBlob(champions.getTournamentImagePath()));
            tournaments.save(champions);

            Tournament copaDelRey = new Tournament("Sevilla", "Futbol 7", "Copa del Rey", "https://upload.wikimedia.org/wikipedia/commons/9/94/Logo_Copa_del_Rey_2021_%28red_badge%29.png");
            copaDelRey.setTournamentImageFile(copaDelRey.URLtoBlob(copaDelRey.getTournamentImagePath()));
            tournaments.save(copaDelRey);



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

            Team osasuna = new Team("Osasuna", "Arrasate", "Estadio El Sadar", copaDelRey, 0 ,0, 0, "https://upload.wikimedia.org/wikipedia/commons/3/39/CA_Osasuna.png");
            osasuna.setImageFile(realMadrid.URLtoBlob(osasuna.getImagePath()));
            teams.save(osasuna);



            //PlayerCreation
            players.save(new Player("Lionel", "Messi", "34", 10, "Argentino", 0, "forward", "72 kg", "1.70",realMadrid));
            players.save(new Player("Neymar", "Jr", "30", 10, "Brasileño", 0, "forward", "68 kg", "1.75",realMadrid));
            players.save(new Player("Kylian", "Mbappé", "23", 7, "Francés", 0, "forward", "73 kg", "1.78",realMadrid));
            players.save(new Player("Robert", "Lewandowski", "33", 9, "Polaco", 0, "forward", "80 kg", "1.84",realMadrid));
            players.save(new Player("Gianluigi", "Donnarumma", "23", 1, "Italiano", 0, "goalkeeper", "90 kg", "1.96",realMadrid));
            players.save(new Player("Alisson", "Becker", "29", 1, "Brasileño", 0, "goalkeeper", "91 kg", "1.91",realMadrid));
            players.save(new Player("Jan", "Oblak", "29", 1, "Esloveno", 0, "goalkeeper", "87 kg", "1.88",realMadrid));

            players.save(new Player("Joshua", "Kimmich", "27", 6, "Alemán", 3, "defender", "73 kg", "1.76",eibar));
            players.save(new Player("Trent", "Alexander-Arnold", "23", 66, "Inglés", 3, "defender", "69 kg", "1.80",eibar));
            players.save(new Player("Marquinhos", "dos Santos", "27", 5, "Brasileño", 1, "defender", "75 kg", "1.83",eibar));
            players.save(new Player("Frenkie", "de Jong", "24", 21, "Holandés", 4, "midfielder", "74 kg", "1.81",eibar));
            players.save(new Player("Casemiro", "Ferreira", "30", 14, "Brasileño", 3, "midfielder", "84 kg", "1.85",eibar));
            players.save(new Player("Paul", "Pogba", "29", 6, "Francés", 1, "midfielder", "84 kg", "1.91",eibar));
            players.save(new Player("Karim", "Benzema", "34", 9, "Francés", 10, "forward", "81 kg", "1.85",eibar));

            players.save(new Player("Sergio", "Ramos", "35", 4, "Español", 1, "defender", "82 kg", "1.84",levante));
            players.save(new Player("Virgil", "van Dijk", "30", 4, "Holandés", 1, "defender", "92 kg", "1.93",levante));
            players.save(new Player("Kevin", "De Bruyne", "30", 17, "Belga", 6, "midfielder", "70 kg", "1.81",levante));
            players.save(new Player("Luka", "Modric", "36", 10, "Croata", 2, "midfielder", "66 kg", "1.72",levante));
            players.save(new Player("N'Golo", "Kanté", "31", 7, "Francés", 3, "midfielder", "70 kg", "1.68",levante));
            players.save(new Player("Eden", "Hazard", "31", 7, "Belga", 14, "forward", "74 kg", "1.75",levante));
            players.save(new Player("Mohamed", "Salah", "29", 11, "Egipcio", 2, "forward", "71 kg", "1.75",levante));

            players.save(new Player("Raheem", "Sterling", "27", 7, "Inglés", 6, "forward", "69 kg", "1.70",getafe));
            players.save(new Player("Harry", "Kane", "29", 9, "Inglés", 12, "forward", "86 kg", "1.88",getafe));
            players.save(new Player("Erling", "Haaland", "21", 9, "Noruego", 4, "forward", "87 kg", "1.94",getafe));
            players.save(new Player("Manuel", "Neuer", "36", 1, "Alemán", 0, "goalkeeper", "93 kg", "1.93",getafe));
            players.save(new Player("Thibaut", "Courtois", "30", 1, "Belga", 0, "goalkeeper", "96 kg", "1.99",getafe));
            players.save(new Player("Keylor", "Navas", "35", 1, "Costarricense", 0, "goalkeeper", "85 kg", "1.85",getafe));
            players.save(new Player("João", "Cancelo", "28", 27, "Portugués", 4, "defender", "74 kg", "1.82",getafe));

            players.save(new Player("Andrew", "Robertson", "28", 26, "Escocés", 7, "defender", "70 kg", "1.78",osasuna));
            players.save(new Player("David", "Alaba", "29", 4, "Austríaco", 13, "defender", "82 kg", "1.80",osasuna));
            players.save(new Player("Marco", "Verratti", "29", 6, "Italiano", 15, "midfielder", "60 kg", "1.65",osasuna));
            players.save(new Player("Saul", "Niguez", "27", 17, "Español", 5, "midfielder", "76 kg", "1.84",osasuna));
            players.save(new Player("Giovanni", "Lo Celso", "26", 18, "Argentino", 3, "midfielder", "68 kg", "1.77",osasuna));
            players.save(new Player("Mohamed", "Elyounoussi", "27", 22, "Noruego", 6, "forward", "76 kg", "1.79",osasuna));
            players.save(new Player("Memphis", "Depay", "28", 9, "Holandés", 5, "forward", "78 kg", "1.76",osasuna));

            players.save(new Player("Marcus", "Rashford", "24", 10, "Inglés", 2, "forward", "70 kg", "1.80",huesca));
            players.save(new Player("Luis", "Suarez", "35", 9, "Uruguayo", 7, "forward", "86 kg", "1.82",huesca));
            players.save(new Player("Jamie", "Vardy", "35", 9, "Inglés", 3, "forward", "74 kg", "1.79",huesca));
            players.save(new Player("Sadio", "Mane", "30", 10, "Senegalés", 8, "forward", "69 kg", "1.75",huesca));
            players.save(new Player("Romelu", "Lukaku", "29", 9, "Belga", 9, "forward", "94 kg", "1.91",huesca));
            players.save(new Player("Karim", "Adeyemi", "20", 9, "Alemán", 4, "forward", "76 kg", "1.79",huesca));
            players.save(new Player("Ansu", "Fati", "19", 22, "Español", 2, "forward", "66 kg", "1.78",huesca));

            players.save(new Player("Vinicius", "Junior", "22", 20, "Brasileño", 9, "forward", "73 kg", "1.76",celtaVigo));
   players.save(new Player("Zinedine", "Zidane", "49", 10, "Francés", 126, "midfielder", "80 kg", "1.85",celtaVigo));
        players.save(new Player("Ronaldo", "Nazário", "45", 9, "Brasileño", 352, "forward", "87 kg", "1.83", celtaVigo));
        players.save(new Player("Luis", "Figo", "49", 10, "Portugués", 139, "midfielder", "75 kg", "1.80", celtaVigo));
        players.save(new Player("Paolo", "Maldini", "54", 3, "Italiano", 40, "defender", "85 kg", "1.86", celtaVigo));
        players.save(new Player("Alessandro", "Del Piero", "47", 10, "Italiano", 290, "forward", "73 kg", "1.74", celtaVigo));
        players.save(new Player("Didier", "Drogba", "44", 11, "Marfileño", 297, "forward", "84 kg", "1.88", celtaVigo));

        players.save(new Player("David", "Beckham", "47", 7, "Inglés", 129, "midfielder", "75 kg", "1.83", barcelona));
        players.save(new Player("Thierry", "Henry", "44", 14, "Francés", 360, "forward", "83 kg", "1.88", barcelona ));
        players.save(new Player("Roberto", "Carlos", "48", 6, "Brasileño", 113, "defender", "73 kg", "1.68",barcelona));
        players.save(new Player("Xavi", "Hernández", "42", 6, "Español", 85, "midfielder", "68 kg", "1.70",barcelona));
        players.save(new Player("Andriy", "Shevchenko", "45", 7, "Ucraniano", 342, "forward", "78 kg", "1.83",barcelona));
        players.save(new Player("Ryan", "Giggs", "48", 11, "Galés", 169, "midfielder", "74 kg", "1.79",barcelona));
        players.save(new Player("Patrick", "Vieira", "46", 4, "Francés", 107, "midfielder", "84 kg", "1.93",barcelona));

        players.save(new Player("Fabio", "Cannavaro", "48", 5, "Italiano", 20, "defender", "75 kg", "1.75", realBetis));
        players.save(new Player("Michael", "Owen", "42", 10, "Inglés", 222, "forward", "73 kg", "1.73",realBetis));
        players.save(new Player("Rivaldo", "Vitor", "49", 10, "Brasileño", 233, "forward", "78 kg", "1.86",realBetis));
        players.save(new Player("Ronaldinho", "Gaucho", "42", 10, "Brasileño", 247, "forward", "80 kg", "1.81",realBetis));
        players.save(new Player("Frank", "Lampard", "43", 8, "Inglés", 274, "midfielder", "88 kg", "1.84",realBetis));
        players.save(new Player("Steven", "Gerrard", "42", 8, "Inglés", 186, "midfielder", "85 kg", "1.85",realBetis));
        players.save(new Player("Paul", "Scholes", "47", 8, "Inglés", 169, "midfielder", "68 kg", "1.68",realBetis));

players.save(new Player("Alessandro", "Nesta", "46", 3, "Italiano", 14, "defender", "78 kg", "1.87",valencia));
        players.save(new Player("Javier", "Zanetti", "48", 4, "Argentino", 21, "defender", "75 kg", "1.78",valencia));
        players.save(new Player("Cafu", "", "51", 2, "Brasileño", 29, "defender", "78 kg", "1.76",valencia));
        players.save(new Player("Gianluca", "Zambrotta", "45", 2, "Italiano", 10, "defender", "75 kg", "1.81",valencia));
        players.save(new Player("Edgar", "Davids", "49", 4, "Holandés", 40, "midfielder", "68 kg", "1.70",valencia));
        players.save(new Player("Clarence", "Seedorf", "46", 4, "Holandés", 76, "midfielder", "80 kg", "1.76",valencia));
        players.save(new Player("Luís", "Figo", "49", 3, "Portugués", 32, "midfielder", "75 kg", "1.80",valencia));

        players.save(new Player("Patrick", "Vieira", "46", 4, "Francés", 43, "midfielder", "84 kg", "1.93", villarreal));
        players.save(new Player("Dennis", "Bergkamp", "52", 4, "Holandés", 120, "forward", "80 kg", "1.88",villarreal));
        players.save(new Player("Ronaldo", "Nazário", "45", 5, "Brasileño", 352, "forward", "87 kg", "1.83",villarreal));
        players.save(new Player("Andriy", "Shevchenko", "45", 3, "Ucraniano", 342, "forward", "78 kg", "1.83",villarreal));
        players.save(new Player("Didier", "Drogba", "44", 5, "Marfileño", 297, "forward", "84 kg", "1.88",villarreal));
        players.save(new Player("Michael", "Owen", "42", 3, "Inglés", 222, "forward", "73 kg", "1.73",villarreal));
        players.save(new Player("Ruud", "van Nistelrooy", "45", 5, "Holandés", 249, "forward", "78 kg", "1.88",villarreal));

        players.save(new Player("Thierry", "Henry", "44", 6, "Francés", 360, "forward", "83 kg", "1.88", realSociedad));
        players.save(new Player("Roberto", "Baggio", "55", 6, "Italiano", 318, "forward", "72 kg", "1.74",realSociedad));
        players.save(new Player("Paolo", "Maldini", "54", 2, "Italiano", 40, "defender", "85 kg", "1.86",realSociedad));
        players.save(new Player("Franz", "Beckenbauer", "76", 3, "Alemán", 111, "defender", "78 kg", "1.81",realSociedad));
        players.save(new Player("Paul", "Gascoigne", "54", 3, "Inglés", 83, "midfielder", "81 kg", "1.83",realSociedad));
        players.save(new Player("Diego", "Maradona", "61", 10, "Argentino", 259, "midfielder", "75 kg", "1.65",realSociedad));
players.save(new Player("Raul", "Gonzalez", "44", 7, "Español", 323, "forward", "78 kg", "1.81",realSociedad));

        players.save(new Player("Fernando", "Torres", "38", 9, "Español", 260, "forward", "78 kg", "1.86",sevilla));
        players.save(new Player("Alessandro", "Del Piero", "47", 8, "Italiano", 290, "forward", "73 kg", "1.74",sevilla));
        players.save(new Player("Francesco", "Totti", "45", 10, "Italiano", 307, "forward", "82 kg", "1.80",sevilla));
        players.save(new Player("Ryan", "Giggs", "48", 12, "Galés", 169, "midfielder", "74 kg", "1.79",sevilla));
        players.save(new Player("Steven", "Gerrard", "42", 9, "Inglés", 186, "midfielder", "85 kg", "1.85",sevilla));
        players.save(new Player("Gianfranco", "Zola", "56", 7, "Italiano", 229, "forward", "68 kg", "1.68",sevilla));
        players.save(new Player("Michael", "Ballack", "45", 5, "Alemán", 158, "midfielder", "75 kg", "1.89",sevilla));


        players.save(new Player("Cesc", "Fabregas", "35", 8, "Español", 144, "midfielder", "75 kg", "1.78", granada));
        players.save(new Player("David", "Beckham", "47", 5, "Inglés", 129, "midfielder", "75 kg", "1.83",granada));
        players.save(new Player("Ruud", "Gullit", "60", 9, "Holandés", 152, "midfielder", "86 kg", "1.91",granada));
        players.save(new Player("George", "Best", "59", 7, "Irlandés", 181, "forward", "72 kg", "1.75",granada));
        players.save(new Player("Eusebio", "da Silva", "80", 9, "Portugués", 733, "forward", "70 kg", "1.75",granada));
        players.save(new Player("Johan", "Cruyff", "73", 14, "Holandés", 291, "forward", "77 kg", "1.80",granada));
        players.save(new Player("Diego", "Maradona", "61", 15, "Argentino", 259, "forward", "75 kg", "1.65",granada));

        players.save(new Player("Zico", "", "69", 11, "Brasileño", 732, "forward", "70 kg", "1.72", atleticoMadrid));
        players.save(new Player("Garrincha", "", "49", 9, "Brasileño", 232, "forward", "65 kg", "1.69",atleticoMadrid));
        players.save(new Player("Johan", "Cruyff", "73", 13, "Holandés", 291, "forward", "77 kg", "1.80",atleticoMadrid));
        players.save(new Player("Roberto", "Baggio", "55", 8, "Italiano", 318, "forward", "72 kg", "1.74",atleticoMadrid));
        players.save(new Player("Marco", "van Basten", "57", 9, "Holandés", 277, "forward", "78 kg", "1.88",atleticoMadrid));
        players.save(new Player("Karl-Heinz", "Rummenigge", "66", 10, "Alemán", 328, "forward", "81 kg", "1.82",atleticoMadrid));
        players.save(new Player("Gerd", "Müller", "76", 12, "Alemán", 735, "forward", "76 kg", "1.76",atleticoMadrid));

        players.save(new Player("Rivellino", "", "75", 9, "Brasileño", 254, "midfielder", "76 kg", "1.76", athleticBilbao));
        players.save(new Player("Paolo", "Rossi", "65", 7, "Italiano", 202, "forward", "75 kg", "1.77",athleticBilbao));
        players.save(new Player("Jairzinho", "", "77", 10, "Brasileño", 699, "forward", "76 kg", "1.72",athleticBilbao));
        players.save(new Player("Just Fontaine", "", "88", 13, "Francés", 344, "forward", "73 kg", "1.74",athleticBilbao));
        players.save(new Player("Josef", "Bican", "109", 17, "Checoslovaco", 805, "forward", "76 kg", "1.76",athleticBilbao));
        players.save(new Player("Ferenc", "Puskas", "74", 12, "Húngaro", 806, "forward", "79 kg", "1.72",athleticBilbao));
        players.save(new Player("Alfredo", "Di Stefano", "91", 14, "Argentino", 534, "forward", "75 kg", "1.78",athleticBilbao));




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
