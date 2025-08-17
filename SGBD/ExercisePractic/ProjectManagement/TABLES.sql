use ProjectManagementSystem

CREATE TABLE Developer(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	name VARCHAR(50),
	age INT,
	experience INT

);

CREATE TABLE Category(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	categoryType VARCHAR(100),
	description VARCHAR(200)
);

CREATE TABLE Project(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	teamLead INT,
	FOREIGN KEY(teamLead) REFERENCES Developer(ID),
	category INT,
	FOREIGN KEY(category) REFERENCES Category(ID)
);

CREATE TABLE ProjectDevs(
	projectID INT,
	FOREIGN KEY(projectID) REFERENCES Project(ID),
	developerID INT,
	FOREIGN KEY(developerID) REFERENCES Developer(ID),
	PRIMARY KEY(projectID, developerID),
	joinDate DATE
);


CREATE TABLE Task(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	description VARCHAR(200),
	dueDate DATE,
	developerID INT,
	FOREIGN KEY(developerID) REFERENCES Developer(ID)
);

CREATE TABLE Feature(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	name VARCHAR(100),
	estimation INT
);

CREATE TABLE TaskFeature(
	taskID INT,
	FOREIGN KEY(taskID) REFERENCES Task(ID),
	featureID INT,
	FOREIGN KEY(featureID) REFERENCES Feature(ID)
);

