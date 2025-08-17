use Lab3_Week8

select * from Airport
select * from Apron
-- Airports
INSERT INTO Airport (airportSize, airportName) VALUES
(2, 'Charles de Gaulle Airport'),
(1, 'Frankfurt Airport'),
(3, 'Amsterdam Airport Schiphol'),
(2, 'Madrid-Barajas Adolfo Suárez Airport'),
(2, 'London Heathrow Airport'),
(1, 'Zurich Airport'),
(3, 'Munich Airport'),
(2, 'Leonardo da Vinci–Fiumicino Airport'),
(1, 'Copenhagen Airport'),
(3, 'Barcelona–El Prat Airport');

-- Aprons
INSERT INTO Apron (airportID, longitude, latitude) VALUES
-- Charles de Gaulle Airport
(1, 2.5490, 49.0030),
(1, 2.5505, 49.0046),
(1, 2.5519, 49.0058),
(1, 2.5532, 49.0071),
-- Frankfurt Airport
(2, 8.5721, 50.0488),
(2, 8.5713, 50.0482),
(2, 8.5705, 50.0476),
(2, 8.5698, 50.0471),
-- Amsterdam Airport Schiphol
(3, 4.7597, 52.3074),
(3, 4.7606, 52.3078),
(3, 4.7615, 52.3082),
(3, 4.7623, 52.3087),
-- Madrid-Barajas Adolfo Suárez Airport
(4, -3.5683, 40.4828),
(4, -3.5691, 40.4835),
(4, -3.5699, 40.4841),
(4, -3.5708, 40.4847),
-- London Heathrow Airport
(5, -0.4550, 51.4700),
(5, -0.4542, 51.4695),
(5, -0.4534, 51.4690),
(5, -0.4526, 51.4685),
-- Zurich Airport
(6, 8.5620, 47.4561),
(6, 8.5612, 47.4556),
(6, 8.5604, 47.4551),
(6, 8.5596, 47.4546),
-- Munich Airport
(7, 11.7907, 48.3554),
(7, 11.7899, 48.3549),
(7, 11.7891, 48.3544),
(7, 11.7884, 48.3539),
-- Leonardo da Vinci–Fiumicino Airport
(8, 12.2464, 41.7932),
(8, 12.2458, 41.7927),
(8, 12.2452, 41.7922),
(8, 12.2446, 41.7917),
-- Copenhagen Airport
(9, 12.6461, 55.6268),
(9, 12.6453, 55.6263),
(9, 12.6445, 55.6258),
(9, 12.6437, 55.6253),
-- Barcelona–El Prat Airport
(10, 2.0806, 41.2969),
(10, 2.0799, 41.2964),
(10, 2.0792, 41.2959),
(10, 2.0785, 41.2954);