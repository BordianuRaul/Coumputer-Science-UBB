use Zoo;

BEGIN TRANSACTION;
INSERT INTO Specie (latinName, romanianName, classID) VALUES ('Panthera pardus', 'Leopard', 1);
COMMIT TRANSACTION;