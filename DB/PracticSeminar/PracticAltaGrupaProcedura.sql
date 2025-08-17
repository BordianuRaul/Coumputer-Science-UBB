USE TryStuff2;
GO

CREATE OR ALTER PROCEDURE addGame
    @date DATETIME,
    @team1 INT,
    @team2 INT,
    @stadium INT,
    @team1Score INT,
    @team2Score INT,
    @winner INT,
    @overtime BIT
AS
BEGIN
    IF EXISTS (SELECT * FROM Game G WHERE G.team1ID = @team1 AND G.team2ID = @team2 AND G.date = @date)
    BEGIN
        -- Update the existing game
        UPDATE Game
        SET team1Score = @team1Score,
            team2Score = @team2Score
        WHERE team1ID = @team1 AND team2ID = @team2 AND date = @date;
    END
    ELSE
    BEGIN
        -- Insert a new game
        INSERT INTO Game VALUES (@date, @team1, @team2, @stadium, @team1Score, @team2Score, @winner, @overtime);
    END
END;


-- Inserting data for testing the addGame procedure
INSERT INTO Game (date, team1ID, team2ID, stadiumID, team1Score, team2Score, winner, overtime)
VALUES
('2024-01-10 18:00:00', 1, 2, 1, 0, 0, NULL, 0), -- Game not played yet
('2024-01-11 19:30:00', 3, 1, 2, 15, 10, 3, 0),   -- Game with winner
('2024-01-12 20:15:00', 2, 3, 3, NULL, NULL, NULL, NULL); -- Game without scores

-- Display the existing data in the Game table
SELECT * FROM Game;

-- Call the addGame procedure with test data
EXEC addGame '2024-01-10 18:00:00', 1, 2, 1, 10, 8, NULL, 1; -- Update existing game

-- Display the updated data in the Game table
SELECT * FROM Game;

