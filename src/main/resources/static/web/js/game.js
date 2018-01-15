var query_obj = getQueryObj();

console.log(query_obj.gp);

$.getJSON("/api/game_view/" + query_obj.gp)
  .done(function(game) {

    console.log(game);

    $("#player-info-title").text(createPlayerInfo(game));

    createBoard();

    createShips(game);
  })
  .fail(function(jqXHR, textStatus, errorThrown) {
    console.log("fail");
  })

// functions

function getQueryObj() {
  var search = location.search;
  var obj = {};
  var reg = /(?:[?&]([^?&#=]+)(?:=([^&#]*))?)(?:#.*)?/g;

  search.replace(reg, function(match, param, val) {
    obj[decodeURIComponent(param)] = val === undefined ? "" : decodeURIComponent(val);
  });

  return obj;
}

function createPlayerInfo(game) {

  var viewer = "";
  var opponent = "";
  var viewer_id = getQueryObj().gp;

  $(game.gamePlayers).each(function(i, gamePlayer) {

    if(gamePlayer.id == viewer_id) {

      viewer = gamePlayer.players.email + "(you)";
    } else {

      opponent = gamePlayer.players.email;
    }
  })

  return viewer + " vs " + opponent;
}

function createBoard() {

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
      if ((i == 0) && (j != 0)) {
        $("#" + letter[i] + j).text(letter[j]);
      }
      if (!((i == 0) || (j == 0))) {
        $("#" + letter[i] + j).attr("bgcolor", "#3498DB");
      }
    }
  }
}

function createShips(game) {

  $(game.ships).each(function(i, ship) {

    $(ship.location).each(function(i, cell) {

      $("#" + cell).attr("bgcolor", "#707B7C");
    })
  })
}
