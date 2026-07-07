-- Transitly Database Schema
-- IS230: Introduction to Database Systems, King Saud University

CREATE SCHEMA Project;
USE project;

CREATE TABLE line(
 line_num INT PRIMARY KEY,
 start_station VARCHAR(30),
 last_station VARCHAR(30),
 type VARCHAR(5) CHECK (type IN('metro', 'bus'))
 );

CREATE TABLE Metro_Bus(
 number INT PRIMARY KEY,
 type VARCHAR(5) CHECK (type IN('metro', 'bus')),
 line_num INT,
 FOREIGN KEY(line_num) REFERENCES line(line_num)
 );

CREATE TABLE Seat(
 metro_number INT,
 seat_number VARCHAR(3),
 class VARCHAR(6) CHECK (class IN('first', 'normal')),
 status VARCHAR(10) CHECK (status IN('available', 'booked')),
 PRIMARY KEY(metro_number, seat_number),
 FOREIGN KEY(metro_number) REFERENCES Metro_Bus(number) ON DELETE CASCADE
 );

CREATE TABLE Station(
 name VARCHAR(30) PRIMARY KEY,
 street_name VARCHAR(30),
 postal_code CHAR(5),
 type VARCHAR(5) CHECK (type IN('metro', 'bus')),
 working_hours VARCHAR(15)
 );

CREATE TABLE Contains(
 line_num INT,
 station_name VARCHAR(30),
 PRIMARY KEY(line_num, station_name),
 FOREIGN KEY(line_num) REFERENCES line(line_num) ON DELETE CASCADE,
 FOREIGN KEY(station_name) REFERENCES station(name) ON DELETE CASCADE
 );

CREATE TABLE Amenity(
 amenity VARCHAR(30),
 station_name VARCHAR(30),
 PRIMARY KEY(amenity, station_name),
 FOREIGN KEY(station_name) REFERENCES station(name) ON DELETE CASCADE
 );

CREATE TABLE Customer (
 Id CHAR(10) PRIMARY KEY,
 Fname VARCHAR(20),
 Lname VARCHAR(20),
 Email VARCHAR(35),
 Sex VARCHAR(6) CHECK (sex IN ('Male', 'Female')),
 DOB DATE,
 Phone_number VARCHAR(15));

CREATE TABLE Ticket (
 Ticket_number INT PRIMARY KEY,
 Customer_Id VARCHAR(10),
 Type VARCHAR(8) CHECK (type IN ('2 hours', '3 days', '7 days', '30 days')),
 Validness VARCHAR(10) CHECK (validness IN ('valid', 'invalid')),
 Class VARCHAR(10) CHECK (class IN ('First', 'Normal')),
 Price DECIMAL(6,2),
 Ticket_activated_date DATE,
 Ticket_activated_time TIME,
 FOREIGN KEY (Customer_Id) REFERENCES Customer(Id));

CREATE TABLE Scanned_At (
 Ticket_number INT,
 Station_name VARCHAR(30),
 Scan_date DATE,
 Scan_time TIME,
 PRIMARY KEY (Ticket_number, Station_name),
 FOREIGN KEY (Ticket_number) REFERENCES Ticket(Ticket_number),
 FOREIGN KEY (Station_name) REFERENCES Station(Name));

CREATE TABLE Trip (
 Source VARCHAR(30),
 Destination VARCHAR(30),
 Metro_number INT,
 Departure_time TIME,
 Trip_date DATE,
 PRIMARY KEY (Source, Destination, Departure_time, Trip_date),
 FOREIGN KEY (Metro_number) REFERENCES Metro_Bus(Number));

CREATE TABLE Stop (
 Arrival_time TIME,
 Station_name VARCHAR(30),
 Source VARCHAR(30),
 Destination VARCHAR(30),
 Departure_time TIME,
 Trip_date DATE,
 PRIMARY KEY (Station_name, Source, Destination, Departure_time, Trip_date),
 FOREIGN KEY (Station_name) REFERENCES Station(Name),
 FOREIGN KEY (Source, Destination, Departure_time, Trip_date) REFERENCES Trip(Source, Destination, Departure_time, Trip_date));
