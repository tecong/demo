DROP TABLE IF EXISTS cars;

create table cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(100) NOT NULL,
    make VARCHAR(100) NOT NULL,
    year INT NOT NULL
);

INSERT INTO cars (model, make, year) VALUES
    ('A3', 'Audi', 2003),
    ('X3', 'BMW', 2014),
    ('2000 mk2', 'Triumph', 1970);
