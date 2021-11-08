# WildLife-Sight-App
An application to track wild animals.

## Description
This is application that tracks animal in two categories, animal and endagered animals and record its sighting according to the region seen and the ranger's name.

## Behavior Driven Development.
1. Display Animal's property.
  * INPUT : Enter name of animal.
  * OUTPUT : Displays the animal.
2. Display Endagered Animal property.
  * INPUT : Enter name of animal.
  * OUTPUT : Displays Endangered animal.
3. Display Sightings record.
  * INPUT : Enter ranger's name.
  * OUTPUT : Displays Sighting records.

## Installation Requirements
* Install Java Software Development (IDE) also known as IntelliJ.
* Install Gradle to run the dependencies.
* Clone the repository in your local machine.
* Open with the IDE and run it using Gradle.

## Database setup
1. Type in psql.
Run the commands.
CREATE DATABASE wildlife_tracker;
CREATE TABLE animals (id SERIAL PRIMARY KEY, name VARCHAR, type VARCHAR, health VARCHAR, age VARCHAR);
CREATE TABLE sightings (id SERIAL PRIMARY KEY, animal_Id VARCHAR, location VARCHAR, rangerName VARCHAR, timeSeen TIMESTAMP);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

## Known Bugs
* None.

## Technologies Used
* JAVA.
* CSS.
* Handlebars

## Support and contact details
Email: lsheldon645@gmail.com


### License
* MIT
Copyright (c) 2021 Sheldon OKware
