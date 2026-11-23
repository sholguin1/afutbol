
DROP DATABASE IF EXISTS afutbol;
CREATE DATABASE afutbol CHARACTER SET utf8mb4;
USE afutbol;

CREATE TABLE app_user (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE field (
    field_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    city VARCHAR(100) NOT NULL
);

CREATE TABLE team (
    team_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    city VARCHAR(100) NOT NULL,
    home_field_id INT NOT NULL,
    alternate_field_id INT NULL,
    CHECK (home_field_id <> alternate_field_id),
    FOREIGN KEY (home_field_id) REFERENCES field (field_id),
    FOREIGN KEY (alternate_field_id) REFERENCES field (field_id)
);

CREATE TABLE tournament (
    tournament_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    CHECK (end_date >= start_date)
);

CREATE TABLE registration (
    tournament_id INT NOT NULL,
    team_id INT NOT NULL,
    PRIMARY KEY (tournament_id, team_id),
    FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id),
    FOREIGN KEY (team_id) REFERENCES team (team_id)
);

CREATE TABLE matchday (
    matchday_id INT AUTO_INCREMENT PRIMARY KEY,
    tournament_id INT NOT NULL,
    number INT NOT NULL,
    UNIQUE (tournament_id, number),
    CHECK (number > 0),
    FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id)
);

CREATE TABLE game (
    game_id INT AUTO_INCREMENT PRIMARY KEY,
    matchday_id INT NOT NULL,
    home_team_id INT NOT NULL,
    away_team_id INT NOT NULL,
    field_id INT NOT NULL,
    game_date DATE NOT NULL,
    game_time TIME NOT NULL,
    UNIQUE (field_id, game_date, game_time),
    CHECK (home_team_id <> away_team_id),
    FOREIGN KEY (matchday_id) REFERENCES matchday (matchday_id),
    FOREIGN KEY (home_team_id) REFERENCES team (team_id),
    FOREIGN KEY (away_team_id) REFERENCES team (team_id),
    FOREIGN KEY (field_id) REFERENCES field (field_id)
);

-- Sample data

INSERT INTO field (name, city) VALUES
    ('Cancha La Floresta', 'Medellín'),
    ('Cancha El Dorado', 'Envigado'),
    ('Cancha Niquía', 'Bello'),
    ('Cancha Ditaires', 'Itagüí'),
    ('Polideportivo Sur', 'Envigado'),
    ('Cancha Belén', 'Medellín');

INSERT INTO team (name, city, home_field_id, alternate_field_id) VALUES
    ('Atlético', 'Medellín', 1, 6),
    ('Deportivo', 'Envigado', 2, 5),
    ('Halcones', 'Bello', 3, NULL),
    ('Real Norte', 'Itagüí', 4, NULL);

INSERT INTO tournament (name, start_date, end_date) VALUES
    ('Torneo Primera A de Antioquia', '2026-11-01', '2026-12-15');

INSERT INTO registration (tournament_id, team_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4);

INSERT INTO matchday (tournament_id, number) VALUES
    (1, 1),
    (1, 2);

INSERT INTO game (matchday_id, home_team_id, away_team_id, field_id, game_date, game_time) VALUES
    (1, 1, 2, 1, '2026-11-08', '15:00:00'),
    (1, 3, 4, 3, '2026-11-08', '17:00:00'),
    (2, 2, 3, 2, '2026-11-15', '15:00:00'),
    (2, 4, 1, 4, '2026-11-15', '17:00:00');