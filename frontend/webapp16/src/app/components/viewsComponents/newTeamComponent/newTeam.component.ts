import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'new-team',
  templateUrl: './newTeam.component.html',
  styleUrls: ['./newTeam.component.css']
})
export class NewTeamComponent implements OnInit{
  team = {
    name: '',
    coach: '',
    stadium: '',
    colors: '',
    players: [
      { name: '', age: '', shirtNumber: '', nationality: '', weight: '', height: '', position: '' },
      { name: '', age: '', shirtNumber: '', nationality: '', weight: '', height: '', position: '' },
      { name: '', age: '', shirtNumber: '', nationality: '', weight: '', height: '', position: '' },
      { name: '', age: '', shirtNumber: '', nationality: '', weight: '', height: '', position: '' },
      { name: '', age: '', shirtNumber: '', nationality: '', weight: '', height: '', position: '' },
      { name: '', age: '', shirtNumber: '', nationality: '', weight: '', height: '', position: '' },
      { name: '', age: '', shirtNumber: '', nationality: '', weight: '', height: '', position: '' }
    ]
  };
  constructor(private router: Router) { }

  onSubmit() {
    if (localStorage.getItem('team1') === null) {
      localStorage.setItem('team1', JSON.stringify(this.team));
    } else if (localStorage.getItem('team2') === null) {
      localStorage.setItem('team2', JSON.stringify(this.team));
    } else if (localStorage.getItem('team3') === null) {
      localStorage.setItem('team3', JSON.stringify(this.team));
    } else if (localStorage.getItem('team4') === null) {
      localStorage.setItem('team4', JSON.stringify(this.team));
    } else if (localStorage.getItem('team5') === null) {
      localStorage.setItem('team5', JSON.stringify(this.team));
    } else if (localStorage.getItem('team6') === null) {
      localStorage.setItem('team6', JSON.stringify(this.team));
    } else if (localStorage.getItem('team7') === null) {
      localStorage.setItem('team7', JSON.stringify(this.team));
    }else if (localStorage.getItem('team8') === null) {
      localStorage.setItem('team8', JSON.stringify(this.team));
    }
    this.router.navigate(['/addNewTournament']);
  }
  ngOnInit(): void {
  }
}
