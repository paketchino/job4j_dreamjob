create table POST (
    id serial PRIMARY KEY,
    name TEXT,
    desc varchar (255),
    visible boolean,
    city_id integer,
    created timestamp
);
