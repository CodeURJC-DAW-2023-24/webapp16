# FutPro League
___
## Index
- [**Phase 0**](#phase-0)
  - [**:busts_in_silhouette: Development team member**](#busts_in_silhouette-development-team-members)
  - [**:hammer_and_wrench: Team coordination tool**](#hammer_and_wrench-team-coordination-tool)
  - [**:memo: Entities**](#memo-entities)
  - [**:gear: Funcionalities**](#gear-functionalities)
  - [**:key: User permissions**](#key-user-permissions)
  - [**:framed_picture: Images**](#framed_picture-Images)
  - [**:bar_chart: Graphics**](#bar_chart-Graphics)
  - [**:bulb: Complementary technology**](#bulb-complementary-technology)
  - [**:chart_with_upwards_trend: Algorithm and advanced query**](#chart_with_upwards_trend-algorithm-and-advanced-query)
- [**Phase 1**](#phase-1)
  - [**🖵 Screens**](#🖵-Screens)
    - [**Home Page**](#Home-Page)
    - [**Register/Login**](#Register/Login)
    - [**Tournament Bracket**](#Tournament-Bracket)
    - [**Match Screen**](#Match-Screen)
    - [**Stadistics**](#Stadistics)
    - [**View Player**](#View-Player)
    - [**Create New Player**](#Create-New-Player)
    - [**Profile**](#Profile)
    - [**About Us**](#About-Us)
    - [**Teams**](#Teams)
    - [**Team Info**](#Team-Info)
    - [**Fill Match Report**](#Fill-Match-Report)
    - [**Tournament Create**](#Tournament-Create)
  - [**Flow Diagram**](#Flow-Diagram)

___

___
# Phase 0
___
## :busts_in_silhouette: Development team members

|Name and surname|mail|github|
|----------------|----|------|
| Nicolás Hernández Tejero | n.hernandezt.2021@alumnos.urjc.es | nicohht |
| Manuel López Corchado | m.lopezc.2021@alumnos.urjc.es | MNXLPZ |
| Ahmad Abdel Raziq Al Otaibi | a.abdel.2021@alumnos.urjc.es | Ahmad-SE |
| Adrián Soriano Aragón | a.soriano.2021@alumnos.urjc.es | aadrisoriiano |
| Natalia Hernández Vargas | n.hernandezv.2021@alumnos.urjc.es | nataaah |

___

## :hammer_and_wrench: Team coordination tool
### Trello
This tool offers a Kanban-like organization, allowing us to view at any time and from any location the section of the software that is under development, completed, or pending.
<a href="https://trello.com/b/arU2ZCsQ/daw">Trello link</a>

___
## Main aspects of the web application
___
### :memo: Entities 
The main entities that the application will manage are:
- Users
- Teams
- Tournaments
- Matches
- Stadium

:twisted_rightwards_arrows: <b>Main relations between entities: </b>
Users can be anonymous users, admins, players, coaches, or referees.
Players and coaches belong to teams. Referees do not belong to any team but they participate in matches.
Teams can have multiple players and coaches. They also take part in tournaments.
Tournaments consist of several matches with different teams.
Each team has its own stadium.  

___
### :gear: Functionalities
- Create a new tournament  with all its attributes (name, date, stadium, teams participating).
- Add teams  to a tournament.
- Add players to a team. 
- Generate matches for each round of the tournament based on the number of teams in it.
- Show match results after they have been played.
- Access to information about users, teams, tournaments, matches, and stadiums.
- User registration and login system.
- Write a match report. 
- Show statistics and graphics about the performance of players or teams.
- Follow your favorite teams and players. 
- Use search bar. 
- Download match reports. 
- Consult weather forecast. 
- Send notifications about a match.
- Modify personal profile. 
___

### :key: User permissions
The permissions of each of the user types are:
- <b>Anonymous</b>: View  public content, sign up, sign in.
- <b>Registered</b>:  Allows to do everything an anonymous user can plus mark teams as favourite, view and download match reports, view their profile and modify it and sign out.
- <b>Referee</b>: Allows to do everything a registered user can plus fill out the match report.
- <b>Coach</b>: Allows to do everything a registered user can plus create teams and add players to teams and their teams to tournaments.
- <b>Admin</b>: Allows everything other users can do plus create tournaments,  teams, matches, add players, modify stadiums information, tournaments, matches, teams and players.
___
### :framed_picture: Images
The images mentioned are related to the entities mentioned above.
- Favicon of the web page.
- Team Icons.
- Player Photos.
- Stands Image (Stadium).
- Tournament logo.
___
### :bar_chart: Graphics
The information that will be displayed using graphics is:
- Top Scorers: Table
- Goals per team: Column chart
- Number of goals scored vs goals conceded: Bar graph
- Average age of players: Pie chart

___
### :bulb: Complementary technology
The complementary technology to be used is:
- PDF autogeneration  for match reports.
- Google Maps API integration for stadiums location.
- Weather API for match weather information.
- Email notifications for players, coaches, referee and registered users.

___

### :chart_with_upwards_trend: Algorithm and advanced query
The algorithm or advanced query to be implemented is:
- Automatic clasification when submitting the match report.
- Advanced statistics of  matches by team/player.
- Search bar on teams and players pages.
___

___
# Phase 1
___
#  🖵 Screens
___
## Home Page: 
The main page is dedicated to football, showcasing a vibrant and dynamic design. It features the following elements:

A navigation bar at the top with options for “Home,” “Teams,” “Profile,” and “Tournaments.” There is also a search bar and a sign in/register button on the right side.
A section titled “Available Tournaments” beneath the banner, which displays four different types of trophy icons There is also a button for view the tournaments and a countdown timer for the registration deadline.
A footer menu at the bottom, including options like “Menu,” “About us,” social media profiles, an email contact option, and some information about the website creator and the copyright.

![*HomePage*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/index.png)

___

## Register/Login:
The login/sign up page of the website allows users to access or create their account on your website. It features the following elements:

A logo at the top corner, consisting of multiple colors and shapes, representing the identity of your website.
A login box in the center, with options to enter email and password. The login box has two tabs labeled “Log In” and “Sign Up,” indicating that users can either log into their existing account or create a new one. There is a red “LOGIN” button below the email and password fields for users to proceed after entering their credentials. There is also a link labeled “Forgot your password?” for users who need to recover their password.

![*RegLog*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/login.png)

___

## Tournament Bracket:
The tournament bracket page website displays the matches and results of a football tournament. It features the following elements:

A blank tournament bracket in the middle, labeled ROUND 1 through FINAL; each section has blank spaces for team names and scores. The bracket is designed for a single-elimination tournament with 16 teams.
A stadistics button below the bracket, which allows users see the stadistics off the tournament.

![*Bracket*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/tournamentBracket.png)

___

## Match Screen:
The Match Screen of the web page displays detailed information about a specific football match between two teams. It provides a clear and organized layout that allows users to quickly grasp the key details of the match.
The Match Screen consists of the following elements:

Team logos displayed prominently.
A white box containing lists of players from both teams, divided into two columns labeled with each team’s name. Each column lists players’ names under their respective teams.
A button labeled “Match Report” located below the white box, which leads to a page with more details and statistics about the match.

![*Match*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/matchScreen.png)

___

## Stadistics:
The Statistics Page of the web page shows four bar graphs with different statistics about the football matches. It provides a simple and visual way to compare the performance of the teams and players.

The Statistics Page consists of: 

Four graphs for the following stadistics: goals, shots, passes, and fouls.

![*Stadistics*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/stadistics.png)

___

## View Player:
The Player Info is a section where users can input information about a football player of their choice.

- The club logo is visible on the top right corner.
- A large image of a stadium filled with spectators serves as the background at the upper part of the webpage. The UEFA Champions League trophy is prominently displayed on this background.
- In front of this background, there's an empty form titled “PLAYER INFO” with various fields like “Name,” “Nationality,” “Age,” etc., for inputting information about a player.
![*View Player*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/viewPlayer.png)

___

## Create New Player:

The create new player section is a section where the user can enter information about a soccer player he wants to add to his team.

- The form fields include spaces to enter the player's name, age, position, team, nationality, height, weight and number. Each field is delineated and has placeholder text indicating what information to enter.
- Below the form is a red button labeled "ADD" to submit the completed form.


![*Create Player*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/addPlayer.png)

___

## Create New Team:

The create new team section is a section where the user can enter information about a soccer team he wants to create.

- The section displays an online form titled "Football Referee Report".
- The form has a light gray background and contains several fields to fill in, including:
  - Team Name
  - Players
  - Basic stadistics
- There is an orange "Save Changes" button at the bottom of the form to submit the completed information.


![*Create Team*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/createTeam.png)

___

## Profile:

The profile section is a section where the user can enter and update personal information.

- The section shows a profile page on a website.
- Below the header is the main content area for user profile information with fields for "First Name*", "Last Name*", "Date of Birth*", "Phone Number*", "Email Address*" and "Address".
- Each field has an empty text box next to it for entering information.
- There is a red button labeled "Save changes" below the input fields.

![*Profile*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/profile.png)

___

## About Us:
The About Us is a section where users can learn more about the FUTBOLPRO  League platform and its features.

- In the middle section, there is an article or blog post titled “FutPro League” accompanied by an image. The article explains what FUTBOLPRO LEAGUE is and how it helps users manage their football leagues in minutes.
- Below this section, there’s another part titled “Our Testimonials” featuring a quote from someone praising the website/platform.
  
![*About Us*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/aboutUs.png)

___

## Team:
The Team is a section where users can view information about different football teams.

- All the teams registered in the web.
- The names of the teams and the city that came form are written below their respective logos.

![*Team*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/team.png)

___

## Team Info:
The Team Info is a section where users can view information about a specific football team.

- All the players registered in the team.
- Stadistics off that football team.
![*Team Info*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/teamInfo.png)

___

## Fill Match Report:
The section for filling out the match report is a section in which the soccer referee can enter information about the match he/she has refereed.

- The section displays an online form entitled "Soccer Referee Report".
- The form has a light gray background and contains several fields to fill in, including:
  - Date of the match
  - Time of the match
  - Home team
  - Away team
  - Stadium
  - Weather conditions
  - Temperature (°F)
  - Match Referees
  - Match Summary
- Each field has a blank space next to it to enter the information.
- There is a red "SEND REPORT" button at the bottom of the form to send the completed report.
![*Fill Match Report*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/fillMatchReport.png)

___

## Tournament Create:

The create new tournament section is a section where the user can enter information about a soccer tournament he/she wants to organize.

- The section shows a user interface for creating a new tournament on a website or application.
- Below this header, there is a form with fields to enter:
    - "Tournament name"
    - "Category"
    - "Cup" with an example text "FIFA 20 Tournament cup".
    - Fields to enter different teams labeled "Team 1", "Team 2", "Team 3"...
- At the bottom of the form there are two buttons: a green one labeled "SAVE" and a red one labeled "CANCEL".

![*Tournament Create*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/createTournament.png)

___
# Flow Diagram:

The diagram is a flow chart showing user permissions and navigation steps within a web interface. The diagram uses colored arrows to indicate the progression from one web page or state to another, based on user actions or permissions.

- The diagram is a complex schematic with multiple screenshots of web pages connected by colored arrows indicating different user actions or states.
- Each screenshot represents different sections or pages of a sports or tournament related website.
- There are three initial states at the top: "Registered User", "Guest", "Referee" and "Admin", leading to different paths depending on the user's status.
- Arrows connect these initial states to various web pages, including "Tournament Create", "Events" and "Player/Team Login" among others.
- Each colored arrow represents a user, and therefore the path through which he/she can navigate the site.
![*Flow Diagram*](https://github.com/CodeURJC-DAW-2023-24/webapp16/blob/main/readmeImg/usersDiagram.jpg)
