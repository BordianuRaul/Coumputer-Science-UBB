USE Lab4_Week11


--				A
--2 queries with the union operation; use UNION [ALL] and OR;

	
	--A travel agency analyst that needs to find airports that meet specific criteria to offer the best options to his clients.
	
-- First part of the query
SELECT a.airportName, a.airportSize
FROM Airport a
INNER JOIN AirportAirline aa ON a.airportID = aa.airportID
INNER JOIN Airline al ON aa.airlineID = al.airlineID
WHERE (a.airportSize > 200 OR a.airportName LIKE '%International%')
      AND NOT(a.airportName = 'Henri-Coanda')
      AND a.airportSize IS NOT NULL
      AND al.airlineName = 'WizzAir'

UNION


SELECT a.airportName, a.airportSize
FROM Airport a
INNER JOIN AirportAirline aa ON a.airportID = aa.airportID
INNER JOIN Airline al ON aa.airlineID = al.airlineID
WHERE (a.airportSize > 200 OR a.airportName LIKE '%International%')
      AND NOT(a.airportName = 'Henri-Coanda')
      AND a.airportSize IS NOT NULL
      AND al.airlineName = 'RyanAir';


--LA International size 3500
--Charleroi 2000


-- This Query retrieves all airports and their respective airlines, this can be used for example by the manager of the database to see
-- the state of colaboration between each airport and airline



SELECT A.airportName AS Name, B.airlineName AS Company
FROM Airport AS A
INNER JOIN AirportAirline AS AA ON A.airportID = AA.airportID
INNER JOIN Airline AS B ON AA.airlineID = B.airlineID

UNION


SELECT A.airportName AS Name, 'Unknown' AS Company
FROM Airport AS A
WHERE A.airportID NOT IN (SELECT DISTINCT airportID FROM AirportAirline);



--				B
--2 queries with the intersection operation; use INTERSECT and IN;

-- Query to find airlines with both delayed and on-time flights at the same airport
SELECT A.airlineName AS Airline
FROM Airline AS A
WHERE A.airlineID IN (
  SELECT airlineID FROM AirportAirline
  WHERE flightStatus = 'OnTime'
)

INTERSECT

SELECT A.airlineName AS Airline
FROM Airline AS A
WHERE A.airlineID IN (
  SELECT airlineID FROM AirportAirline
  WHERE flightStatus = 'DELAYED'
);


-- Query to find common airlines serving both Cluj-Napoca and Los Angeles airports
SELECT A1.airlineName AS CommonAirlines
FROM Airline AS A1
WHERE A1.airlineID IN (
  SELECT AA1.airlineID
  FROM AirportAirline AS AA1
  WHERE AA1.airportID = (SELECT airportID FROM Airport WHERE airportName = 'Charleroi')
)

INTERSECT

SELECT A2.airlineName AS CommonAirlines
FROM Airline AS A2
WHERE A2.airlineID IN (
  SELECT AA2.airlineID
  FROM AirportAirline AS AA2
  WHERE AA2.airportID = (SELECT airportID FROM Airport WHERE airportName = 'Los Angeles International')
);


--			C
--2 queries with the difference operation; use EXCEPT and NOT IN;


-- Query to find airlines serving Cluj-Napoca but not serving Los Angeles International
SELECT A1.airlineName AS Airline
FROM Airline AS A1
WHERE A1.airlineID IN (
  SELECT AA1.airlineID
  FROM AirportAirline AS AA1
  WHERE AA1.airportID = (SELECT airportID FROM Airport WHERE airportName = 'Charleroi')
)
EXCEPT
SELECT A2.airlineName AS Airline
FROM Airline AS A2
WHERE A2.airlineID IN (
  SELECT AA2.airlineID
  FROM AirportAirline AS AA2
  WHERE AA2.airportID = (SELECT airportID FROM Airport WHERE airportName = 'Los Angeles International')
);

-- Query to find airlines serving Charleroi with no "OnTime" flights
SELECT A1.airlineName AS AirlineWithNoONTIME
FROM Airline AS A1
WHERE A1.airlineID IN (
  SELECT AA1.airlineID
  FROM AirportAirline AS AA1
  WHERE AA1.airportID = (SELECT airportID FROM Airport WHERE airportName = 'Charleroi')
)
AND A1.airlineID NOT IN (
  SELECT AA2.airlineID
  FROM AirportAirline AS AA2
  WHERE AA2.airportID = (SELECT airportID FROM Airport WHERE airportName = 'Charleroi') 
  AND AA2.flightStatus = 'OnTime'
);


--			D
--4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN (one query per operator); one query will join at least 3 tables, while another one will join at least two many-to-many relationships;

-- Query to list airports and their associated parking lots, including airports without parking lots
SELECT A.airportName, P.capacity  AS ParkingCapacity
FROM Airport AS A
LEFT JOIN ParkingLot AS P ON A.airportID = P.airportID;

-- Query to list airlines and their associated airport-airline relationships, including airlines with no relationships
SELECT A.airlineName, AA.flightStatus
FROM Airline AS A
RIGHT JOIN AirportAirline AS AA ON A.airlineID = AA.airlineID;

-- Query to list airlines and their associated airport-airline relationships, including airlines with no relationships and relationships with no associated airlines
SELECT A.airlineName, AA.flightStatus
FROM Airline AS A
FULL JOIN AirportAirline AS AA ON A.airlineID = AA.airlineID;


-- Query to list airlines and the airports they serve through the airport-airline and airport-runway relationships
SELECT A.airlineName, AR.airportName, R.orientation
FROM Airline AS A
INNER JOIN AirportAirline AS AA ON A.airlineID = AA.airlineID
INNER JOIN Airport AS AR ON AA.airportID = AR.airportID
LEFT JOIN Runway AS R ON AR.airportID = R.airportID;

--			E
--2 queries with the IN operator and a subquery in the WHERE clause;
--in at least one case, the subquery must include a subquery in its own WHERE clause;

-- Query to find airlines serving airports based on a list of airport names from a subquery
SELECT A.airlineName AS Airlines
FROM Airline AS A
WHERE A.airlineID IN (
  SELECT AA.airlineID
  FROM AirportAirline AS AA
  WHERE AA.airportID IN (
    SELECT airportID
    FROM Airport
    WHERE airportName IN ('Cluj-Napoca', 'Iasi')
  )
);

--			F
--2 queries with the EXISTS operator and a subquery in the WHERE clause;

-- Query to find airlines with at least one flight in "DELAYED" status
SELECT A.airlineName
FROM Airline AS A
WHERE EXISTS (
  SELECT 1
  FROM AirportAirline AS AA
  WHERE AA.airlineID = A.airlineID
  AND AA.flightStatus = 'DELAYED'
);



-- Query to find airlines that have at least one airport with "OnTime" flights
SELECT AL.airlineName as OnTimeAirports
FROM Airline AS AL
WHERE EXISTS (
  SELECT 1
  FROM AirportAirline AS AA
  WHERE AA.airlineID = AL.airlineID
  AND EXISTS (
    SELECT 1
    FROM Airport AS AR
    WHERE AR.airportID = AA.airportID
    AND EXISTS (
      SELECT 1
      FROM AirportAirline AS AA2
      WHERE AA2.airlineID = AL.airlineID
      AND AA2.flightStatus = 'OnTime'
	  )
    )
  );



  -- G
  -- 2 queries with a subquery in the FROM clause

	-- Query to find airports and airlines that have cooperation
SELECT A.airportName, AL.airlineName
FROM (
  SELECT DISTINCT AA.airportID, AA.airlineID
  FROM AirportAirline AS AA
) AS Cooperation
JOIN Airport AS A ON Cooperation.airportID = A.airportID
JOIN Airline AS AL ON Cooperation.airlineID = AL.airlineID;


-- Query to find airlines with the most cooperation with airports
SELECT AL.airlineName, COUNT(*) AS CooperationCount
FROM (
  SELECT DISTINCT AA.airlineID, AA.airportID
  FROM AirportAirline AS AA
) AS Cooperation
JOIN Airline AS AL ON Cooperation.airlineID = AL.airlineID
GROUP BY AL.airlineName
HAVING COUNT(*) = (
  SELECT MAX(CountCooperation)
  FROM (
    SELECT AA2.airlineID, COUNT(*) AS CountCooperation
    FROM AirportAirline AS AA2
    GROUP BY AA2.airlineID
  ) AS CooperationCounts
);

--				H
--h. 4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause; 2 of the latter will also have a subquery in the HAVING clause;
--use the aggregation operators: COUNT, SUM, AVG, MIN, MAX;


-- Query to find airports with the highest number of parking lots
SELECT AR.airportName, COUNT(PL.parkingLotID) AS NumberOfParkingLots
FROM Airport AS AR
LEFT JOIN ParkingLot AS PL ON AR.airportID = PL.airportID
GROUP BY AR.airportName
HAVING COUNT(PL.parkingLotID) = (
  SELECT MAX(CountParkingLots)
  FROM (
    SELECT AR2.airportID, COUNT(PL2.parkingLotID) AS CountParkingLots
    FROM Airport AS AR2
    LEFT JOIN ParkingLot AS PL2 ON AR2.airportID = PL2.airportID
    GROUP BY AR2.airportID
  ) AS ParkingLotCounts
);


-- Query to find the total capacity of parking lots at each airport
SELECT AR.airportName, SUM(PL.capacity) AS TotalCapacity
FROM Airport AS AR
LEFT JOIN ParkingLot AS PL ON AR.airportID = PL.airportID
GROUP BY AR.airportName;

-- Query to find airlines operating at airports with more than one runway
SELECT AL.airlineName
FROM Airline AS AL
JOIN AirportAirline AS AA ON AL.airlineID = AA.airlineID
JOIN (
  SELECT airportID, COUNT(*) AS RunwayCount
  FROM Runway
  GROUP BY airportID
  HAVING COUNT(*) > 0
) AS MultipleRunways
ON AA.airportID = MultipleRunways.airportID;

-- Query to find airlines that have both the shortest and longest runways (assuming each airport has only one runway)
SELECT AL.airlineName, R.length AS RunwayLength
FROM Airline AS AL
JOIN AirportAirline AS AA ON AL.airlineID = AA.airlineID
JOIN Runway AS R ON AA.airportID = R.airportID
WHERE R.length = (SELECT MIN(length) FROM Runway)
OR R.length = (SELECT MAX(length) FROM Runway);

--		I
-- 4 queries using ANY and ALL to introduce a subquery in the WHERE clause (2 queries per operator);
--rewrite 2 of them with aggregation operators, and the other 2 with IN / [NOT] IN.


-- Query to find airports with at least one airline having "OnTime" flights using ANY and a subquery
SELECT AR.airportName
FROM Airport AS AR
WHERE AR.airportID = ANY (
  SELECT airportID
  FROM AirportAirline AS AA
  WHERE AA.flightStatus = 'OnTime'
);


-- Query to find airports where all airlines have "OnTime" flights using ALL and a subquery
SELECT AR.airportName
FROM Airport AS AR
WHERE NOT EXISTS (
  SELECT airlineID
  FROM Airline AS AL
  WHERE NOT EXISTS (
    SELECT 1
    FROM AirportAirline AS AA
    WHERE AA.airportID = AR.airportID
    AND AA.airlineID = AL.airlineID
    AND AA.flightStatus = 'OnTime'
  )
);


-- Query to find airports with at least one airline having "OnTime" flights using IN and a subquery
SELECT AR.airportName
FROM Airport AS AR
WHERE AR.airportID IN (
  SELECT airportID
  FROM AirportAirline AS AA
  WHERE AA.flightStatus = 'OnTime'
);


-- Query to find airports where no airline has "Delayed" flights using NOT IN and a subquery
SELECT AR.airportName
FROM Airport AS AR
WHERE AR.airportID NOT IN (
  SELECT DISTINCT airportID
  FROM AirportAirline AS AA
  WHERE AA.flightStatus = 'DELAYED'
);

--Find the nr of runways for all airports and order them accordingly

SELECT AL.airlineName, MultipleRunways.RunwayCount
FROM Airline AS AL
JOIN AirportAirline AS Flight ON AL.airlineID = Flight.airlineID
JOIN (
  SELECT airportID, COUNT(*) AS RunwayCount
  FROM Runway
  GROUP BY airportID
  HAVING COUNT(*) > 0
) AS MultipleRunways
ON Flight.airportID = MultipleRunways.airportID
ORDER BY MultipleRunways.RunwayCount;


SELECT TOP (7) AL.airlineName, MultipleRunways.RunwayCount
FROM Airline AS AL
JOIN AirportAirline AS Flight ON AL.airlineID = Flight.airlineID
JOIN (
  SELECT airportID, COUNT(*) AS RunwayCount
  FROM Runway
  GROUP BY airportID
  HAVING COUNT(*) > 0
) AS MultipleRunways
ON Flight.airportID = MultipleRunways.airportID
ORDER BY MultipleRunways.RunwayCount;
