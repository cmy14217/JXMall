CREATE TABLE LogisticsRecord(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    logisticsStatus VARCHAR(30) DEFAULT "readyToShip",
    outboundTime DATE DEFAULT NULL,
    signedTime DATE DEFAULT NULL,
    deliveryTime DATE DEFAULT NULL,
    orderId INT UNIQUE,
    FOREIGN KEY(orderId) REFERENCES Orders(id)
);