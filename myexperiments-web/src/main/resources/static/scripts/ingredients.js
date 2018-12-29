$(document).ready(function() {
  $.ajax({
    url : "/ingredients?byType"
  }).then(function(data) {
    $.get('mustache/ingredients.mst', function(template) {
      $('#doughs').html(Mustache.render(template, data.dough));
      $('#toppings').html(Mustache.render(template, data.topping));
      $('#cheeses').html(Mustache.render(template, data.cheese));
      $('#sauces').html(Mustache.render(template, data.sauce));
    });
  });
});