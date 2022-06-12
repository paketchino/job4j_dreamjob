CREATE TABLE IF NOT EXISTS POST (
    id serial PRIMARY KEY,
    name TEXT,
    describe text,
    city_id integer,
    created timestamp default current_timestamp,
    visible boolean default false
);