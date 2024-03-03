function saveTournamentFormData(teamNumber,tournamentNumber){ //Crea torneo nuevo o actualiza buscando por nombre
    let elements = document.querySelectorAll('input.texted')
    console.log(elements)
    elements.forEach(function(element) { //guardamos en memoria los datos primeros del formulario
        localStorage.setItem(element.id, element.value);
        console.log(element.value)
    });
    var queryParams = new URLSearchParams(window.location.search);
    queryParams.set("field_1", document.getElementById("field_1").value);
    queryParams.set("field_2", document.getElementById("field_2").value);
    queryParams.set("field_3", document.getElementById("field_3").value);
    window.location.href = "https://localhost:8443/tournamentCreation/"+tournamentNumber+"/"+teamNumber+"?"+queryParams.toString(); //No reconoce los request params
}
function saveTeamDataFormData(tourNumber){ //Crea jugadores y equipo y asigna equipo al torneo
    let elements = document.querySelectorAll('input')
    console.log(elements)
    var dataArray = Array.from(elements).map(function(element) { //convertimos elements a un array
        return { name: element.name, value: element.value };
    });
    var xhr = new XMLHttpRequest(); //Hacemos post de toda la info para controlarla desde Java
    xhr.open('POST', '/addTeam/'+tourNumber, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(dataArray));
}