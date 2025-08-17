USE TryStuff

INSERT INTO Airport(airportSize, airportName)
VALUES
(200, 'Cluj-Napoca'),
(100, 'Henri-Coanda'),
(300, 'Iasi'),
(2000, 'Charleroi'), 
(1000, 'France');

INSERT INTO Airline(airlineName)
VALUES
('WizzAir'),
('RyanAir'),
(NULL), 
('Tarom'),
('QatarAirways');


INSERT INTO AirportAirline(airportID, airlineID, flightStatus)
VALUES
(1, 1, 'OnTime'),
(1, 2, 'DELAYED'),
(2, 6, 'CANCELED'), 
(5, 5, 'OnTime'),
(3, 4, 'Landed');


INSERT INTO ParkingLot(airportID, capacity)
VALUES
(1, 100),
(2, 200), 
(3, 400), 
(4, 400), 
(5, 500); 

-- Populating the ATCTower table
INSERT INTO ATCTower(airportID, nrOfOperators)
VALUES
(1, 5), -- Cluj-Napoca Airport has 5 operators
(2, 3), -- Henri-Coanda Airport has 3 operators
(4, 4), -- Charleroi Airport has 4 operators
(5, 2); -- France Airport has 2 operators

-- Update the Airport table to reflect changes in airport size due to extension plans
UPDATE Airport
SET airportSize = 999
WHERE airportName = 'Iasi'; -- Iasi Airport underwent expansion, increasing its size

-- Update the Airline table to reflect a name change due to acquisition by another company
UPDATE Airline
SET airlineName = 'MiddleEastAirlines'
WHERE airlineName = 'QatarAirways'; -- Qatar Airways was acquired and renamed to Middle East Airlines

-- Update the AirportAirline table to indicate delayed flights for a specific airline and airport
UPDATE AirportAirline
SET flightStatus = 'DELAYED'
WHERE airlineID = 1 AND airportID = 1; -- WizzAir flights experienced delays at Cluj-Napoca Airport

-- Delete a specific entry in the AirportAirline table due to the termination of the airline's cooperation with the airport
DELETE FROM AirportAirline
WHERE airlineID = 5 AND airportID = 5; 

-- Delete the Airport beacause it is to small and is not profitable
DELETE FROM Airport
WHERE airportSize < 200 
