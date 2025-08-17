
--A.

-- Clustered Index Scan
SELECT * FROM Elf

-- Clustered Index Seek
SELECT * FROM Elf WHERE elfId = 1

-- Nonclustered Index Scan
CREATE NONCLUSTERED INDEX UNCLUSTER_INDEX_NAME ON Elf(name);
SELECT name FROM Elf 

-- Nonclustered Index Seek
CREATE NONCLUSTERED INDEX IX_Name ON Elf(name);
SELECT * FROM Elf
WHERE name LIKE 'AAE%' 

-- Key Lookup
SELECT * FROM Elf WHERE elfId = 1





--B

-- Create a nonclustered index on the 'cost' column of the WishList table
CREATE NONCLUSTERED INDEX NONCLUSTERED_INDEX_COST ON WishList (cost);
DROP INDEX NONCLUSTERED_INDEX_COST ON WishList;

SELECT * FROM WishList WHERE cost = 100;

SELECT * FROM WishList WHERE cost = 100;



--C

-- Create a view that joins Elf, WishList, and SantaShop tables

CREATE OR ALTER VIEW ElfWishListView AS
SELECT
    e.elfId,
    e.CNP,
    e.name,
    wl.whishListId,
    wl.cost,
    ss.santaShopId
FROM
    Elf e WITH (INDEX(UNCLUSTER_INDEX_NAME)) -- Use the nonclustered index
JOIN
    SantaShop ss ON e.elfId = ss.elfId
JOIN
    WishList wl ON ss.whishListId = wl.whishListId
WHERE
    e.name LIKE 'AAE%'; -- Filter for names starting with 'AAE%'



SELECT * FROM ElfWishListView;