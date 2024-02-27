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
                    // Renderiza los nuevos equipos y agrega al contenedor
                    var newTeamsHtml = '';
                    data.forEach(function(team) {
                        newTeamsHtml += `
                     <div class="col-md-3 column">
                        <div class="card">
                           <div class="content">
                              <img class="img-responsive" src="/images/realMadrid.png" alt="${team.name}" style="width:100%">
                              <h4>${team.name}</h4>
                              <p class="title">${team.stadium}</p>
                              <div class="center"><a href="/teams/${team.name}"><button class="button">Info</button></a></div>
                           </div>
                        </div>
                     </div>`;
                    });
                    $('#teamContainer').append(newTeamsHtml);

                    // Incrementa el número de página
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