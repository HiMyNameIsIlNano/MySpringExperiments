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

insert into USER_ACCOUNT (username, password, fullname, street, city, state, zip, phone_number, enabled) values (
  'user'
  , '$2a$10$WoorisJD1V//O5qNn..Wx.fj5zHD12kRNAjPIUXSUjmIX7EsdiInC'
  , 'User Dummy'
  , 'A Street 1'
  , 'City'
  , 'IT'
  , '999'
  , '123456789'
  , 1
);


insert into USER_ACCOUNT (username, password, fullname, street, city, state, zip, phone_number, enabled) values (
  'admin'
  , '$2a$10$ZAQw2RXjLY229RozSXbKkemy3TmHANCvpKFwKLi9iXiU1Sf5RFlLS'
  , 'Admin Dummy'
  , 'A Street 2'
  , 'City'
  , 'IT'
  , '999'
  , '987654321'
  , 1
);

insert into USER_AUTHORITIES (username, authority) values ('user', 'ROLE_USER');
insert into USER_AUTHORITIES (username, authority) values ('admin', 'ROLE_ADMIN');