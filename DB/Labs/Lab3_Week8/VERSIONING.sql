USE Lab3_Week8

CREATE TABLE CurrentDataBaseSchema
(
	currentVersion INT
)

SELECT * FROM CurrentDataBaseSchema
INSERT INTO CurrentDataBaseSchema
VALUES(0)

ALTER PROCEDURE dataBaseVersioning
	@targetVersion INT
AS
	DECLARE @nextStep varchar(30);
	DECLARE @currentVersion INT
	SET @currentVersion = (SELECT currentVersion FROM CurrentDataBaseSchema)
	
	IF @targetVersion >= 0 AND @targetVersion <= 7
		BEGIN

			IF @targetVersion != @currentVersion
				BEGIN

					WHILE @currentVersion < @targetVersion
						BEGIN
							SET @currentVersion = @currentVersion + 1
							SET @nextStep = 'upToVersion' + convert(varchar(3), @currentVersion)
							EXECUTE @nextStep
						END

					WHILE @currentVersion > @targetVersion
						BEGIN
							SET @currentVersion = @currentVersion - 1
							SET @nextStep = 'downToVersion' + convert(varchar(3), @currentVersion)
							EXECUTE @nextStep
						END
			
					TRUNCATE TABLE CurrentDataBaseSchema
					INSERT INTO CurrentDataBaseSchema
					VALUES(@targetVersion)
	
				END
			ELSE
				BEGIN
					PRINT('The target version is the same with the current version')
					RETURN;
				END
		END
	ELSE
		BEGIN
			print('Not a valid data base version')
			RETURN;
		END

GO

SELECT * FROM CurrentDataBaseSchema

EXEC dataBaseVersioning @targetVersion = 0


EXEC downToVersion6
EXEC downToVersion5
EXEC downToVersion4
EXEC downToVersion3
EXEC downToVersion2
EXEC downToVersion1
EXEC downToVersion0

EXEC upToVersion1
EXEC upToVersion2
EXEC upToVersion3
EXEC upToVersion4
EXEC upToVersion5
EXEC upToVersion6
EXEC upToVersion7

