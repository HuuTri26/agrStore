USE master
GO

-- chạy câu lệnh này trc
CREATE DATABASE DB_WEBNONGSAN4;
GO

-- chạy câu lệnh này
USE DB_WEBNONGSAN4
GO

-- Table: Province
CREATE TABLE Province (
  provinceId INT IDENTITY PRIMARY KEY,
  provinceName NVARCHAR(MAX) NOT NULL,
);

INSERT INTO Province (provinceName) VALUES (N'Hà Nội'),(N'TP. Hồ Chí Minh'),(N'Đà Nẵng');

-- Table: District
CREATE TABLE District (
  districtId INT IDENTITY PRIMARY KEY,
  districtName NVARCHAR(MAX) NOT NULL,


  provinceId INT NOT NULL,
  FOREIGN KEY (provinceId) REFERENCES Province(provinceId)
);

INSERT INTO District (districtName, provinceId) VALUES (N'Hoàn Kiếm', 1),(N'Ba Đình', 1),(N'Quận 1', 2),(N'Quận 3', 2),(N'Hải Châu', 3),(N'Thanh Khê', 3);

-- Table: Ward
CREATE TABLE Ward (
  wardId INT IDENTITY PRIMARY KEY,
  wardName NVARCHAR(MAX) NOT NULL,


  districtId INT NOT NULL,
  FOREIGN KEY (districtId) REFERENCES District(districtId)
);

INSERT INTO Ward (wardName, districtId) VALUES 
(N'Hàng Trống', 1),
(N'Phan Chu Trinh', 1),
(N'Bến Thành', 3),
(N'Phường 6', 4),
(N'Phước Ninh', 5),
(N'An Khê', 6);



-- Table: Role
CREATE TABLE Role (
  roleId INT IDENTITY PRIMARY KEY,
  roleName NVARCHAR(MAX) NOT NULL
);

INSERT INTO Role (roleName) VALUES 
(N'Admin'),
(N'Employee'),
(N'Customer');

-- Table: Address
CREATE TABLE Address (
  addressId INT IDENTITY PRIMARY KEY,
  streetName NVARCHAR(MAX) ,


 -- accountId INT ,
  wardId INT NOT NULL,
  --FOREIGN KEY (accountId) REFERENCES Account(accountId),
  FOREIGN KEY (wardId) REFERENCES Ward(wardId),

);

INSERT INTO Address (streetName, wardId) VALUES 
(N'123 Hàng Trống', 1),
(N'45 Phan Chu Trinh',  2),
(N'789 Bến Thành',  3),
(N'10 Phường 6',  4),
(N'22 Phước Ninh',  5);

-- Table: Account
CREATE TABLE Account (
  accountId INT IDENTITY PRIMARY KEY,
  status BIT NOT NULL DEFAULT 0, --0: Disable / 1: Enable
  avatar NVARCHAR(MAX) NULL,
  gmail NVARCHAR(MAX) NOT NULL,
  fullName NVARCHAR(MAX) NOT NULL,
  phoneNumber NVARCHAR(MAX) NULL,
  password NVARCHAR(MAX) NOT NULL DEFAULT N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', -- Pass 123
  createAt DATETIME NOT NULL DEFAULT GETDATE(),
  updateAt DATETIME NULL DEFAULT GETDATE(),



  roleId INT NOT NULL,
  addressId INT NULL, -- tạm thời để null
  FOREIGN KEY (roleId) REFERENCES Role(roleId),
  FOREIGN KEY (addressId) REFERENCES Address(addressId)
);

INSERT INTO Account (status, avatar, gmail, fullName, phoneNumber, password, createAt, updateAt, roleId) VALUES 
(1, NULL, N'tribui2017@gmail.com', N'Admin User', N'0123456789', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', GETDATE(), GETDATE(), 1),
(1, NULL, N'dinhquoctoan200103@gmail.com', N'Employee User 1', N'0987654321', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', GETDATE(), GETDATE(), 2),
(1, NULL, N'employee2@example.com', N'Employee User 2', N'0909123456', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', GETDATE(), GETDATE(), 2),
(1, NULL, N'hannguyenlvm@gmail.com', N'Customer 1', N'0912345678', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', GETDATE(), GETDATE(), 3),
(1, NULL, N'customer2@example.com', N'Customer 2', N'0912987654', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', GETDATE(), GETDATE(), 3);








-- Table: Provider
CREATE TABLE Provider (
  providerId INT IDENTITY PRIMARY KEY,
  providerName NVARCHAR(MAX) NOT NULL,
  phoneNumber NVARCHAR(MAX) NULL,
  status BIT NOT NULL DEFAULT 1,
  image NVARCHAR(MAX) NULL
);

INSERT INTO Provider (providerName, phoneNumber, status) VALUES 
(N'Tiểu thương A', N'0123456789', 1),
(N'Tiểu thương B', N'0987654321', 1),
(N'Tiểu thương C', N'0909123456', 1),
(N'Tiểu thương D', N'0912345678', 1);

-- Table: Category
CREATE TABLE Category (
  categoryId INT IDENTITY PRIMARY KEY,
  categoryName NVARCHAR(MAX) NOT NULL,
  status BIT NOT NULL DEFAULT 1,
  image NVARCHAR(MAX) NULL,
  descript NVARCHAR(MAX) NULL,
);

INSERT INTO Category (categoryName, status, image, descript) VALUES 
(N'Rau củ', 1, N'image1.jpg', N'Các loại rau củ tươi'),
(N'Thịt', 1, N'image2.jpg', N'Các loại thịt tươi'),
(N'Trái cây', 1, N'image3.jpg', N'Các loại trái cây tươi'),
(N'Hải sản', 1, N'image4.jpg', N'Các loại hải sản tươi sống');

-- Table: Product
CREATE TABLE Product (
  productId INT IDENTITY PRIMARY KEY,
 -- providerId INT NOT NULL,
  productName NVARCHAR(MAX) NOT NULL,
  price MONEY NULL DEFAULT 0,
  quantity INT NOT NULL DEFAULT 1,
  descript NVARCHAR(MAX) NULL,
  image NVARCHAR(MAX) NULL,
  unit NVARCHAR(MAX) NOT NULL,
  status BIT NOT NULL DEFAULT 1,
  createAt DATETIME NOT NULL DEFAULT GETDATE(),
  updateAt DATETIME NULL DEFAULT GETDATE(),

 
  categoryId INT NOT NULL,
   providerId INT NOT NULL,

  FOREIGN KEY (categoryId) REFERENCES Category(categoryId),
    FOREIGN KEY (providerId) REFERENCES Provider(providerId),
);

INSERT INTO Product (productName, price, quantity, descript, image, unit, status, categoryId,providerId) VALUES 
(N'Cà rốt', 20000, 100, N'Cà rốt tươi', N'carrot.jpg', N'kg', 1, 1,1),
(N'Thịt gà', 80000, 50, N'Thịt gà tươi', N'chicken.jpg', N'kg', 1, 2,1),
(N'Táo', 30000, 80, N'Táo tươi', N'apple.jpg', N'kg', 1, 3,1),
(N'Mực tươi', 150000, 30, N'Mực tươi sống', N'squid.jpg', N'kg', 1, 4,2),
(N'Khoai tây', 15000, 60, N'Khoai tây Đà Lạt', N'potato.jpg', N'kg', 1, 1,2),
(N'Bò mỹ', 250000, 20, N'Thịt bò Mỹ nhập khẩu', N'beef.jpg', N'kg', 1, 2,2);

-- Table: Cart
CREATE TABLE Cart (
  cartId INT IDENTITY PRIMARY KEY,
  --customerId INT NOT NULL,
  totalQuantity INT NULL,


  accountId INT NOT NULL,
  FOREIGN KEY (accountId) REFERENCES Account(accountId),
);


INSERT INTO Cart (totalQuantity, accountId) VALUES 
(5, 4), -- Customer 1
(3, 5); -- Customer 2

-- Table CartItem
CREATE TABLE CartItem (
  cartItemId INT IDENTITY PRIMARY KEY,
  quantity INT NULL,


  cartId int NOT NULL,
  productId INT NOT NULL,
  FOREIGN KEY (cartId) REFERENCES Cart(cartId),
  FOREIGN KEY (productId) REFERENCES Product(productId)
);

INSERT INTO CartItem (quantity, cartId, productId) VALUES 
(2, 1, 1), -- 2 Cà rốt cho giỏ hàng của Customer 1
(1, 1, 2), -- 1 Thịt gà cho giỏ hàng của Customer 1
(1, 2, 3), -- 1 Táo cho giỏ hàng của Customer 2
(2, 2, 5); -- 2 Khoai tây cho giỏ hàng của Customer 2


-- Table: OrderBill
CREATE TABLE OrderBill (
  orderBillId INT IDENTITY PRIMARY KEY,
  statusOrder INT NOT NULL DEFAULT 1, -- 1: Pending, 2: Confirmed, 3: Not Shipped, 4: Shipped, 5: Completed, 6: Canceled
  orderTime DATETIME NOT NULL DEFAULT GETDATE(),
  totalQuantity INT NULL DEFAULT 0,
  totalPrice MONEY NULL DEFAULT 0,


  accountId INT NOT NULL,
  FOREIGN KEY (accountId) REFERENCES Account(accountId),
 
);

INSERT INTO OrderBill (statusOrder, orderTime, totalQuantity, totalPrice, accountId) VALUES 
(1, GETDATE(), 3, 140000, 4), -- Đơn hàng của Customer 1
(1, GETDATE(), 2, 45000, 5); -- Đơn hàng của Customer 2

-- Table: OrderBillDetail
CREATE TABLE OrderBillDetail (
  orderBillDetailId INT IDENTITY PRIMARY KEY,
  quantity INT NULL DEFAULT 0,
  price INT NULL DEFAULT 0,
  

  orderBillId INT NOT NULL,
  productId INT NOT NULL,
  FOREIGN KEY (orderBillId) REFERENCES OrderBill(orderBillId),
  FOREIGN KEY (productId) REFERENCES Product(productId)
);

INSERT INTO OrderBillDetail (quantity, price, orderBillId, productId) VALUES 
(2, 20000, 1, 1), -- 2 Cà rốt trong đơn hàng của Customer 1
(1, 80000, 1, 2), -- 1 Thịt gà trong đơn hàng của Customer 1
(1, 30000, 2, 3), -- 1 Táo trong đơn hàng của Customer 2
(1, 15000, 2, 5); -- 2 Khoai tây trong đơn hàng của Customer 2

-- Table: importBill
CREATE TABLE ImportBill (
  importBillId INT IDENTITY PRIMARY KEY,
  totalPrice MONEY NULL DEFAULT 0,
  totalQuantity INT NULL DEFAULT 0,
  createAt DATETIME NOT NULL DEFAULT GETDATE(),


  accountId INT NOT NULL,
  --providerId INT NOT NULL,
  FOREIGN KEY (accountId) REFERENCES Account(accountId),
  --FOREIGN KEY (providerId) REFERENCES Provider(providerId),
);

INSERT INTO ImportBill (totalPrice, totalQuantity, createAt, accountId) VALUES 
(2500000, 100, GETDATE(), 1), -- Hóa đơn nhập do Admin thực hiện
(1500000, 80, GETDATE(), 1); -- Hóa đơn nhập do Admin thực hiện

-- Table: importBillDetail
CREATE TABLE ImportBillDetail (
  importBillDetailId INT IDENTITY PRIMARY KEY,
  quantity INT NULL DEFAULT 0,
  price MONEY NULL DEFAULT 0,


  importBillId INT NOT NULL,
  productId INT NOT NULL,
  FOREIGN KEY (importBillId) REFERENCES importBill(importBillId),
  FOREIGN KEY (productId) REFERENCES Product(productId)
);

INSERT INTO ImportBillDetail (quantity, price, importBillId, productId) VALUES 
(50, 20000, 1, 1), -- Nhập 50 kg Cà rốt
(50, 80000, 1, 2), -- Nhập 50 kg Thịt gà
(30, 150000, 2, 4), -- Nhập 30 kg Mực tươi
(50, 15000, 2, 5); -- Nhập 50 kg Khoai tây
DROP TABLE Feedback
GO
-- Table: Feedback
CREATE TABLE Feedback (
  feedbackId INT IDENTITY PRIMARY KEY,
  comment NVARCHAR(MAX) NULL,
  star INT NULL,
  createAt DATETIME NOT NULL DEFAULT GETDATE(),



   accountId INT NOT NULL,
   orderBillDetailId INT NOT NULL,
   FOREIGN KEY (accountId) REFERENCES Account(accountId),
  FOREIGN KEY (orderBillDetailId) REFERENCES OrderBillDetail(orderBillDetailId)
);

INSERT INTO Feedback (comment, star, createAt, accountId,orderBillDetailId) VALUES 
(N'Sản phẩm tươi ngon', 5, GETDATE(), 4, 1),
(N'Thịt gà rất ngon', 4, GETDATE(), 4, 1), -- Đánh giá của Customer 1 cho Thịt gà
(N'Táo khá ngọt', 4, GETDATE(), 5, 2), -- Đánh giá của Customer 2 cho Táo
(N'Khoai tây không tươi lắm', 3, GETDATE(), 5, 3); -- Đánh giá của Customer 2 cho Khoai tây

-- Table: Coupon
/*
CREATE TABLE Coupon (
  couponId INT PRIMARY KEY,
  couponName NVARCHAR(MAX),
  applyTime DATETIME,
  discount FLOAT,
  maxValue FLOAT,
  status BIT
);
*/

/*INSERT INTO dbo.Role(roleName) VALUES('Admin')
INSERT INTO dbo.Role(roleName) VALUES('Staff')
INSERT INTO dbo.Role(roleName) VALUES('Customer')
SELECT * FROM Role

INSERT INTO dbo.Province(provinceName) VALUES('TP.HCM')
INSERT INTO dbo.District(districtName, provinceId) VALUES('Quận 9', 1)

UPDATE District SET districtName = N'Quan 9' WHERE districtId = 1

INSERT INTO dbo.Ward(wardName, districtId) VALUES('Hiep Phu', 1)
INSERT INTO dbo.Ward(wardName, districtId) VALUES('Tang Nhon Phu', 1)
INSERT INTO dbo.Address(provinceId, districtId, wardId) VALUES(1, 1, 1)

INSERT dbo.Account(roleId, gmail, fullName, addressId) VALUES (1, N'dinhquoctoan200103@gmail.com', N'Dinh Quoc Toan', 1)

SELECT * FROM PROVINCE
SELECT * FROM District
SELECT * FROM Ward
SELECT * FROM Address
*/


-- Thêm cột employeeId vào bảng OrderBill
--ALTER TABLE OrderBill
--ADD employeeId INT NULL; -- Cho phép null
/*
-- Tạo khóa ngoại liên kết employeeId với accountId trong bảng Account
ALTER TABLE OrderBill
ADD CONSTRAINT FK_OrderBill_Employee
FOREIGN KEY (employeeId) REFERENCES Account(accountId);
*/


































