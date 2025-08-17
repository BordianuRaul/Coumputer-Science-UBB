use TryStuff2

CREATE TABLE Cities
(
	cityID INT PRIMARY KEY,
	name VARCHAR(50) UNIQUE
)

CREATE TABLE Stadium
(
	stadiumID INT PRIMARY KEY,
	name VARCHAR(50) UNIQUE,
	cityID INT FOREIGN KEY REFERENCES Cities(cityID)
)

CREATE TABLE Country
(
	countryID INT PRIMARY KEY,
	name VARCHAR(50) UNIQUE
)

CREATE TABLE NationalTeam
(
	nationalTeamID INT PRIMARY KEY,
	countryID INT FOREIGN KEY REFERENCES Country(countryID),
	UNIQUE(CountryID)
)

CREATE TABLE Player
(
	playerID INT PRIMARY KEY,
	name VARCHAR(50),
	birthDate DATE,
	nationality INT FOREIGN KEY REFERENCES Country(countryID),
	position VARCHAR(30),
	captain BIT

)
CREATE TABLE NationalTeamPlayers
(
	nationalTeamID INT FOREIGN KEY REFERENCES NationalTeam(nationalTeamID),
	playerID INT FOREIGN KEY REFERENCES Player(playerID),
	PRIMARY KEY(nationalTeamId, playerID)
)

CREATE TABLE Coach
(
	coachID INT PRIMARY KEY,
	name VARCHAR(50),
	nationality INT FOREIGN KEY REFERENCES Country(countryID)
)

CREATE TABLE NationalTeamCoaches
(
	nationalTeamID INT FOREIGN KEY REFERENCES NationalTeam(nationalTeamID),
	coachID INT FOREIGN KEY REFERENCES Coach(coachID),
	PRIMARY KEY(nationalTeamID, coachID)
)

CREATE TABLE Game
(
	gameID INT PRIMARY KEY IDENTITY (1,1),
	date DATETIME,
	team1ID INT FOREIGN KEY REFERENCES NationalTeam(nationalTeamID),
	team2ID INT FOREIGN KEY REFERENCES NationalTeam(nationalTeamID),
	stadiumID INT FOREIGN KEY REFERENCES Stadium(stadiumID),
	team1Score INT,
	team2Score INT,
	winner INT FOREIGN KEY REFERENCES NationalTeam(nationalTeamID),
	overtime BIT

)

DROP TABLE Game

