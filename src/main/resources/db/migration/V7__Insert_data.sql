INSERT INTO Product(name,description,price) VALUES("方便面","康师傅",2.5),("纯牛奶","卫岗",2.3),("饼干","苏打饼干",3.6);

INSERT INTO Inventory(id) VALUES(1),(2),(3);

INSERT INTO Users(userName) VALUES("小明"),("小红"),("小王");

INSERT INTO Orders(totalPrice,createTime,userId) VALUES(26.3,"2018-05-16",1),(19,"2018-05-16",2);

INSERT INTO ProductSnapshot(productName,productDescription,purchasePrice,purchaseCount,orderId) VALUES
           ("方便面","康师傅",2.5,5,1),("纯牛奶","蒙牛",2.3,6,1),("方便面","康师傅",2.5,3,2),("纯牛奶","蒙牛",2.3,5,2);

INSERT INTO LogisticsRecord(orderId) VALUES(1),(2);