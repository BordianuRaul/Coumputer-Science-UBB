USE Library;

-- Insert data into Library table
INSERT INTO Library (name, location) VALUES ('Central Library', 'Downtown');
INSERT INTO Library (name, location) VALUES ('Westside Branch', 'West End');
INSERT INTO Library (name, location) VALUES ('Eastside Branch', 'East End');

-- Insert data into Publisher table
INSERT INTO Publisher (name) VALUES ('Penguin Books');
INSERT INTO Publisher (name) VALUES ('HarperCollins');
INSERT INTO Publisher (name) VALUES ('Random House');

-- Insert data into Author table
INSERT INTO Author (name) VALUES ('J.K. Rowling');
INSERT INTO Author (name) VALUES ('George R.R. Martin');
INSERT INTO Author (name) VALUES ('J.R.R. Tolkien');
INSERT INTO Author (name) VALUES ('Agatha Christie');

-- Insert data into Book table
INSERT INTO Book (libraryID, title, publisherID) VALUES (1, 'Harry Potter and the Philosopher''s Stone', 1);
INSERT INTO Book (libraryID, title, publisherID) VALUES (2, 'A Game of Thrones', 2);
INSERT INTO Book (libraryID, title, publisherID) VALUES (3, 'The Hobbit', 3);
INSERT INTO Book (libraryID, title, publisherID) VALUES (1, 'Murder on the Orient Express', 1);

-- Insert data into BookAuthor table
INSERT INTO BookAuthor (bookID, authorID) VALUES (1, 1);  -- 'Harry Potter and the Philosopher''s Stone' by J.K. Rowling
INSERT INTO BookAuthor (bookID, authorID) VALUES (2, 2);  -- 'A Game of Thrones' by George R.R. Martin
INSERT INTO BookAuthor (bookID, authorID) VALUES (3, 3);  -- 'The Hobbit' by J.R.R. Tolkien
INSERT INTO BookAuthor (bookID, authorID) VALUES (4, 4);  -- 'Murder on the Orient Express' by Agatha Christie

-- Insert data into Affiliation table
INSERT INTO Affiliation (name) VALUES ('University Library');
INSERT INTO Affiliation (name) VALUES ('Public Library');
INSERT INTO Affiliation (name) VALUES ('Private Collection');

-- Insert data into Reader table
INSERT INTO Reader (name, preferance, affiliationID) VALUES ('Alice', 'Fantasy', 1);
INSERT INTO Reader (name, preferance, affiliationID) VALUES ('Bob', 'Mystery', 2);
INSERT INTO Reader (name, preferance, affiliationID) VALUES ('Charlie', 'Fantasy', 3);

-- Insert data into ReaderBook table
INSERT INTO ReaderBook (readerID, bookID) VALUES (1, 1);  -- Alice reads 'Harry Potter and the Philosopher''s Stone'
INSERT INTO ReaderBook (readerID, bookID) VALUES (2, 4);  -- Bob reads 'Murder on the Orient Express'
INSERT INTO ReaderBook (readerID, bookID) VALUES (3, 3);  -- Charlie reads 'The Hobbit'
INSERT INTO ReaderBook (readerID, bookID) VALUES (1, 3);  -- Alice also reads 'The Hobbit'


USE Library;

-- Select data from Library table
SELECT * FROM Library;

-- Select data from Publisher table
SELECT * FROM Publisher;

-- Select data from Author table
SELECT * FROM Author;

-- Select data from Book table
SELECT * FROM Book;

-- Select data from BookAuthor table
SELECT * FROM BookAuthor;

-- Select data from Affiliation table
SELECT * FROM Affiliation;

-- Select data from Reader table
SELECT * FROM Reader;

-- Select data from ReaderBook table
SELECT * FROM ReaderBook;
