var query_obj = getQueryObj();

$.getJSON("/api/game_view/" + query_obj.gp)
  .done(function(game) {

    console.log(game);

    $("#player-info").text(createPlayerInfo(game));

    $("#rival-info").text(createRivalInfo(game));

    createBoard();

    createRivalBoard();

    createShips(game);

    createPlayerSalvos(game);
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

  var info = "";
  var viewer_id = getQueryObj().gp;

  $(game.gamePlayers).each(function(i, gamePlayer) {

    if (gamePlayer.id == viewer_id) {

      info = gamePlayer.players.email + "(you)";
    }
  });

  return info;
}

function createRivalInfo(game) {

  var info = "";
  var viewer_id = getQueryObj().gp;

  $(game.gamePlayers).each(function(i, gamePlayer) {

    if (gamePlayer.id != viewer_id) {

      info = gamePlayer.players.email;
    }
  });

  return info;
}

function createBoard() {

  var letter = ["Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

  for (var i = 0; i < 11; i++) {

    $('<tr/>', {
      id: letter[i],
    }).appendTo("#player-board");

    for (var j = 0; j < 11; j++) {

      $('<td/>', {
        id: letter[j] + i,
        class: "cell",
      }).appendTo("#" + letter[i]);

      if ((j == 0) && (i != 0)) {
        $("#" + letter[j] + i).text(i);
      }
      if ((i == 0) && (j != 0)) {
        $("#" + letter[j] + i).text(letter[j]);
      }
      if (!((i == 0) || (j == 0))) {
        $("#" + letter[j] + i).attr("bgcolor", "#3498DB");
      }
    }
  }
}

function createRivalBoard() {

  var letter = ["Z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

  for (var i = 0; i < 11; i++) {

    $('<tr/>', {
      id: "r-" + letter[i],
    }).appendTo("#rival-board");

    for (var j = 0; j < 11; j++) {

      $('<td/>', {
        id: "r-" + letter[j] + i,
        class: "cell",
      }).appendTo("#r-" + letter[i]);

      if ((j == 0) && (i != 0)) {
        $("#r-" + letter[j] + i).text(i);
      }
      if ((i == 0) && (j != 0)) {
        $("#r-" + letter[j] + i).text(letter[j]);
      }
      if (!((i == 0) || (j == 0))) {
        $("#r-" + letter[j] + i).attr("bgcolor", "#3498DB");
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

function createPlayerSalvos(game) {

  $(game.salvos).each(function(i, salvo) {

    $(salvo.location).each(function(i, cell) {
      if (salvo.gamePlayer == getQueryObj().gp) {

        $("#r-" + cell).attr("bgcolor", "#AED6F1");
        $("#r-" + cell).text(salvo.turn);

      } else {

        if ($("#" + cell).attr("bgcolor") == "#707B7C") {
          $("#" + cell).attr("bgcolor", "#E74C3C");
        } else {
          $("#" + cell).attr("bgcolor", "#AED6F1");
        }
        $("#" + cell).text(salvo.turn);
      }
    })
  })
}
