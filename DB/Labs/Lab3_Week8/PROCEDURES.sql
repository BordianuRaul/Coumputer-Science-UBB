use Lab3_Week8;

--modify the type of a column;

CREATE PROCEDURE upToVersion1
AS
	ALTER TABLE Runway
	ALTER COLUMN length INT;
GO

CREATE PROCEDURE downToVersion0
AS
	ALTER TABLE Runway
	ALTER COLUMN length FLOAT;
GO

EXEC upToVersion1
EXEC downToVersion0

--add / remove a column;

CREATE PROCEDURE upToVersion2
AS
	ALTER TABLE ParkingLot
	ADD freeSpots INT

GO

CREATE PROCEDURE downToVersion1
AS
	ALTER TABLE ParkingLot
	DROP COLUMN freeSpots
GO

EXEC upToVersion2
EXEC downToVersion1


--add / remove a DEFAULT constraint

CREATE PROCEDURE upToVersion3
AS
	ALTER TABLE ParkingLot
	ADD CONSTRAINT defaultCapacity DEFAULT 0 FOR capacity
GO

CREATE PROCEDURE downToVersion2
AS
	ALTER TABLE ParkingLot
	DROP CONSTRAINT defaultCapacity
GO

EXEC upToVersion3;

SELECT * FROM ParkingLot;

INSERT INTO ParkingLot(airportID)
VALUES(3)

EXEC downToVersion2

--add / remove a primary key;

CREATE PROCEDURE upToVersion4
AS
	ALTER TABLE ParkingLot
	DROP CONSTRAINT primaryKeyConstraintParkingLot;
GO

CREATE PROCEDURE downToVersion3
AS
	ALTER TABLE ParkingLot
	ADD CONSTRAINT primaryKeyConstraintParkingLot PRIMARY KEY (parkingLotID);
GO

EXEC upToVersion4
EXEC downToVersion3

--add / remove a candidate key


CREATE PROCEDURE upToVersion5
AS
	ALTER TABLE Airline
	ADD CONSTRAINT airlineNameUniqueConstraint UNIQUE (airlineName);
GO

CREATE PROCEDURE downToVersion4
AS
	ALTER TABLE Airline
	DROP CONSTRAINT airlineNameUniqueConstraint;
GO

EXEC upToVersion5;
EXEC downToVersion4;

--add / remove a foreign key;

CREATE PROCEDURE upToVersion6
AS
	ALTER TABLE Runway
	DROP CONSTRAINT runwayForeignKeyConstraint;
GO

EXEC upToVersion6;

CREATE PROCEDURE downToVersion5
AS
	ALTER TABLE  Runway
	ADD CONSTRAINT runwayForeignKeyConstraint FOREIGN KEY(airportID) REFERENCES Airport(airportID);
GO

EXEC downToVersion5

--create / drop a table.

ALTER PROCEDURE upToVersion7
AS
	DROP TABLE Heliport
GO

SELECT * FROM ParkingLot;

EXEC upToVersion7;

ALTER PROCEDURE downToVersion6
AS
		CREATE TABLE Heliport
	(
		heliportID INT PRIMARY KEY IDENTITY(1,1),
		airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
		longitude FLOAT,
		latitude FLOAT
	)
GO

EXEC downToVersion6;
