DROP TABLE IF EXISTS beers;

create table beers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    brewery VARCHAR(100) NOT NULL,
    type VARCHAR(100) NOT NULL
);

INSERT INTO beers (name, brewery, type) VALUES
    ('Special', 'Olvi', 'Lager'),
    ('Sandels', 'Olvi', 'Lager'),
    ('Iisalmi Pale Ale', 'Olvi', 'IPA');
