USE Lab4_Week11

CREATE TABLE Airport
(
    airportID INT CONSTRAINT primaryKeyConstraint PRIMARY KEY IDENTITY(1,1),
    airportSize INT,
    airportName VARCHAR(200)
)


CREATE TABLE Apron
(
    apronId INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    longitude FLOAT,
    latitude FLOAT
)


CREATE TABLE Airplane
(
    airplaneID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    apronID INT REFERENCES Apron(apronID),
    airplaneModel VARCHAR(100),
    airplaneLength INT
)


CREATE TABLE Heliport
(
    heliportID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    longitude FLOAT,
    latitude FLOAT
)


CREATE TABLE Airline
(
    airlineID INT PRIMARY KEY IDENTITY(1,1),
    airlineName VARCHAR(100)
)


CREATE TABLE AirportAirline
(
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    airlineID INT REFERENCES Airline(airlineID) ON DELETE CASCADE,
    PRIMARY KEY (airportID, airlineID),
    flightStatus VARCHAR(200)
)


CREATE TABLE Runway
(
    runwayID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT CONSTRAINT runwayForeignKeyConstraint REFERENCES Airport(airportID) ON DELETE CASCADE,
    length FLOAT,
    orientation VARCHAR(10)
)


CREATE TABLE TaxiStation
(
    taxiStationID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    capacity INT
)

CREATE TABLE ATCTower
(
    atcTowerID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    nrOfOperators INT
)

CREATE TABLE ParkingLot
(
    parkingLotID INT CONSTRAINT primaryKeyConstraintParkingLot PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    capacity INT
)
