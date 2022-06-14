create table if not exists USERS (
    id serial primary key,
    name_users varchar,
    email varchar,
    password text,
    city_id integer
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);

