CREATE TABLE IF NOT EXISTS POST (
    id serial PRIMARY KEY,
    name TEXT,
    describe text,
    visible boolean default false,
    city_id integer,
    created timestamp default current_timestamp
);