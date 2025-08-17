use ProjectManagementSystem

-- Developer table
INSERT INTO Developer (name, age, experience) VALUES 
('John Doe', 30, 5),
('Alice Smith', 28, 3),
('Bob Johnson', 35, 8);

-- Category table
INSERT INTO Category (categoryType, description) VALUES 
('Web Development', 'Projects related to web application development'),
('Mobile Development', 'Projects related to mobile application development'),
('Data Science', 'Projects related to data analysis and machine learning');

-- Project table
INSERT INTO Project (teamLead, category) VALUES 
(1, 1),
(2, 2),
(3, 3);

-- ProjectDevs table
INSERT INTO ProjectDevs (projectID, developerID, joinDate) VALUES 
(1, 1, '2024-01-01'),
(1, 2, '2024-02-15'),
(2, 2, '2024-03-01'),
(2, 3, '2024-02-01'),
(3, 1, '2024-01-10'),
(3, 3, '2024-03-10');

-- Task table
INSERT INTO Task (description, dueDate) VALUES 
('Design homepage', '2024-02-15'),
('Implement login functionality', '2024-03-01'),
('Analyze dataset', '2024-02-28');

-- Feature table
INSERT INTO Feature (name, estimation) VALUES 
('User Authentication', 10),
('Data Visualization', 8),
('API Integration', 12);

-- TaskFeature table
INSERT INTO TaskFeature (taskID, featureID) VALUES 
(1, 1),
(2, 1),
(3, 2),
(3, 3);

-- DeveloperTask table
INSERT INTO DeveloperTask (developerID, taskID) VALUES 
(1, 1),
(1, 2),
(2, 2),
(3, 3);
