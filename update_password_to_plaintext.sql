-- ========================================
-- 将现有数据库中的管理员密码更新为明文
-- 如果数据库已经存在，执行此脚本更新密码
-- ========================================

USE laji_db;

-- 更新管理员密码为明文（admin123）
UPDATE admin 
SET password = 'admin123' 
WHERE username = 'admin';

SELECT '管理员密码已更新为明文' AS status;

