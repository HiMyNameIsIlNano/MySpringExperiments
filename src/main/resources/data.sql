delete from PIZZA_INGREDIENTS;
delete from PIZZA_ORDER_PIZZAS;
delete from PIZZA;
delete from PIZZA_ORDER;

delete from INGREDIENT;
insert into INGREDIENT (id, name, type) values ('SOURD', 'Sourdough', 'DOUGH');
insert into INGREDIENT (id, name, type) values ('NORMD', 'Normal Dough', 'DOUGH');
insert into INGREDIENT (id, name, type) values ('MOZZ', 'Mozzarella Cheese', 'CHEESE');
insert into INGREDIENT (id, name, type) values ('PRC', 'Parmesan Cheese', 'CHEESE');
insert into INGREDIENT (id, name, type) values ('SAL', 'Spicy Salami', 'TOPPING');
insert into INGREDIENT (id, name, type) values ('EGGP', 'Eggplant', 'TOPPING');
insert into INGREDIENT (id, name, type) values ('CHTO', 'Cherry Tomato', 'TOPPING');
insert into INGREDIENT (id, name, type) values ('HAM', 'Ham', 'TOPPING');