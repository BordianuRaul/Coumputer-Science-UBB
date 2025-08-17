USE Zoo;

-- Set the isolation level to READ UNCOMMITTED
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

-- Start a transaction
BEGIN TRANSACTION;

-- Read the data that was updated by Session 1
SELECT name
FROM Animals
WHERE speciesID = 1;

-- End the transaction
COMMIT;
