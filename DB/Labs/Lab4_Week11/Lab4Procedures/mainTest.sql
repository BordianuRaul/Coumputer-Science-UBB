USE [Lab4_Week11]


--UTILS

CREATE TABLE AuxTable(
		id INT PRIMARY KEY IDENTITY(1,1),
		tableID INT,
		nrRows INT,
		position INT
	)


SELECT * FROM AuxTable

CREATE TABLE AuxViews(
		id INT PRIMARY KEY IDENTITY(1,1),
		testID INT,
		viewId INT
	)


--Prepare Tables
INSERT INTO Tables(Name) VALUES 
('Airport'),
('Apron'),
('Airplane');



INSERT INTO Views(Name) VALUES
('view1Table'),
('view2Tables'),
('view2TablesGroupBy');

GO

INSERT INTO Tests VALUES
('Test1'),
('Test2'),
('TestView');

--Prepare tests formats

SELECT * FROM Tests

INSERT INTO TestTables VALUES
(1, 1, 1000, 3),
(2, 1, 10, 1),
(1, 2, 100, 2),
(1, 3, 10, 1);

INSERT INTO TestViews VALUES
(3, 1),
(3, 2),
(3, 3);

INSERT INTO TestViews VALUES
(1, 1);


-- MAIN PROCEDURE
ALTER PROCEDURE mainTest
	@testName VARCHAR(100)
AS BEGIN
	
	--Stuff for the table tests
	DECLARE @testId INT
	SET @testId = (SELECT Tests.testID FROM Tests WHERE Tests.Name = @testName)

	DELETE FROM AuxTable;
	DBCC CHECKIDENT('AuxTable', RESEED, 0);

	INSERT INTO AuxTable (tableID, nrRows, position)
    SELECT TestTables.TableID, TestTables.NoOfRows, TestTables.Position
    FROM TestTables
    WHERE TestTables.TestID = @testId
    ORDER BY TestTables.Position ASC;

	DECLARE @nrOfRowsFromAux INT
	SET @nrOfRowsFromAux = (SELECT MAX(id) FROM AuxTable)

	DECLARE @currentRow INT
	SET @currentRow = 1

	DECLARE @tableId INT
	DECLARE @tableName VARCHAR(30)

	INSERT INTO TestRuns
	VALUES
	(@testName, 0, 1)
	DECLARE @currentTestRunsID INT

	SET @currentTestRunsID = (SELECT MAX(TestRunID) FROM TestRuns)

	IF @currentTestRunsID IS NULL
		SET @currentTestRunsID = 1;


	DECLARE @start DATETIME
	DECLARE @end DATETIME
	DECLARE @globalStart DATETIME
	DECLARE @globalEnd DATETIME

	SET @globalStart = GETDATE()
	WHILE @currentRow <= @nrOfRowsFromAux
	BEGIN
			
			SET @tableId = (SELECT AuxTable.tableID FROM AuxTable WHERE @currentRow = AuxTable.id)
			SET @tableName = (SELECT Tables.Name FROM Tables WHERE Tables.TableID = @tableId)

			EXEC deleteAllData @tableName

			SET @currentRow = @currentRow + 1;
	END

	SET @currentRow = @nrOfRowsFromAux
	WHILE @currentRow > 0
	BEGIN

			SET @tableId = (SELECT AuxTable.tableID FROM AuxTable WHERE @currentRow = AuxTable.id)
			SET @tableName = (SELECT Tables.Name FROM Tables WHERE Tables.TableID = @tableId)

			DECLARE @nrRows INT
			SET @nrRows = (SELECT AuxTable.nrRows FROM AuxTable WHERE @currentRow = AuxTable.id)

			SET @start = GETDATE()

			EXEC insertRows @nrRows, @tableName

			SET @end = GETDATE()

			INSERT INTO TestRunTables
			VALUES (@currentTestRunsID, @tableId, @start, @end)

			SET @currentRow = @currentRow - 1
	END

	--Stuff for the view tests

	DELETE FROM AuxViews;
	DBCC CHECKIDENT('AuxViews', RESEED, 0);

	INSERT INTO AuxViews(testId, viewID)
	SELECT TestViews.TestID, TestViews.ViewID
	FROM TestViews
	WHERE TestViews.TestID = @testId

	SET @nrOfRowsFromAux = (SELECT MAX(id) FROM AuxViews)
	SET @currentRow = 1
	
	DECLARE @viewID INT
	WHILE @currentRow <= @nrOfRowsFromAux
	BEGIN

		SET @viewID = (SELECT AuxViews.viewID FROM AuxViews WHERE AuxViews.id = @currentRow)

		DECLARE @viewName varChar(50)
		SET @viewName = (SELECT Views.Name FROM Views WHERE Views.ViewID = @viewID)
		
		SET @start = GETDATE()

		EXEC selectView @viewName

		SET @end = GETDATE()

		INSERT INTO TestRunViews
		VALUES
		(@currentTestRunsID, @viewID, @start, @end)

		SET @currentRow = @currentRow + 1

	END
	
	SET @globalEnd = GETDATE()

	UPDATE TestRuns
	SET TestRuns.StartAt = @globalStart, TestRuns.EndAt = @globalEnd
	WHERE TestRuns.TestRunID = @currentTestRunsID
	
END

SELECT * FROM TestRuns
DELETE FROM [TestRuns]
DBCC CHECKIDENT ('TestRuns', RESEED, 0);

SELECT * FROM Tests

SELECT * FROM Views

SELECT * FROM Tables

SELECT * FROM TestTables

SELECT * FROM TestViews

SELECT * FROM TestRuns

SELECT * FROM TestRunTables

SELECT * FROM TestRunViews


EXEC mainTest 'Test2'


SELECT * FROM Views
SELECT * FROM TestViews

SELECT * FROM Airport
SELECT * FROM Apron
SELECT * FROM Airplane

deleteAllData 'Airplane'
deleteAllData 'Apron'
deleteAllData 'Airport'


-- Delete data from TestRuns
DELETE FROM TestRuns;

-- Reset primary key for TestRuns
DBCC CHECKIDENT ('TestRuns', RESEED, 0); -- For SQL Server

-- Delete data from TestRunTables
DELETE FROM TestRunTables;


-- Delete data from TestRunViews
DELETE FROM TestRunViews;



SELECT * FROM AuxTable
SELECT * FROM AuxViews

DELETE FROM Tables;

DBCC CHECKIDENT ('Tables', RESEED, 0); -- For SQL Server