create table IF NOT EXISTS candidate {
    id serial PRIMARY KEY,
    name text,
    describe text,
    photo text[],
    visible boolean,
    created timestamp
};