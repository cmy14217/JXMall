CREATE TABLE Inventory(
    id INT NOT NULL PRIMARY KEY,
    count INT DEFAULT 0,
    lockedCount INT DEFAULT 0,
    FOREIGN KEY(id) REFERENCES Product(id)
);