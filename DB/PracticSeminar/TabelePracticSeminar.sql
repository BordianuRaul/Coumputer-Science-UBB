USE seminar5

CREATE TABLE TrainTypes(
	train_type_id INT PRIMARY KEY,
	train_type_name VARCHAR(200),
	train_type_description VARCHAR(MAX)
)

CREATE TABLE Trains(
	train_id INT PRIMARY KEY,
	train_name VARCHAR(200),
	train_type INT FOREIGN KEY REFERENCES TrainTypes(train_type_id)
)
CREATE TABLE Stations(
	station_id INT PRIMARY KEY,
	station_name VARCHAR(200) UNIQUE
)

CREATE TABLE Routes(
	route_id INT PRIMARY KEY,
	route_name VARCHAR(200) UNIQUE,
	train_id INT FOREIGN KEY REFERENCES Trains(train_id)
)

CREATE TABLE RoutesStations(
	station_id INT FOREIGN KEY REFERENCES Stations(station_id),
	route_id INT FOREIGN KEY REFERENCES Routes(route_id),
	PRIMARY KEY(station_id, route_id),
	departation_time TIME,
	arrival_time TIME
)
-- Insert into TrainTypes
INSERT INTO TrainTypes (train_type_id, train_type_name, train_type_description)
VALUES
(1, 'Express', 'High-speed express trains'),
(2, 'Local', 'Local commuter trains'),
(3, 'Cargo', 'Freight trains');

-- Insert into Trains
INSERT INTO Trains (train_id, train_name, train_type)
VALUES
(101, 'Express Train A', 1),
(102, 'Local Train B', 2),
(103, 'Cargo Train C', 3);

-- Insert into Stations
INSERT INTO Stations (station_id, station_name)
VALUES
(501, 'Station X'),
(502, 'Station Y'),
(503, 'Station Z');

-- Insert into Routes
INSERT INTO Routes (route_id, route_name, train_id)
VALUES
(1001, 'Route 1', 101),
(1002, 'Route 2', 102),
(1003, 'Route 3', 103);

-- Insert into RoutesStations
INSERT INTO RoutesStations (station_id, route_id, departation_time, arrival_time)
VALUES
(501, 1001, '08:00:00', '09:30:00'),
(502, 1001, '10:00:00', '12:00:00'),
(503, 1001, '13:00:00', '15:00:00'),

(501, 1002, '07:30:00', '08:45:00'),
(502, 1002, '09:15:00', '10:30:00'),
(503, 1002, '11:00:00', '12:15:00'),

(501, 1003, '14:30:00', '16:00:00'),
(502, 1003, '17:00:00', '19:00:00'),
(503, 1003, '20:00:00', '22:00:00');

