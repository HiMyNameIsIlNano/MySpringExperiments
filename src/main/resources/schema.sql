create table if not exists INGREDIENT (
    id varchar(5) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists PIZZA (
    id identity,
    name varchar(25) not null,
    createdAt timestamp not null
);

create table if not exists PIZZA_INGREDIENTS (
    pizza_id bigint not null,
    ingredient_id varchar(5) not null
);

alter table PIZZA_INGREDIENTS add foreign key (pizzaId) references PIZZA(id);
alter table PIZZA_INGREDIENTS add foreign key (ingredientId) references INGREDIENT(id);

create table if not exists PIZZA_ORDER (
    id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(2) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists PIZZA_ORDER_PIZZAS (
    orderId bigint not null,
    pizzaId bigint not null
);

alter table PIZZA_ORDER_PIZZAS add foreign key (orderId) references PIZZA_ORDER(id);
alter table PIZZA_ORDER_PIZZAS add foreign key (pizzaId) references PIZZA(id);