create table role(
id serial primary key,
name text
);

create table users(
id serial primary key,
name text,
role_id int references role(id)
);

create table rules(
id serial primary key,
description text
);

create table rules_role(
id serial primary key,
rule_id int references rules(id),
role_id int references role(id)
);

create table item(
id serial primary key,
	status text,
	user_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments(
id serial prumary key,
content text,
item_id int references item(id)
);
 create table attachs(
 id serial primary key,
 file text,
 item_id int references item(id)
 );
 
 create table category(
 id serial primary key,
 type text,
);

create table state(
id serial prumary key,
states text
);