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

// create cells for table
var letter = ["Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

for (var i = 0; i < letter.length; i++) {

  $('<tr/>', {
    id: letter[i],
  }).appendTo("#board-table");

  for (var j = 0; j < letter.length; j++) {

    $('<td/>', {
      id: letter[i] + j,
      class: "cell",
    }).appendTo("#" + letter[i]);

    if ((j == 0) && (i != 0)) {
      $("#" + letter[i] + j).text(i);
    }
    if((i == 0) && (j != 0)) {
      $("#" + letter[i] + j).text(letter[j]);
    }
  }
}
