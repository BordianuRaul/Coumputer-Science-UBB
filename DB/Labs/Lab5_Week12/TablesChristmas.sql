
CREATE TABLE Elf(
	elfId INT PRIMARY KEY IDENTITY(1,1),
	CNP BIGINT UNIQUE,
	name VARCHAR(30)
)
-- Delete all content from the Elf table
DELETE FROM Elf;

-- Reset the identity seed to 0
DBCC CHECKIDENT ('Elf', RESEED, 0);

CREATE TABLE WishList(
	whishListId INT PRIMARY KEY IDENTITY(1,1),
	cost INT
)

CREATE TABLE SantaShop(
	santaShopId INT PRIMARY KEY IDENTITY(1,1),
	elfId INT REFERENCES Elf(elfId),
	whishListId INT REFERENCES WishList(whishListId)

)

SELECT * FROM Elf
SELECT * FROM WishList
SELECT * FROM SantaShop