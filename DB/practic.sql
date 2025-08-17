use Practic

CREATE TABLE Client
(
	clientID INT PRIMARY KEY,
	name VARCHAR(100),
	idenNr INT UNIQUE
)

CREATE TABLE Bank
(
	bankID INT PRIMARY KEY
)

CREATE TABLE BankClient
(
	bankID INT FOREIGN KEY REFERENCES Bank(bankID),
	idenNR INT FOREIGN KEY REFERENCES Client(idenNR),
	PRIMARY KEY(bankID, idenNR),
	clientName VARCHAR(100),

)

CREATE TABLE BankingService
(
	bankID INT FOREIGN KEY REFERENCES Bank(bankID),
	idenNr INT FOREIGN KEY REFERENCES Client(idenNR),
	PRIMARY KEY(bankID, idenNR)

)

CREATE TABLE InvestingService
(
	investingServiceID INT PRIMARY KEY,
	bankID INT FOREIGN KEY REFERENCES Bank(bankID),
	clientID INT FOREIGN KEY REFERENCES Client(clientID),
	clientName VARCHAR(100)

)

