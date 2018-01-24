$.getJSON("/api/players")
  .done(function(data) {

    console.log(data);

    $('<tr/>', {
      id: "titles-table",
    }).appendTo("#leader-board");

    for (var i = 0; i < 4; i++) {
      $('<td/>', {
        class: "cell",
      }).appendTo("#titles-table");
    }



    // $(data).each(function(i, player) {
    //
    //   $('<tr/>', {
    //     id: "row" + i,
    //   }).appendTo("#leader-board");







  })
  .fail(function(jqXHR, textStatus, errorThrown) {
    console.log(textStatus);
    console.log(jqXHR);
    console.log(errorThrown);
  })
