INSERT INTO worker (name, birthday, level, salary) VALUES
('Robert Stark', '1987-10-24', 'Middle', 3500),
('John Snow', '1996-01-13', 'Junior', 1800),
('Andy Murrey', '1998-06-30', 'Trainee', 1000),
('Bill Green', '1990-02-15', 'Senior', 6500),
('Bradly Cooper', '1993-12-30', 'Trainee', 1000),
('Mark White', '1989-08-10', 'Senior', 7000),
('Richard Owen', '1979-09-09', 'Middle', 3700),
('John Dawson', '1985-05-01', 'Junior', 2000),
('Jamie Black', '2002-07-07', 'Junior', 1800),
('Samanta Hill', '1997-03-02', 'Middle', 3500),
('Jack Patrick', '1989-09-25', 'Middle', 3400);

INSERT INTO client (name) VALUES
('Jason Mamoa'),
('Roberto Carlos'),
('David York'),
('Ashly Parker'),
('Rob Hummer'),
('John Smith');

INSERT INTO project (client_id, start_date, finish_date) VALUES
(3, '2024-01-02', '2024-12-30'),
(5, '2025-03-15', '2026-09-17'),
(1, '2025-09-10', '2025-12-05'),
(3, '2024-12-16', '2025-12-16'),
(6, '2023-10-10', '2027-11-18'),
(1, '2025-12-01', '2028-12-31'),
(2, '2023-10-19', '2025-10-19'),
(1, '2024-05-15', '2025-09-18'),
(5, '2024-04-19', '2025-12-10'),
(5, '2025-06-06', '2027-06-06');

INSERT INTO project_worker (project_id, worker_id) VALUES
(1, 2), (1, 4), (1, 5),
(2, 4),
(3, 1), (3, 9),
(4, 11), (4, 9), (4, 2), (4, 8), (4, 10),
(5, 3), (5, 8), (5, 5),
(6, 1), (6, 11),
(7, 9), (7, 7), (7, 8), (7, 1),
(8, 6), (8, 11),
(9, 4), (9, 5),
(10, 5), (10, 6), (10, 9), (10, 10);