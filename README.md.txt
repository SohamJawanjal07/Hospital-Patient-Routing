# Hospital Patient Triage and Routing System

## Project Overview

The Hospital Patient Triage and Routing System is a Core Java, JDBC, and MySQL-based console application. It reads patient records from a database, validates patient information, and routes patients to Critical Care or General Care based on their condition and triage score.

## Technologies Used

* Java
* JDBC
* MySQL
* MySQL Workbench
* Maven
* Eclipse IDE

## Database Details

* Database Name: `batch_k_1335`
* Database Username: `root`

Configure your MySQL username and password in `DBConnection.java` before running the application.

## Main Features

* Fetch pending patient records from MySQL.
* Validate patient details.
* Route critical patients to Critical Care.
* Route other eligible patients to General Care.
* Prevent duplicate patient transfers.
* Use JDBC PreparedStatement for database operations.
* Manage transactions using commit and rollback.
* Display a processing summary in the console.

## Project Structure

* `src/` – Java source code
* `pom.xml` – Maven project configuration
* `01_database.sql` – Creates the database and tables
* `02_sample_data.sql` – Inserts sample patient records
* `screenshots/` – Contains project execution and database screenshots

## How to Run the Project

1. Open MySQL Workbench and connect to your MySQL server.
2. Execute `01_database.sql` to create the database and tables.
3. Execute `02_sample_data.sql` to insert sample patient records.
4. Configure the database connection in `DBConnection.java`.
5. Import or open the Maven project in Eclipse.
6. Run `HospitalRoutingApp.java` as a Java application.
7. Check the console output.
8. Verify the source and destination tables in MySQL Workbench.

## Expected Output

The application processes pending patients, validates their details, routes eligible records to the appropriate destination table, and displays a processing summary.

## Submission Contents

* Complete Java Maven project
* Database creation SQL script
* Sample patient data SQL script
* Console output screenshot
* MySQL Workbench screenshots
* Rollback/failure demonstration

## Author

Soham Jawanjal
