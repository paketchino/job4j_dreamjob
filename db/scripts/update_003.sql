CREATE TABLE USERS (
    id SERIAL PRIMARY KEY,
    email TEXT,
    password TEXT,
    city_id integer
);

ALTER TABLE USERS ADD CONSTRAINT email_unique UNIQUE (email);

