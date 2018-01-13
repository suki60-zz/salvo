// $.getJSON("/api/game_view", function(data) {})
//   .done(function(data) {
//
//     $(data).each(function(i, game){
//       console.log(game.gamePlayers);
//       $('<li/>', {
//         text: "game: " + game.id + " Date: " + game.create.toString() + " Players: " + showPlayers(game.gamePlayers)
//       }).appendTo("#games-list");
//     });
//
//     console.log(data);
//   })
//   .fail(function(jqXHR, textStatus, errorThrown) {
//     alert('getJSON request failed! ' + textStatus);
//   })
var array = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

for (var i = 0; i < array.length; i++) {

  $('<tr/>', {
    id: array[i],
  }).appendTo("#board");

  for (j = 0; j < 10; j++) {
    $('<td/>', {
      id: array[i] + (j + 1),
      class: "cell",
    }).appendTo("#" + array[i]);
  }
}
