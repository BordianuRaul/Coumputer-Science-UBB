USE TryStuff;

-- Reset identity values for the Airport table
DBCC CHECKIDENT ('Airport', RESEED, 0);

-- Reset identity values for the Apron table
DBCC CHECKIDENT ('Apron', RESEED, 0);

-- Reset identity values for the Airplane table
DBCC CHECKIDENT ('Airplane', RESEED, 0);

-- Reset identity values for the Heliport table
DBCC CHECKIDENT ('Heliport', RESEED, 0);

-- Reset identity values for the Airline table
DBCC CHECKIDENT ('Airline', RESEED, 0);

-- Reset identity values for the Runway table
DBCC CHECKIDENT ('Runway', RESEED, 0);

-- Reset identity values for the TaxiStation table
DBCC CHECKIDENT ('TaxiStation', RESEED, 0);

-- Reset identity values for the ATCTower table
DBCC CHECKIDENT ('ATCTower', RESEED, 0);

-- Reset identity values for the ParkingLot table
DBCC CHECKIDENT ('ParkingLot', RESEED, 0);


-- Delete all data from the tables
DELETE FROM AirportAirline;
DELETE FROM Airplane;
DELETE FROM Apron;
DELETE FROM Runway;
DELETE FROM TaxiStation;
DELETE FROM ATCTower;
DELETE FROM ParkingLot;
DELETE FROM Heliport;
DELETE FROM Airline;
DELETE FROM Airport;
