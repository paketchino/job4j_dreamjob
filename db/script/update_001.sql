create table POST (
    id serial PRIMARY KEY,
    name TEXT,
    describe varchar(255),
    visible boolean,
    city_id integer,
    created timestamp
);
