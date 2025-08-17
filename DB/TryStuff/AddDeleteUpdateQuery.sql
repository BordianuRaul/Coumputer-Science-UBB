USE TryStuff

INSERT INTO Airport(airportSize, airportName)
VALUES
(400, 'Cluj-Napoca'),
(100, 'Henri-Coanda'),
(500, 'Iasi'),
(2000, 'Charleroi'), 
(1000, 'France'),
(3500, 'Los Angeles International');

INSERT INTO Airport (airportName)
VALUES ('AirportWithNullSize');

INSERT INTO Airline(airlineName)
VALUES
('WizzAir'),
('RyanAir'),
('Tarom'),
('AirFrance'),
('QatarAirways');


INSERT INTO AirportAirline(airportID, airlineID, flightStatus)
VALUES
(1, 1, 'OnTime'),
(1, 2, 'DELAYED'),
(6, 3, 'OnTime'),
--(2, 8, 'CANCELED'), 
(5, 5, 'OnTime'),
(3, 4, 'Landed'),
(2,3, 'Arrived'),
(1,3, 'DELAYED'),
(4, 3, 'OnTime'),
(4,4, 'OnTime'),
(6,1, 'OnTime'),
(4,2, 'DELAYED'),
(3, 1, 'OnTime'),
(3, 2, 'OnTime');


INSERT INTO ParkingLot(airportID, capacity)
VALUES
(1, 100),
(2, 200), 
(3, 400), 
(4, 400), 
(5, 500); 

-- Sample data for the Runway table
INSERT INTO Runway(airportID, length, orientation)
VALUES
(1, 2500, 'N'),
(1, 3000, 'S'),
(3, 3200, 'W'),
(4, 3500, 'E'),
(6, 3800, 'N'),
(5, 4000, 'S'),
(2, 4200, 'W');


-- Update the Airport table to reflect changes in airport size due to extension plans
UPDATE Airport
SET airportSize = 999
WHERE airportName = 'Iasi';

-- Update the Airline table to reflect a name change due to acquisition by another company
UPDATE Airline
SET airlineName = 'MiddleEastAirlines'
WHERE airlineName LIKE '%QATAR%';

-- Update the AirportAirline table to indicate delayed flights for a specific airline and airport
UPDATE AirportAirline
SET flightStatus = 'DELAYED'
WHERE airlineID = 1 AND airportID = 1

-- Delete a specific entry in the AirportAirline table due to the termination of the airline's cooperation with the airport
DELETE FROM AirportAirline
WHERE airlineID = 5 AND airportID = 5; 

-- Delete the Airport beacause it is to small and is not profitable
DELETE FROM Airport
WHERE airportSize <= 200;

DELETE FROM Airport
WHERE airportSize IS NULL;

-- Delete the flights that have landed
DELETE FROM AirportAirline
WHERE flightStatus IN ('Landed','Arrived');


-- Delete the Airport beacause it is to small and is not profitable
DELETE FROM Airport
WHERE airportSize BETWEEN 100 AND 400;

