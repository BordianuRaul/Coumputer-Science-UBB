-- Create the procedure to insert 10k random rows into Elf table

SELECT * FROM Elf

EXEC InsertIntoElf

ALTER PROCEDURE InsertIntoElf
AS
BEGIN
	DECLARE @Counter INT = 1;
    WHILE @Counter <= 10000
    BEGIN
        DECLARE @randomCNP BIGINT
		SET @randomCNP = CONVERT(BIGINT, CEILING(RAND() * 100000));
        DECLARE @randomName VARCHAR(30);
		SET @randomName = LEFT(CONVERT(VARCHAR(36), NEWID()), 30);

        INSERT INTO Elf (CNP, name)
        VALUES (@RandomCNP, @RandomName);

        SET @Counter = @Counter + 1;
    END
END;

-- Create the procedure to insert 10k random rows into WishList table
CREATE OR ALTER PROCEDURE InsertIntoWishList
AS
BEGIN
    DECLARE @Counter INT = 1;
    WHILE @Counter <= 10000
    BEGIN
        INSERT INTO WishList (cost)
        VALUES (CONVERT(INT, CEILING(RAND() * 100)));

        SET @Counter = @Counter + 1;
    END
END;

EXEC InsertIntoWishList
SELECT * FROM WishList


CREATE OR ALTER PROCEDURE InsertIntoSantaShop
AS
BEGIN
	DECLARE @Counter INT = 1;
    WHILE @Counter <= 10000
    BEGIN
        INSERT INTO SantaShop (elfId, wHishListId)
        VALUES (@counter, 10000 - @counter)

        SET @Counter = @Counter + 1;
    END
END;

EXEC InsertIntoSantaShop
SELECT * FROM SantaShop