use Zoo;

-- Start a transaction
BEGIN TRANSACTION;

-- First read of the data
SELECT name
FROM Animals
WHERE speciesID = 1;

-- Introduce a delay to simulate processing time
WAITFOR DELAY '00:00:05'; -- 10 seconds delay

-- Second read of the data
SELECT name
FROM Animals
WHERE speciesID = 1;

-- Commit the transaction
COMMIT;