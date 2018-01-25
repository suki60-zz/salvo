$.getJSON("/api/players")
  .done(function(data) {

    console.log(data);

    // thead
    $('<thead/>', {
      id: "leader-board-thead",
    }).appendTo("#leader-board-table");

    $('<tr/>', {
      id: "titles-table",
    }).appendTo("#leader-board-thead");

    var titles = ["Username", "Wins", "Ties", "Loses", "Total"]
    for (var i = 0; i < 5; i++) {
      $('<th/>', {
        class: "leader-board-cell",
        text: titles[i],
      }).appendTo("#titles-table");
    }

    //tbody

    $('<tbody/>', {
      id: "leader-board-tbody",
    }).appendTo("#leader-board-table");

    $(data).each(function(i, player) {

      $('<tr/>', {
        id: "leader-board-row-" + (i + 1),
      }).appendTo("#leader-board-tbody");

      // for(var j = 0; j < 5; j++) {
      //
      //   $('<td/>', {
      //     class: "leader-board-cell",
      //     text: player[titles[j]],
      //   }).appendTo("#leader-board-row-" + (i + 1));
      //
      // }

      for (var key in player) {

        $('<td/>', {
          class: "leader-board-cell",
          text: player[key],
        }).appendTo("#leader-board-row-" + (i + 1));
      }

    })
  })
  .fail(function(jqXHR, textStatus, errorThrown) {})
