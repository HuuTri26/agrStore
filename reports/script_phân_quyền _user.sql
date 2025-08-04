USE DB_WEBNONGSAN4
GO
--Chạy 2 lệnh này bằng command-line để tạo biến môi trường
--setx DB_PATH "C:\Users\HUU TRI\eclipse-workspace\agrStoreManagement\src\main\webapp\WEB-INF\configs"
--setx RESOURCES_PATH "C:\Users\HUU TRI\eclipse-workspace\agrStoreManagement\src\main\webapp\assets"
DECLARE @DB_PATH NVARCHAR(MAX) = 'C:\Users\HUU TRI\eclipse-workspace\agrStoreManagement\src\main\webapp\WEB-INF\configs';
DECLARE @RESOURCES_PATH NVARCHAR(MAX) = 'C:\Users\HUU TRI\eclipse-workspace\agrStoreManagement\src\main\webapp\assets';
PRINT @DB_PATH;
PRINT @RESOURCES_PATH;
-- Admin agent
CREATE LOGIN ADMIN_AGENT WITH PASSWORD = 'Re$adRa5tOF2Edr51As@';
USE DB_WEBNONGSAN4;
CREATE USER ADMIN_AGENT FOR LOGIN ADMIN_AGENT;

--Phân quyền cho admin có full quyền trong hệ thống
GRANT SELECT, INSERT, UPDATE, DELETE
ON DATABASE::DB_WEBNONGSAN4 TO ADMIN_AGENT;
GO



-- Cấp quyền cho tất cả các tác vụ liên quan đến dữ liệu
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, ALTER, REFERENCES 
ON DATABASE::DB_WEBNONGSAN4 TO ADMIN_AGENT;

-- Cấp quyền tạo, sửa đổi và xóa các đối tượng cơ sở dữ liệu
GRANT CREATE TABLE, CREATE VIEW, CREATE PROCEDURE, CREATE FUNCTION, CREATE SCHEMA 
ON DATABASE::DB_WEBNONGSAN4 TO ADMIN_AGENT;

-- Cấp quyền quản lý user và quyền của họ trong cơ sở dữ liệu
GRANT ALTER ANY USER, CONTROL 
ON DATABASE::DB_WEBNONGSAN4 TO ADMIN_AGENT;

-- Cấp quyền sao lưu và khôi phục dữ liệu
GRANT BACKUP DATABASE, BACKUP LOG 
ON DATABASE::DB_WEBNONGSAN4 TO ADMIN_AGENT;

-- Đảm bảo admin có quyền điều khiển hoàn toàn cơ sở dữ liệu
ALTER ROLE db_owner ADD MEMBER ADMIN_AGENT;



-- Customer agent
CREATE LOGIN CUSTOMER_AGENT WITH PASSWORD = '@UgaSw8PR@crEheCrutr';
USE DB_WEBNONGSAN4;
CREATE USER CUSTOMER_AGENT FOR LOGIN CUSTOMER_AGENT;

--Phân quyền cho khách hàng:

-- Account
GRANT SELECT, UPDATE, INSERT ON dbo.Account TO CUSTOMER_AGENT;
--Address
GRANT SELECT, UPDATE ON dbo.Address TO CUSTOMER_AGENT;
--Cart
GRANT SELECT, INSERT, DELETE, UPDATE ON dbo.Cart TO CUSTOMER_AGENT;
--CartItem
GRANT SELECT, INSERT, DELETE, UPDATE ON dbo.CartItem TO CUSTOMER_AGENT;
--Category
GRANT SELECT ON dbo.Category TO CUSTOMER_AGENT;
--District
GRANT SELECT,  UPDATE ON dbo.District TO CUSTOMER_AGENT;
--FeedBack
GRANT SELECT,UPDATE, INSERT, DELETE  ON dbo.Feedback TO CUSTOMER_AGENT;
--OrderBill
GRANT SELECT  , INSERT  ON dbo.OrderBill TO CUSTOMER_AGENT;
--OrderBillDetail
GRANT SELECT , INSERT , UPDATE , DELETE ON dbo.OrderBillDetail TO CUSTOMER_AGENT;
--Product
GRANT SELECT ON dbo.Product TO CUSTOMER_AGENT;
--Provider
GRANT SELECT  ON dbo.Provider TO CUSTOMER_AGENT;
--Province
GRANT SELECT,  UPDATE ON dbo.Province TO CUSTOMER_AGENT;
--Role
GRANT SELECT ON dbo.Role TO CUSTOMER_AGENT;
--Ward
GRANT SELECT ,  UPDATE ON dbo.Ward TO CUSTOMER_AGENT;


--Employee:

CREATE LOGIN EMPLOYEE_AGENT WITH PASSWORD = 'kubrob$4TRl4REflKaw+';
USE DB_WEBNONGSAN4;
CREATE USER EMPLOYEE_AGENT FOR LOGIN EMPLOYEE_AGENT;


--Phân quyền cho employee:
-- Account
GRANT SELECT, UPDATE ON dbo.Account TO EMPLOYEE_AGENT;
--Address
GRANT SELECT, INSERT, UPDATE ON dbo.Address TO EMPLOYEE_AGENT;
--Cart
--GRANT SELECT, INSERT, DELETE, UPDATE ON dbo.Cart TO EMPLOYEE_AGENT;
--CartItem
--GRANT SELECT, INSERT, DELETE, UPDATE ON dbo.CartItem TO EMPLOYEE_AGENT;
--Category
GRANT SELECT ON dbo.Category TO EMPLOYEE_AGENT;
--District
GRANT SELECT,  UPDATE ON dbo.District TO EMPLOYEE_AGENT;
--FeedBack
GRANT SELECT, DELETE  ON dbo.Feedback TO EMPLOYEE_AGENT;
--OrderBill
GRANT SELECT  , UPDATE , DELETE ON dbo.OrderBill TO EMPLOYEE_AGENT;
--OrderBillDetail
GRANT SELECT , DELETE ON dbo.OrderBillDetail TO EMPLOYEE_AGENT;
--Product
GRANT SELECT , INSERT , UPDATE, DELETE ON dbo.Product TO EMPLOYEE_AGENT;
--Provider
GRANT SELECT  ON dbo.Provider TO EMPLOYEE_AGENT;
--Province
GRANT SELECT,  UPDATE ON dbo.Province TO EMPLOYEE_AGENT;
--Role
GRANT SELECT ON dbo.Role TO EMPLOYEE_AGENT;
--Ward
GRANT SELECT ,  UPDATE ON dbo.Ward TO EMPLOYEE_AGENT;



-- Default agent
CREATE LOGIN DEFAULT_AGENT WITH PASSWORD = 'Sw!th2#r8yitE5ut#l#a';
USE DB_WEBNONGSAN4;
CREATE USER DEFAULT_AGENT FOR LOGIN DEFAULT_AGENT;

-- Cấp quyền chỉ được xem 
ALTER ROLE db_datareader ADD MEMBER DEFAULT_AGENT;
-- Từ chối xem cột gmail TABLE Account của DEFAULT_AGENT
DENY SELECT ON Account(gmail) TO DEFAULT_AGENT;
-- Từ chối xem cột password TABLE Account của DEFAULT_AGENT
DENY SELECT ON Account(password) TO DEFAULT_AGENT;
-- Từ chối xem cột phoneNumber TABLE Account của DEFAULT_AGENT
DENY SELECT ON Account(phoneNumber) TO DEFAULT_AGENT;

GRANT INSERT, SELECT, UPDATE ON dbo.Cart TO DEFAULT_AGENT;
GRANT SELECT,INSERT,UPDATE  ON dbo.Account TO DEFAULT_AGENT;

--Address
GRANT SELECT, INSERT, UPDATE ON dbo.Address TO DEFAULT_AGENT;
--Phân quyền cho người dùng chưa đăng nhập:







-- để kiểm tra quyền
USE DB_WEBNONGSAN4;
EXEC sp_helprotect @username = 'ADMIN_AGENT';
USE DB_WEBNONGSAN4;
EXEC sp_helprotect @username = 'CUSTOMER_AGENT';
USE DB_WEBNONGSAN4;
EXEC sp_helprotect @username = 'DEFAULT_AGENT';
USE DB_WEBNONGSAN4;
EXEC sp_helprotect @username = 'EMPLOYEE_AGENT';


--Thu hồi quyền:
/*USE DB_WEBNONGSAN4;
REVOKE SELECT, INSERT, UPDATE, DELETE, EXECUTE, ALTER, REFERENCES, BACKUP DATABASE, BACKUP LOG
ON DATABASE::DB_WEBNONGSAN4 FROM CUSTOMER_AGENT;
*/
-- Thu hồi rule trong nhóm user:
/*
USE DB_WEBNONGSAN4;
ALTER ROLE db_owner DROP MEMBER CUSTOMER_AGENT;
ALTER ROLE db_datareader DROP MEMBER CUSTOMER_AGENT;
ALTER ROLE db_datawriter DROP MEMBER CUSTOMER_AGENT;
*/

--Xóa User:
/*
USE DB_WEBNONGSAN4;
DROP USER CUSTOMER_AGENT;
*/

--Xóa login
--DROP LOGIN <Login_Name>;

--Kiểm tra các quyền user:
/*
USE DB_WEBNONGSAN4;
SELECT * 
FROM sys.database_permissions 
WHERE grantee_principal_id = USER_ID('<User_Name>');
*/
--








