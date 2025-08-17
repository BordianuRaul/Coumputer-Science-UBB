USE Zoo;

-- Set the isolation level to READ UNCOMMITTED
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

-- Start a transaction
BEGIN TRANSACTION;

-- Update some data in the Animals table
UPDATE Animals
SET name = 'Nasdasd'
WHERE speciesID = 1;

-- Introduce a delay (simulating a long-running transaction)
WAITFOR DELAY '00:00:10'; -- 10 seconds delay

-- Commit or roll back the transaction
-- COMMIT; -- Uncomment to commit the transaction
ROLLBACK; -- Uncomment to roll back the transaction
