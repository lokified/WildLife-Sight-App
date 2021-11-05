CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE animals (id SERIAL PRIMARY KEY, name VARCHAR, sightingId INT, type VARCHAR, health VARCHAR, age VARCHAR);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;