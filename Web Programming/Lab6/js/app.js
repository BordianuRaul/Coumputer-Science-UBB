$(document).ready(function(){

  $("*").css({
    "padding": "0",
    "margin" : "0",
    "font-family": "Roboto"
  })

  $("ul").css({
    "list-style": "none",
    "background": "#22438C",
    "font-size": "0"
  })

  $("ul.menu").find("li").css({
    "display": "inline-block",
    "position": "relative"

  });

  $("ul.menu").find("li a").css({
    "display": "block",
    "padding": "20px 50px",
    "color": "#FFF",
    "text-decoration": "none",
    "text-align": "center",
    "font-size": "30px"
  });

  $("ul.menu").find("ul.dropdown").css({
    "width": "100%",
    "background": "#22438C",
    "position": "absolute",
    "z-index": "999",
    "display": "none"
  });

  $("a.players").click(function (){
      $("ul.dropdownPlayers").slideToggle('fast')
  })

  $("a.teams").click(function (){
    $("ul.dropdownTeams").slideToggle('fast')
  })

  $("a.leagues").click(function (){
    $("ul.dropdownLeagues").slideToggle('fast')
  })
});
