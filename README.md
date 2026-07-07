# Transitly — Metro/Bus Transit Database System

A relational database system for managing a metro/bus transit network — stations, lines, vehicles, seats, tickets, trips, and customers — built as a course project for **IS230: Introduction to Database Systems**, King Saud University, College of Computer and Information Sciences, Department of Information Systems.

**Team:** Group 5 — Madhawi Alsanad, Ashwaq Almuhaysin, Alanoud Alotaibi, Lubna Alrashoud, Layan bin Saleh
**Supervised by:** Dr. Lubna Yousef Alkhalil

## Overview

Transitly models a full public transit system, covering:
- **Stations & Lines** — metro/bus stations, the lines that connect them, and amenities per station
- **Vehicles & Seats** — metro/bus vehicles with seat classes (first/normal) and availability status
- **Tickets & Customers** — ticket types (2 hours, 3 days, 7 days, 30 days), pricing, and customer records
- **Trips & Stops** — scheduled trips between stations, with multi-stop routing

## Project Phases

- **Phase 1:** Entity-Relationship (ER) diagram and design assumptions
- **Phase 2:** Relational schema, SQL implementation (MariaDB), and a Java console application for managing Station records (insert/display) with input validation and error handling

## Tech Stack

- **Database:** MariaDB / SQL
- **Application:** Java (JDBC)

## Files

- `schema.sql` — Full database schema (CREATE TABLE statements for all entities)
- `MARIA.java` — Java console app connecting to the database via JDBC, with a menu to insert and display Station records
- `Transitly_Full_Report.pdf` — Full project report (Phase 1 ER diagram + Phase 2 schema, SQL execution, and Java implementation with screenshots)

## Key Features

- Full referential integrity via foreign keys and cascading deletes where appropriate
- Input validation for station type (Metro/Bus), postal code format, and working hours format
- Graceful SQL exception handling (duplicate keys, data-length violations, connection failures)

## How to Run

1. Set up a MariaDB instance and run `schema.sql` to create the database.
2. Update the database credentials (`url`, `user`, `pwd`) in `MARIA.java`.
3. Compile and run with the MariaDB JDBC connector on the classpath:
   ```
   javac MARIA.java
   java -cp .:mariadb-java-client.jar MARIA
   ```
