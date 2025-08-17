use Lab3_Week8

create Table Airport
(
	airportID INT primary key identity(1,1),
	airportSize INT,
	airportName VARCHAR(200),
)

create Table Apron
(
	apronId INT primary key identity(1,1),
	airportID INT references Airport(airportID),
	longitude FLOAT,
	latitude FLOAT,

)

create Table Airplane
(
	airplaneID INT primary key identity(1,1),
	aiportID INT references Airport(airportID),
	apronID INT references Apron(apronID),
	airplaneModel VARCHAR(100),
	airplaneLength INT
)

create Table Heliport
(
	heliportID INT primary key identity(1,1),
	airportId INT references Airport(airportID),
	longitude FLOAT,
	latitude FLOAT,
 )


