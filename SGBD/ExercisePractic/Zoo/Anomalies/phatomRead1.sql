use Zoo;

-- Set isolation level to Read Committed
SET TRANSACTION ISOLATION LEVEL READ COMMITTED;

-- Transaction 1: Start and read species in class 1
BEGIN TRANSACTION;
SELECT * FROM Specie WHERE classID = 1;
-- Keep this transaction open

-- Open a new session/connection for Transaction 2

-- Transaction 2: Insert a new species and commit
BEGIN TRANSACTION;
INSERT INTO Specie (latinName, romanianName, classID) VALUES ('Panthera pardus', 'Leopard', 1);
COMMIT TRANSACTION;

-- Back to Transaction 1: Re-read species in class 1
SELECT * FROM Specie WHERE classID = 1;
-- Commit Transaction 1
COMMIT TRANSACTION;
