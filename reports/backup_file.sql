-- Backup

-- Tạo login cho backup
CREATE LOGIN backup_service WITH PASSWORD = 'StrongPassword123!';

-- Tạo user trong database
USE [DB_WEBNONGSAN];
CREATE USER backup_service FOR LOGIN backup_service;

-- Cấp quyền cần thiết
GRANT BACKUP DATABASE TO backup_service;
GRANT BACKUP LOG TO backup_service;
GRANT VIEW SERVER STATE TO backup_service;

USE [DB_WEBNONGSAN]
GO
CREATE PROCEDURE [dbo].[sp_BackupDatabase] 
AS 
BEGIN 
    DECLARE @BackupPath NVARCHAR(500)
    DECLARE @DBName NVARCHAR(100)
    DECLARE @FileName NVARCHAR(100)
    DECLARE @BackupFile NVARCHAR(1000)
    DECLARE @Description NVARCHAR(100)
    
    -- Cấu hình đường dẫn và tên file sao lưu
    SET @BackupPath = 'E:\backup\'  -- Đổi đường dẫn sao lưu theo yêu cầu của bạn
    SET @DBName = DB_NAME()
    SET @FileName = @DBName + '_' + CONVERT(NVARCHAR(20), GETDATE(), 112) + '.bak'
    SET @BackupFile = @BackupPath + @FileName
    SET @Description = 'Full backup of ' + @DBName
    
    -- Thực hiện full backup
    BACKUP DATABASE @DBName 
    TO DISK = @BackupFile
    WITH FORMAT, 
         COMPRESSION, 
         STATS = 10, 
         NAME = 'Full Database Backup',
         DESCRIPTION = @Description
END
GO

GRANT EXECUTE ON dbo.sp_BackupDatabase TO backup_service;

-- Thực hiện gọi tới sp_BackupDatabase
sqlcmd -S .\SQLEXPRESS -d DB_WEBNONGSAN -U backup_service -P StrongPassword123! -Q "EXEC sp_BackupDatabase"




-- Ghi log
CREATE TABLE dbo.AllTables_Log (
    LogID INT IDENTITY(1,1) PRIMARY KEY,
    TableName NVARCHAR(100),  -- Tên bảng mà thay đổi đã xảy ra
    ActionType NVARCHAR(10),  -- Loại hành động: INSERT, UPDATE, DELETE
    RecordID INT,            -- ID của bản ghi bị thay đổi
    OldValues NVARCHAR(MAX), -- Dữ liệu cũ (dành cho UPDATE và DELETE)
    NewValues NVARCHAR(MAX), -- Dữ liệu mới (dành cho INSERT và UPDATE)
    ChangedBy NVARCHAR(100), -- Người thực hiện thay đổi
    ChangeDate DATETIME DEFAULT GETDATE() -- Thời gian thay đổi
);

CREATE TRIGGER trg_Feedback_Log
ON dbo.Feedback
FOR INSERT, UPDATE, DELETE
AS
BEGIN
    -- Log cho INSERT
    IF EXISTS (SELECT * FROM inserted)
    BEGIN
        INSERT INTO dbo.AllTables_Log (TableName, ActionType, RecordID, NewValues, ChangedBy)
        SELECT 'Feedback', 
               'INSERT', 
               i.feedbackId, 
               (SELECT * FROM inserted i FOR JSON PATH), 
               SYSTEM_USER
        FROM inserted i
    END

    -- Log cho UPDATE
    IF EXISTS (SELECT * FROM inserted) AND EXISTS (SELECT * FROM deleted)
    BEGIN
        INSERT INTO dbo.AllTables_Log (TableName, ActionType, RecordID, OldValues, NewValues, ChangedBy)
        SELECT 'Feedback', 
               'UPDATE', 
               i.feedbackId, 
               (SELECT * FROM deleted d FOR JSON PATH),
               (SELECT * FROM inserted i FOR JSON PATH),
               SYSTEM_USER
        FROM inserted i
        INNER JOIN deleted d ON i.feedbackId = d.feedbackId
    END

    -- Log cho DELETE
    IF EXISTS (SELECT * FROM deleted)
    BEGIN
        INSERT INTO dbo.AllTables_Log (TableName, ActionType, RecordID, OldValues, ChangedBy)
        SELECT 'Feedback', 
               'DELETE', 
               d.feedbackId, 
               (SELECT * FROM deleted d FOR JSON PATH),
               SYSTEM_USER
        FROM deleted d
    END
END;


-- Tạo login cho tài khoản xuất file log
CREATE LOGIN log_exporter WITH PASSWORD = 'StrongPassword123!';

-- Tạo user trong database
USE [DB_WEBNONGSAN];
CREATE USER log_exporter FOR LOGIN log_exporter;

-- Cấp quyền đọc cho bảng log
GRANT SELECT ON dbo.AllTables_Log TO log_exporter;

-- Lấy các log của ngày hôm trước
@echo off
sqlcmd -S .\SQLEXPRESS -d DB_WEBNONGSAN -U log_exporter -P "StrongPassword123!" -Q "SELECT * FROM dbo.AllTables_Log WHERE createAt >= CAST(CAST(GETDATE() - 1 AS DATE) AS DATETIME) AND createAt < CAST(CAST(GETDATE() AS DATE) AS DATETIME)" -o "E:\Backup\log\AllTables_Log.csv" -s "," -W

