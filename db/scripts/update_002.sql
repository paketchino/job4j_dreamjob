CREATE TABLE IF NOT EXISTS CANDIDATE (
    id serial PRIMARY KEY,
    name TEXT,
    describe TEXT,
    photo bytea,
    visible boolean default false,
    created timestamp default current_timestamp
);