CREATE TABLE IF NOT EXISTS worker (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(1000) NOT NULL,
    birthday DATE,
    level VARCHAR(20) NOT NULL,
    salary INT,

    CONSTRAINT ck_worker_name CHECK (LENGTH(name) BETWEEN 2 AND 1000),
    CONSTRAINT ck_worker_birthday CHECK (YEAR(birthday) > 1900),
    CONSTRAINT ck_worker_level CHECK (level IN ('Trainee', 'Junior', 'Middle', 'Senior')),
    CONSTRAINT ck_worker_salary CHECK (salary BETWEEN 100 AND 100000)
);

CREATE TABLE IF NOT EXISTS client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(1000) NOT NULL,

    CONSTRAINT ck_client_name CHECK (LENGTH(name) BETWEEN 2 AND 1000)
);

CREATE TABLE IF NOT EXISTS project (
    id IDENTITY PRIMARY KEY,
    client_id INT NOT NULL,
    start_date DATE NOT NULL,
    finish_date DATE NOT NULL,

    FOREIGN KEY (client_id) REFERENCES client(id),
    CONSTRAINT ck_project_start_finish CHECK (finish_date > start_date)
);

CREATE TABLE IF NOT EXISTS project_worker (
    project_id INT NOT NULL,
    worker_id INT NOT NULL,

    PRIMARY KEY (project_id, worker_id),
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);