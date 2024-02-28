$(document).ready(function() {
    var page = 1;  // Número de página inicial
    var pageSize = 4;  // Número de jugadores por página

    function loadMorePlayers() {
        $.ajax({
            url: '/api/players?page=' + page + '&pageSize=' + pageSize,
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                if (data.length > 0) {
                    var newPlayersHtml = '';
                    data.forEach(function(player) {
                        newPlayersHtml += `
                            <div class="col-md-3 column">
                                <div class="card">
                                    <div class="content">
                                        <img class="img-responsive" src="/images/default_avatar_player.png" alt="${player.name}" style="width:100%">
                                        <h4 style="margin-bottom:20px ;margin-top:20px">${player.name} ${player.lastName}</h4>
                                        <p class="title" style="margin-bottom:10px">${player.position}</p>
                                        <div class="center"><a href="/teams/${player.team.name}/${player.name}"><button class="button">Info</button></a></div>
                                    </div>
                                </div>
                            </div>`;
                    });
                    $('#playerContainer').append(newPlayersHtml);

                    page++;
                } else {
                    $('#loadMorePlayers').prop('disabled', true).text('No hay mas jugadores');
                }
            },
            error: function(error) {
                console.error('Error al cargar jugadores:', error);
            }
        });
    }

    $('#loadMorePlayers').on('click', function() {
        loadMorePlayers();
    });
});
