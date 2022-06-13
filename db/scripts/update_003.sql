CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name_users varchar,
    email varchar,
    password TEXT,
    city_id integer
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);

