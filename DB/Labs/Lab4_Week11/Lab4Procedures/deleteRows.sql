USE Lab4_Week11
GO

CREATE PROCEDURE deleteRows
	@nrOfRows INT,
	@tableName varchar(30)
AS
BEGIN

	SET NOCOUNT ON;

	DECLARE @lastRow INT

		IF @tableName = 'Airport'
		BEGIN
			SET @lastRow = (SELECT MAX(airportID) from Airport) - @nrOfRows

			DELETE FROM Airport
			WHERE airportId > @lastRow
		END
		
		IF @tableName = 'Apron'
		BEGIN
			SET @lastRow = (SELECT MAX(apronID) from Apron) - @nrOfRows

			DELETE FROM Apron
			WHERE apronID > @lastRow
		END

		IF @tableName = 'Airplane'
		BEGIN
			DELETE FROM Airplane
		END
END


CREATE OR ALTER PROCEDURE deleteAllData
	 @tableName NVARCHAR(30)
AS
BEGIN
    
	IF @tableName = 'Airport'
		BEGIN
			DELETE FROM Airport
			DBCC CHECKIDENT ('Airport', RESEED, 0); 
		END
		
		IF @tableName = 'Apron'
		BEGIN

			DELETE FROM Apron
			DBCC CHECKIDENT ('Apron', RESEED, 0); 
		END

		IF @tableName = 'Airplane'
		BEGIN
			DELETE FROM Airplane
			DBCC CHECKIDENT ('Airplane', RESEED, 0); 
		END
END
