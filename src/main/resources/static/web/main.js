// $.getJSON("http://localhost:8080/api/games", function(json) {
//   console.log(json);
// });
$.getJSON("http://localhost:8080/api/games", function(data) {})
  .done(function(data) {

    $(data).each(function(i, game){
      console.log(game.gamePlayers);
      $('<li/>', {
        text: "game: " + game.id + " Date: " + game.create.toString() + " Players: " + showPlayers(game.gamePlayers)
      }).appendTo("#games-list");
    });

    console.log(data);
  })
  .fail(function(jqXHR, textStatus, errorThrown) {
    alert('getJSON request failed! ' + textStatus);
  })

function showPlayers(gamePlayers) {
  var allPlayers = "";

  $(gamePlayers).each(function(i, gamePlayer){

    allPlayers += gamePlayer.players.email.toString() + " ";
  });

  return allPlayers;
}
