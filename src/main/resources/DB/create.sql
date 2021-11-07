CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE animals (id SERIAL PRIMARY KEY, name VARCHAR, type VARCHAR, health VARCHAR, age VARCHAR);
CREATE TABLE sightings (id SERIAL PRIMARY KEY, animal_Id VARCHAR, location VARCHAR, rangerName VARCHAR, timeSeen TIMESTAMP);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;