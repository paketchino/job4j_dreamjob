create table IF NOT EXISTS candidate {
    id serial PRIMARY KEY,
    name text,
    desc text,
    photo text[],
    visible boolean,
    created timestamp
};