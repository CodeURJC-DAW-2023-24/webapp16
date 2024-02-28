$(document).ready(function() {
    var page = 1;  // Número de página inicial
    var pageSize = 4;  // Número de equipos por página

    function loadMoreTeams() {
        $.ajax({
            url: '/api/teams?page=' + page + '&pageSize=' + pageSize,
            method: 'GET',
            dataType: 'json',
                success: function(data) {
                if (data.length > 0) {

                    var newTeamsHtml = '';
                    data.forEach(function(team) {
                        newTeamsHtml += `
                    <div class="col-md-3 column">
                        <div class="card">
                            <div class="content">
                                <img class="img-responsive" src="data:image/png;base64,${team.imagePath}" alt="imagenEquipo" style="width: 250px; height:350px" />
                                <h4 style="margin-bottom:20px; margin-top:20px">${team.name}</h4>
                                <p class="title" style="margin-bottom:10px">${team.stadium}</p>
                                <div class="center"><a href="/teams/${team.name}"><button class="button">Info</button></a></div>
                            </div>
                        </div>
                    </div>`;
                    });
                    $('#teamContainer').append(newTeamsHtml);

                    page++;
                } else {
                    // No hay más equipos para cargar
                    $('#loadMoreTeams').prop('disabled', true).text('No hay más equipos');
                }
            },
            error: function(error) {
                console.error('Error al cargar equipos:', error);
            }
        });
    }

    // Manejador de clic para el botón "Cargar más equipos"
    $('#loadMoreTeams').on('click', function() {
        loadMoreTeams();
    });
});