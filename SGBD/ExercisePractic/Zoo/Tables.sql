use Zoo;

-- Insert data into Cage table
INSERT INTO Cage (series, number) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1);

-- Insert data into Class table
INSERT INTO Class (name) VALUES
('Fish'),
('Amphibians'),
('Reptiles'),
('Birds'),
('Mammals');

-- Insert data into Specie table
INSERT INTO Specie (latinName, romanianName, classID) VALUES
('Salmo salar', 'Somon', 1),
('Bufo bufo', 'Broasca', 2),
('Crocodylus niloticus', 'Crocodil', 3),
('Ara macao', 'Papagal', 4),
('Panthera leo', 'Leu', 5);

-- Insert data into Animals table
INSERT INTO Animals (cageSeries, cageNumber, speciesID, name, description, birthdate) VALUES
(1, 1, 1, 'Nemo', 'A fish with a distinctive orange color', '2020-04-10'),
(1, 2, 2, 'Frodo', 'A small green frog', '2019-07-22'),
(2, 1, 3, 'Snappy', 'A large Nile crocodile', '2018-05-30'),
(2, 2, 4, 'Polly', 'A vibrant red macaw', '2021-01-15'),
(3, 1, 5, 'Simba', 'The king of the jungle', '2017-08-10');

-- Insert data into Caretaker table
INSERT INTO Caretaker (firstName, secondName, address) VALUES
('John', 'Doe', '123 Elm Street'),
('Jane', 'Smith', '456 Oak Avenue'),
('Jim', 'Beam', '789 Pine Road');

-- Insert data into CaretakerCage table
INSERT INTO CaretakerCage (caretakerID, cageSeries, cageNumber) VALUES
(1, 1, 1),
(1, 1, 2),
(2, 2, 1),
(2, 2, 2),
(3, 3, 1);
