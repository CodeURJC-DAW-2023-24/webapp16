import { Component } from '@angular/core';
import {TournamentService} from "../../../services/tournament.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-tournament',
  templateUrl: './newTournament.component.html',
  styleUrls: ['./newTournament.component.css']
})

export class NewTournamentComponent{


  teams: any[] =  Array(8).fill(0)
  tournament: any;
  tourName= '';
  tourCategory= '';
  tourCup= '';
  tourImage = '';
  team1 = {
    name: '',
    coach: '',
    stadium: '',
    gamesPlayed: 0,
    wins: 0,
    loses: 0,
    imagePath: '',
    tournament: {
      name: '',
      category: '',
      cup: '',
      tournamentImagePath: '',
      tournamentImageAsString: '',
    },
    players: [
      // ...
    ],
    imageAsString: ''
  };
  team2 = {
    name: '',
    coach: '',
    stadium: '',
    gamesPlayed: 0,
    wins: 0,
    loses: 0,
    imagePath: '',
    tournament: {
      name: '',
      category: '',
      cup: '',
      tournamentImagePath: '',
      tournamentImageAsString: '',
    },
    players: [
      // ...
    ],
    imageAsString: ''
  };
  team3 = {
    name: '',
    coach: '',
    stadium: '',
    gamesPlayed: 0,
    wins: 0,
    loses: 0,
    imagePath: '',
    tournament: {
      name: '',
      category: '',
      cup: '',
      tournamentImagePath: '',
      tournamentImageAsString: '',
    },
    players: [
      // ...
    ],
    imageAsString: ''
  };
  team4 = {
    name: '',
    coach: '',
    stadium: '',
    gamesPlayed: 0,
    wins: 0,
    loses: 0,
    imagePath: '',
    tournament: {
      name: '',
      category: '',
      cup: '',
      tournamentImagePath: '',
      tournamentImageAsString: '',
    },
    players: [
      // ...
    ],
    imageAsString: ''
  };
  team5 = {
    name: '',
    coach: '',
    stadium: '',
    gamesPlayed: 0,
    wins: 0,
    loses: 0,
    imagePath: '',
    tournament: {
      name: '',
      category: '',
      cup: '',
      tournamentImagePath: '',
      tournamentImageAsString: '',
    },
    players: [
      // ...
    ],
    imageAsString: ''
  };
  team6 = {
    name: '',
    coach: '',
    stadium: '',
    gamesPlayed: 0,
    wins: 0,
    loses: 0,
    imagePath: '',
    tournament: {
      name: '',
      category: '',
      cup: '',
      tournamentImagePath: '',
      tournamentImageAsString: '',
    },
    players: [
      // ...
    ],
    imageAsString: ''
  };
  team7 = {
    name: '',
    coach: '',
    stadium: '',
    gamesPlayed: 0,
    wins: 0,
    loses: 0,
    imagePath: '',
    tournament: {
      name: '',
      category: '',
      cup: '',
      tournamentImagePath: '',
      tournamentImageAsString: '',
    },
    players: [
      // ...
    ],
    imageAsString: ''
  };
  team8 = {
    name: '',
    coach: '',
    stadium: '',
    gamesPlayed: 0,
    wins: 0,
    loses: 0,
    imagePath: '',
    tournament: {
      name: '',
      category: '',
      cup: '',
      tournamentImagePath: '',
      tournamentImageAsString: '',
    },
    players: [
      // ...
    ],
    imageAsString: ''
  };

  constructor(private tournamentService: TournamentService, private router: Router) {
    this.getTeamsFromLocalStorage();
  }

  getTeamsFromLocalStorage() {
    let team1 = localStorage.getItem('team1');
    if (team1 !== null) {
      this.teams[0]=JSON.parse(team1);
    }
    let team2 = localStorage.getItem('team2');
    if (team2 !== null) {
      this.teams[1]=JSON.parse(team2);
    }
    let team3 = localStorage.getItem('team3');
    if (team3 !== null) {
      this.teams[2]=JSON.parse(team3);
    }
    let team4 = localStorage.getItem('team4');
    if (team4 !== null) {
      this.teams[3]=JSON.parse(team4);
    }
    let team5 = localStorage.getItem('team5');
    if (team5 !== null) {
      this.teams[4]=JSON.parse(team5);
    }
    let team6 = localStorage.getItem('team6');
    if (team6 !== null) {
      this.teams[5]=JSON.parse(team6);
    }
    let team7 = localStorage.getItem('team7');
    if (team7 !== null) {
      this.teams[6]=JSON.parse(team7);
    }
    let team8 = localStorage.getItem('team8');
    if (team8 !== null) {
      this.teams[7]=JSON.parse(team8);
    }
  }
  onSubmit() {
    this.team1.coach =this.teams[0].coach;
    this.team1.name = this.teams[0].name;
    this.team1.stadium = this.teams[0].stadium;
    this.team1.imagePath = this.teams[0].imagePath;
    this.team1.players = this.teams[0].players;
    this.team1.tournament.name = this.tourName;
    this.team1.tournament.category = this.tourCategory;
    this.team1.tournament.cup = this.tourCup;

    this.team2.coach =this.teams[1].coach;
    this.team2.name = this.teams[1].name;
    this.team2.stadium = this.teams[1].stadium;
    this.team2.imagePath = this.teams[1].imagePath;
    this.team2.players = this.teams[1].players;
    this.team2.tournament.name = this.tourName;
    this.team2.tournament.category = this.tourCategory;
    this.team2.tournament.cup = this.tourCup;

    this.team3.coach =this.teams[2].coach;
    this.team3.name = this.teams[2].name;
    this.team3.stadium = this.teams[2].stadium;
    this.team3.imagePath = this.teams[2].imagePath;
    this.team3.players = this.teams[2].players;
    this.team3.tournament.name = this.tourName;
    this.team3.tournament.category = this.tourCategory;
    this.team3.tournament.cup = this.tourCup;

    this.team4.coach =this.teams[3].coach;
    this.team4.name = this.teams[3].name;
    this.team4.stadium = this.teams[3].stadium;
    this.team4.imagePath = this.teams[3].imagePath;
    this.team4.players = this.teams[3].players;
    this.team4.tournament.name = this.tourName;
    this.team4.tournament.category = this.tourCategory;
    this.team4.tournament.cup = this.tourCup;

    this.team5.coach =this.teams[4].coach;
    this.team5.name = this.teams[4].name;
    this.team5.stadium = this.teams[4].stadium;
    this.team5.imagePath = this.teams[4].imagePath;
    this.team5.players = this.teams[4].players;
    this.team5.tournament.name = this.tourName;
    this.team5.tournament.category = this.tourCategory;
    this.team5.tournament.cup = this.tourCup;

    this.team6.coach =this.teams[5].coach;
    this.team6.name = this.teams[5].name;
    this.team6.stadium = this.teams[5].stadium;
    this.team6.imagePath = this.teams[5].imagePath;
    this.team6.players = this.teams[5].players;
    this.team6.tournament.name = this.tourName;
    this.team6.tournament.category = this.tourCategory;
    this.team6.tournament.cup = this.tourCup;

    this.team7.coach =this.teams[6].coach;
    this.team7.name = this.teams[6].name;
    this.team7.stadium = this.teams[6].stadium;
    this.team7.imagePath = this.teams[6].imagePath;
    this.team7.players = this.teams[6].players;
    this.team7.tournament.name = this.tourName;
    this.team7.tournament.category = this.tourCategory;
    this.team7.tournament.cup = this.tourCup;

    this.team8.coach =this.teams[7].coach;
    this.team8.name = this.teams[7].name;
    this.team8.stadium = this.teams[7].stadium;
    this.team8.imagePath = this.teams[7].imagePath;
    this.team8.players = this.teams[7].players;
    this.team8.tournament.name = this.tourName;
    this.team8.tournament.category = this.tourCategory;
    this.team8.tournament.cup = this.tourCup;

    console.log(this.team1)
    console.log(this.team2)
    console.log(this.team3)
    console.log(this.team4)
    console.log(this.team5)
    console.log(this.team6)
    console.log(this.team7)
    console.log(this.team8)

  let tournamentWithTeams = {
      tournament: {
        name: this.tourName,
        category: this.tourCategory,
        cup: this.tourCup,
        tournamentImagePath: this.tourImage,
      },
    teams: [this.team1, this.team2, this.team3, this.team4, this.team5, this.team6, this.team7, this.team8]

  }
    for (let team of tournamentWithTeams.teams) {
      team.imagePath = team.imageAsString;
    }
  console.log(tournamentWithTeams)
    this.tournament = tournamentWithTeams.tournament
    this.tournamentService.createTournament(tournamentWithTeams).subscribe(
      data => {
        console.log(data);

        this.router.navigate(['/']);
      },
      error => {
        console.error('Error:', error);
      }
    );



  }




  onFileChange(event: Event, teamIndex: number) {
    let reader = new FileReader();
    let target = event.target as HTMLInputElement;
    if(target && target.files && target.files.length > 0) {
      let file = target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        if (teamIndex=== -1) {
          this.tourImage = reader.result as string;
        this.team1.tournament.tournamentImageAsString = reader.result as string;
        this.team2.tournament.tournamentImageAsString = reader.result as string;
        this.team3.tournament.tournamentImageAsString = reader.result as string;
        this.team4.tournament.tournamentImageAsString = reader.result as string;
        this.team5.tournament.tournamentImageAsString = reader.result as string;
        this.team6.tournament.tournamentImageAsString = reader.result as string;
        this.team7.tournament.tournamentImageAsString = reader.result as string;
        } else if (teamIndex=== 0) {
          this.team1.imageAsString = reader.result as string;
        }else if (teamIndex=== 1) {
          this.team2.imageAsString = reader.result as string;
        }else if (teamIndex=== 2) {
          this.team3.imageAsString = reader.result as string;
        }else if (teamIndex=== 3) {
          this.team4.imageAsString = reader.result as string;
        }else if (teamIndex=== 4) {
          this.team5.imageAsString = reader.result as string;
        }else if (teamIndex=== 5) {
          this.team6.imageAsString = reader.result as string;
        }else if (teamIndex=== 6) {
          this.team7.imageAsString = reader.result as string;
        }
      };
    }
  }
}
