create table if not exists INGREDIENT (
    id varchar(5) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists PIZZA (
    id identity,
    name varchar(25) not null,
    created_at timestamp not null
);

create table if not exists PIZZA_INGREDIENTS (
    pizza_id bigint not null,
    ingredient_id varchar(5) not null
);

alter table PIZZA_INGREDIENTS add foreign key (pizza_id) references PIZZA(id);
alter table PIZZA_INGREDIENTS add foreign key (ingredient_id) references INGREDIENT(id);

create table if not exists PIZZA_ORDER (
    id identity,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists PIZZA_ORDER_PIZZAS (
    order_id bigint not null,
    pizza_id bigint not null
);

alter table PIZZA_ORDER_PIZZAS add foreign key (order_id) references PIZZA_ORDER(id);
alter table PIZZA_ORDER_PIZZAS add foreign key (pizza_id) references PIZZA(id);