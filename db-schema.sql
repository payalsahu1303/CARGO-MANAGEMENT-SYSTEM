CREATE DATABASE cargo_db;
USE cargo_db;

CREATE TABLE cargo_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    width INT,
    depth INT,
    height INT,
    mass DOUBLE,
    priority INT,
    preferred_zone VARCHAR(50),
    usage_limit INT,
    expiry_date DATE
);

CREATE TABLE containers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    container_id VARCHAR(50) UNIQUE NOT NULL,
    zone VARCHAR(50) NOT NULL,
    width INT NOT NULL,
    depth INT NOT NULL,
    height INT NOT NULL
);

CREATE TABLE logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    action_type VARCHAR(50) NOT NULL,
    details TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

