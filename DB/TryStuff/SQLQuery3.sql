USE TryStuff

-- Create Airport table with cascade delete
CREATE TABLE Airport
(
    airportID INT PRIMARY KEY IDENTITY(1,1),
    airportSize INT,
    airportName VARCHAR(200)
)

-- Create Apron table with cascade delete
CREATE TABLE Apron
(
    apronId INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    longitude FLOAT,
    latitude FLOAT
)

-- Create Airplane table with cascade delete for 'apronID' only
CREATE TABLE Airplane
(
    airplaneID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    apronID INT REFERENCES Apron(apronID),
    airplaneModel VARCHAR(100),
    airplaneLength INT
)

-- Create Heliport table with cascade delete
CREATE TABLE Heliport
(
    heliportID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    longitude FLOAT,
    latitude FLOAT
)

-- Create Airline table
CREATE TABLE Airline
(
    airlineID INT PRIMARY KEY IDENTITY(1,1),
    airlineName VARCHAR(100)
)

-- Create AirportAirline table with cascade delete
CREATE TABLE AirportAirline
(
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    airlineID INT REFERENCES Airline(airlineID) ON DELETE CASCADE,
    PRIMARY KEY (airportID, airlineID),
    flightStatus VARCHAR(200)
)

-- Create Runway table with cascade delete
CREATE TABLE Runway
(
    runwayID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    length FLOAT,
    orientation VARCHAR(10)
)

-- Create TaxiStation table with cascade delete
CREATE TABLE TaxiStation
(
    taxiStationID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    capacity INT
)

-- Create ATCTower table with cascade delete
CREATE TABLE ATCTower
(
    atcTowerID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    nrOfOperators INT
)

-- Create ParkingLot table with cascade delete
CREATE TABLE ParkingLot
(
    parkingLotID INT PRIMARY KEY IDENTITY(1,1),
    airportID INT REFERENCES Airport(airportID) ON DELETE CASCADE,
    capacity INT
)
