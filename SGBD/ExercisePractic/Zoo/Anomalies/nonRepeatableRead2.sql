USE Zoo;

SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- Start a transaction
BEGIN TRANSACTION;

-- Update the data in the Animals table
UPDATE Animals
SET name = 'Nemo Updated'
WHERE speciesID = 1;

-- Commit the transaction
COMMIT;