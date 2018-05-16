CREATE TABLE Orders(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    totalPrice FLOAT,
    status VARCHAR(30) DEFAULT "unpaid",
    createTime DATE,
    finishTime DATE DEFAULT NULL,
    paidTime DATE DEFAULT NULL,
    withdrawnTime DATE DEFAULT NULL,
    userId INT NOT NULL,
    FOREIGN KEY(userId) REFERENCES Users(id)
);