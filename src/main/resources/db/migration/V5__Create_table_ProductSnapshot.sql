CREATE TABLE ProductSnapshot(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(255),
    productDescription VARCHAR(255),
    purchasePrice FLOAT,
    purchaseCount INT,
    orderId INT,
    FOREIGN KEY(orderId) REFERENCES Orders(id)
);