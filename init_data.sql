-- create departments
CREATE TABLE departments (
                            id serial PRIMARY KEY,
                            department_name VARCHAR(255),
                            head_of_department VARCHAR(255),
                            employee_count integer
);
-- insert into department
INSERT INTO departments (department_name, head_of_department, employee_count)VALUES ('Finance', 'Ivan Valeryovich', 4);
INSERT INTO departments (department_name, head_of_department, employee_count)VALUES ('Mathematical', 'Mary Olegovna', 3);
INSERT INTO departments (department_name, head_of_department, employee_count)VALUES ('Humanitarian', 'Petrov Oleksandryv', 4);

-- create lectors
CREATE TABLE lectors (
                        id serial PRIMARY KEY,
                        name VARCHAR(255),
                        degree VARCHAR(255),
                        salary double precision,
                        department_id integer,
                        FOREIGN KEY (department_id) REFERENCES departments (id)
);

-- insert into lectors
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Khotybor Solomonovych', 'assistant', 1299.50, 1);
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Kiyan Arturovych', 'professor', 3219.75, 1);
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Pershyk Adamovych', 'associate professor', 2983.20, 1);
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Emmanuel Ruslanovych', 'assistant', 1299.50, 1);

INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Hryva Ostapovich', 'assistant', 993.00, 2);
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Chek Vladyslavovich', 'associate professor', 3500.01, 2);
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Korina Severinivna', 'professor', 3725.50, 2);

INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Olga Sarmativna', 'professor', 3245.60, 3);
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Yaroslav Volodymyrivna', 'professor', 3378.50, 3);
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Ihorovych lived', 'associate professor', 2756.80, 3);
INSERT INTO lectors (name, degree, salary, department_id) VALUES ('Tur Zoryanovych', 'associate professor', 2600.75, 3);


