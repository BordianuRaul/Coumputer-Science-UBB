USE seminar5

SELECT * FROM Trains

SELECT * FROM TrainTypes

SELECT * FROM Stations

SELECT * FROM  Routes

SELECT * FROM   RoutesStations

--2.B PROCEDURE


CREATE OR ALTER PROCEDURE addStation
	@rName VARCHAR(50),
	@sName VARCHAR(50),
	@arrival TIME,
	@departure TIME

AS
BEGIN
	DECLARE @rID INT = (SELECT route_id FROM Routes R WHERE R.route_name = @rName)
	DECLARE @sID INT = (SELECT station_id FROM Stations S WHERE S.station_name = @sName)

	IF(EXISTS(SELECT * FROM RoutesStations RS WHERE RS.station_id = @sID AND RS.route_id = @rID))
		UPDATE RoutesStations SET arrival_time = @arrival, departation_time = @departure WHERE route_id = @rID AND station_id = @sID
	ELSE
		INSERT INTO RoutesStations(station_id, route_id, departation_time, arrival_time) VALUES (@sID, @rID, @departure, @arrival)
		
END

EXECUTE addStation 'Route 3', 'Station Z', '1:00am', '1:11am'

--2.C VIEW

CREATE OR ALTER VIEW trainsNames
AS
	
	SELECT route_name
	FROM Routes
	WHERE route_id IN
		(
			SELECT route_id
			FROM RoutesStations
			GROUP BY route_id
			HAVING COUNT(*) = (SELECT COUNT(*) FROM Stations)
		)

GO

SELECT * FROM trainsNames


--2.D FUNCTION

CREATE OR ALTER FUNCTION NamesOfStations (@R INT)
    RETURNS TABLE
AS
RETURN
(
    SELECT station_name
    FROM Stations
    WHERE station_id IN
        (SELECT station_id
         FROM RoutesStations
         GROUP BY station_id
         HAVING COUNT(*) > @R)
);
GO

SELECT *
FROM dbo.NamesOfStations(1)

