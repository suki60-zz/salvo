var game_player = paramObj();

function paramObj() {
  var search = location.search;
  var obj = {};
  var reg = /(?:[?&]([^?&#=]+)(?:=([^&#]*))?)(?:#.*)?/g;

  search.replace(reg, function(match, param, val) {
    obj[decodeURIComponent(param)] = val === undefined ? "" : decodeURIComponent(val);
  });

  return obj;
}
console.log(game_player.gp);

$.getJSON("/api/game_view/" + game_player.gp, function(data) {})
  .done(function(data) {

    console.log("Ok");
    console.log(data);
  })
  .fail(function(jqXHR, textStatus, errorThrown) {
    console.log("fail");
  })

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
