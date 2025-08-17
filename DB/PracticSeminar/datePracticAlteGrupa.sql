USE TryStuff2

-- Insert data into Cities
INSERT INTO Cities (cityID, name) VALUES
(1, 'CityA'),
(2, 'CityB'),
(3, 'CityC');

-- Insert data into Stadiums
INSERT INTO Stadium (stadiumID, name, cityID) VALUES
(1, 'StadiumA', 1),
(2, 'StadiumB', 2),
(3, 'StadiumC', 3);

-- Insert data into Country
INSERT INTO Country (countryID, name) VALUES
(1, 'CountryX'),
(2, 'CountryY'),
(3, 'CountryZ');

-- Insert data into NationalTeam
INSERT INTO NationalTeam (nationalTeamID, countryID) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Insert data into Player
INSERT INTO Player (playerID, name, birthDate, nationality, position, captain) VALUES
(1, 'Player1', '1990-01-01', 1, 'Forward', 1),
(2, 'Player2', '1992-03-15', 2, 'Back', 0),
(3, 'Player3', '1988-07-20', 3, 'Scrum-half', 1);

-- Insert data into NationalTeamPlayers
INSERT INTO NationalTeamPlayers (nationalTeamID, playerID) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3);

-- Insert data into Coach
INSERT INTO Coach (coachID, name, nationality) VALUES
(1, 'Coach1', 1),
(2, 'Coach2', 2),
(3, 'Coach3', 3);

-- Insert data into NationalTeamCoaches
INSERT INTO NationalTeamCoaches (nationalTeamID, coachID) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Insert data into Game
INSERT INTO Game (date, team1ID, team2ID, stadiumID, team1Score, team2Score, winner, overtime)
VALUES
('2023-07-01 18:00:00', 1, 2, 1, 20, 15, 1, 0),
('2023-07-02 17:30:00', 2, 3, 2, 25, 18, 2, 1),
('2023-07-03 19:00:00', 3, 1, 3, 30, 28, 3, 0),
('2023-07-04 20:30:00', 1, 2, 1, 15, 10, 1, 0),
('2023-07-05 19:45:00', 2, 3, 2, 22, 20, 2, 1),
('2023-07-06 18:15:00', 3, 1, 3, 28, 25, 3, 1);


-- Retrieve all data from the Cities table
SELECT * FROM Cities;

-- Retrieve all data from the Stadiums table
SELECT * FROM Stadium;

-- Retrieve all data from the Country table
SELECT * FROM Country;

-- Retrieve all data from the NationalTeam table
SELECT * FROM NationalTeam;

-- Retrieve all data from the Player table
SELECT * FROM Player;

-- Retrieve all data from the NationalTeamPlayers junction table
SELECT * FROM NationalTeamPlayers;

-- Retrieve all data from the Coach table
SELECT * FROM Coach;

-- Retrieve all data from the NationalTeamCoaches junction table
SELECT * FROM NationalTeamCoaches;

-- Retrieve all data from the Game table
SELECT * FROM Game;
