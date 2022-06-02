CREATE TABLE IF NOT EXISTS POST (
    id serial PRIMARY KEY,
    name TEXT,
    describe text,
    visible boolean,
    city_id integer,
    created timestamp
);