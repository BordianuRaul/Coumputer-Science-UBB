USE Lab4_Week11
GO

ALTER PROCEDURE insertRows
	@nrRows INT,
	@tableName varchar(30)
	AS
	BEGIN
		SET NOCOUNT ON;

		DECLARE @table varchar(100)
		SET @table = ('[' + @tableName + ']')

		IF @tableName = 'Airport' OR  @tableName = 'Apron'
		BEGIN
			DBCC  CHECKIDENT (@table, RESEED, 0);
		END

		DECLARE @contor INT
		SET @contor = 1

		--Airport data
		
		DECLARE @airportSize INT
		SET @airportSize = 100

		DECLARE @airportName varchar(100)
		SET @airportName = 'AirportName'

		--Apron data

		DECLARE @airportID INT
		SET @airportID = 1

		DECLARE  @longitude FLOAT
		SET @longitude = 43.14

		DECLARE  @latitude FLOAT
		SET @latitude = -3.11

		--Airplane data
		DECLARE @apronID INT
		SET @apronID = 1
		
		DECLARE @airplaneModel varchar(100)
		SET @airplaneModel = 'modelXVC'

		DECLARE @airplaneLength INT
		SET @airplaneLength = 30

		--POPULATE 
		WHILE @contor <= @nrRows
		BEGIN

			IF @tableName = 'Airport'
			BEGIN
				SET @airportSize = @airportSize + 1
				SET @airportName = 'Airport' +  convert(varchar(7), @contor)
				INSERT INTO Airport(airportSize, airportName) VALUES(@airportSize, @airportName)
			END

			IF @tableName = 'Apron'
			BEGIN
				SET @airportID = (SELECT TOP 1 airportID FROM Airport ORDER BY NEWID()) 
				SET @latitude = @latitude + 1
				SET @longitude = @longitude + 1
				INSERT INTO Apron(airportID, latitude, longitude) VALUES(@airportID, @latitude, @longitude)

			END

			IF @tableName = 'Airplane'
			BEGIN
				SET @airportID = (SELECT TOP 1 airportID FROM Airport ORDER BY NEWID()) 
				SET @apronID = (SELECT TOP 1 apronID FROM Apron ORDER BY NEWID()) 
				SET @airplaneModel = 'Model:' +  convert(varchar(7), @contor)
				SET @airplaneLength = @airplaneLength + 1
				INSERT INTO Airplane(airportId, apronID, airplaneModel, airplaneLength) VALUES (@airportID, @apronID, @airplaneModel, @airplaneLength)

			END

			SET @contor = @contor + 1

		END

	END