USE Lab3_Week8

GO

CREATE or alter PROCEDURE InsertDataGrade5
AS
BEGIN
    SET NOCOUNT ON;
    
    DECLARE @ErrorOccurred BIT = 0;
    DECLARE @AirportID INT;

    BEGIN TRANSACTION;

    BEGIN TRY
        -- Insert into tables with m:n relationship
        DECLARE @InsertedAirport TABLE (airportID INT);
        INSERT INTO [Lab3_Week8].[dbo].[Airport] (airportSize, airportName)
        OUTPUT INSERTED.airportID INTO @InsertedAirport
        VALUES (1, 'Airport1');
        
        SELECT @AirportID = airportID FROM @InsertedAirport;

        INSERT INTO [Lab3_Week8].[dbo].[Apron] (airportID, longitude, latitude) VALUES (@AirportID, 10.0, 20.0);

        -- Commit the transaction if all inserts are successful
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        -- Roll back the transaction if an error occurred
        SET @ErrorOccurred = 1;
        IF @@TRANCOUNT > 0
        BEGIN
            ROLLBACK TRANSACTION;
        END
    END CATCH;

    -- If an error occurred, attempt to recover as much data as possible
    IF @ErrorOccurred = 1
    BEGIN
        -- Check if the airport was successfully inserted
        IF EXISTS (SELECT 1 FROM [Lab3_Week8].[dbo].[Airport] WHERE airportName = 'Airport1')
        BEGIN
            SET @AirportID = (SELECT airportID FROM [Lab3_Week8].[dbo].[Airport] WHERE airportName = 'Airport1');

            -- Check if the apron with the corresponding airportID was successfully inserted
            IF EXISTS (SELECT 1 FROM [Lab3_Week8].[dbo].[Apron] WHERE airportID = @AirportID)
            BEGIN
                -- Airport and corresponding apron were successfully inserted
                PRINT 'Airport and Apron insertion succeeded.';
            END
            ELSE
            BEGIN
                -- Apron insertion failed
                -- Roll back only the airport insertion
                PRINT 'Apron insertion failed. Rolling back airport insertion.';
                DELETE FROM [Lab3_Week8].[dbo].[Airport] WHERE airportID = @AirportID;
            END
        END
        ELSE
        BEGIN
            -- Airport insertion failed
            PRINT 'Airport insertion failed.';
        END
    END
END;

exec InsertDataGrade5

select * from Airport
select * from Apron

-- DIRTY READ
--Transaction 1 reads uncommitted data modified by Transaction 2.

-- Transaction 1
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
BEGIN TRANSACTION;
SELECT * FROM [Lab3_Week8].[dbo].[Airport]; -- Read uncommitted data
-- Wait for Transaction 2 to make changes
COMMIT TRANSACTION;

-- Transaction 2
BEGIN TRANSACTION;
UPDATE [Lab3_Week8].[dbo].[Airport] SET airportName = 'NewName' WHERE airportID = 1;
-- Wait for Transaction 1 to read uncommitted data
COMMIT TRANSACTION; -- Transaction 2 commits, but Transaction 1 has already read uncommitted data

--NON REPEATABLE READS
--Transaction 1 reads the same row multiple times but
--gets different results due to Transaction 2 modifying the row between reads.

-- Transaction 1
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRANSACTION;
SELECT * FROM [Lab3_Week8].[dbo].[Airport] WHERE airportID = 1; -- Read data
-- Wait for Transaction 2 to update the same row
SELECT * FROM [Lab3_Week8].[dbo].[Airport] WHERE airportID = 1; -- Read again
COMMIT TRANSACTION;

-- Transaction 2
BEGIN TRANSACTION;
UPDATE [Lab3_Week8].[dbo].[Airport] SET airportSize = 3 WHERE airportID = 1;
COMMIT TRANSACTION;


--PHANTOM READS
--Transaction 1 reads a set of rows that satisfy a certain condition, but Transaction 2 inserts or deletes
--rows that also satisfy that condition, causing Transaction 1 to see different results in subsequent reads.

-- Transaction 1
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
BEGIN TRANSACTION;
SELECT COUNT(*) FROM [Lab3_Week8].[dbo].[Airport]; -- Read count
-- Wait for Transaction 2 to insert a new row
SELECT COUNT(*) FROM [Lab3_Week8].[dbo].[Airport]; -- Read count again
COMMIT TRANSACTION;

-- Transaction 2
BEGIN TRANSACTION;
INSERT INTO [Lab3_Week8].[dbo].[Airport] (airportSize, airportName) VALUES (4, 'NewAirport');
COMMIT TRANSACTION;

-- Transaction 1
BEGIN TRANSACTION;
UPDATE [Lab3_Week8].[dbo].[Airport] SET airportSize = 5 WHERE airportID = 1;
-- Wait for Transaction 2 to acquire lock on another row
-- Transaction 1 tries to update the same row locked by Transaction 2, causing a deadlock
COMMIT TRANSACTION;

--DEADLOCK
--Transaction 1 updates Row A and waits to update Row B,
--while Transaction 2 updates Row B and waits to update Row A, causing a deadlock.

-- Transaction 2
BEGIN TRANSACTION;
UPDATE [Lab3_Week8].[dbo].[Airport] SET airportSize = 6 WHERE airportID = 2;
-- Wait for Transaction 1 to acquire lock on another row
-- Transaction 2 tries to update the same row locked by Transaction 1, causing a deadlock
COMMIT TRANSACTION;
