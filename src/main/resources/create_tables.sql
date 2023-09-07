-- create departments
CREATE TABLE departments (
                            id serial PRIMARY KEY,
                            department_name VARCHAR(255),
                            head_of_department VARCHAR(255),
                            employee_count integer
);
-- create lectors
CREATE TABLE lectors (
                        id serial PRIMARY KEY,
                        name VARCHAR(255),
                        degree VARCHAR(255),
                        salary double precision,
                        department_id integer,
                        FOREIGN KEY (department_id) REFERENCES departments (id)
);