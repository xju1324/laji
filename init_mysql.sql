-- ========================================

CREATE DATABASE IF NOT EXISTS laji_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE laji_db;

-- 2. 创建管理员表
CREATE TABLE IF NOT EXISTS admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码(明文)',
    email VARCHAR(50) COMMENT '邮箱',
    phone VARCHAR(20) COMMENT '手机号',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- 3. 创建用户表
CREATE TABLE IF NOT EXISTS user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    wechat_openid VARCHAR(100) UNIQUE COMMENT '微信OpenID',
    nickname VARCHAR(100) COMMENT '昵称',
    avatar_url VARCHAR(500) COMMENT '头像URL',
    phone VARCHAR(20) COMMENT '手机号',
    points INT DEFAULT 0 COMMENT '积分',
    total_recognition INT DEFAULT 0 COMMENT '识别总次数',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 4. 创建识别历史表
CREATE TABLE IF NOT EXISTS recognition_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL COMMENT '用户ID',
    image_url VARCHAR(500) NOT NULL COMMENT '图片URL',
    garbage_type VARCHAR(50) COMMENT '垃圾类型',
    category VARCHAR(100) COMMENT '垃圾分类',
    confidence DECIMAL(5,2) COMMENT '置信度',
    recognition_result TEXT COMMENT '识别结果JSON',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_user_id (user_id),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='识别历史表';

-- 5. 插入默认管理员账号 (用户名: admin, 密码: admin123)
-- 注意：密码为明文存储
INSERT INTO admin (username, password, email, phone) VALUES
('admin', 'admin123', 'admin@example.com', '13800138000')
ON DUPLICATE KEY UPDATE username=username;

-- 6. 插入测试用户数据
INSERT INTO user (wechat_openid, nickname, avatar_url, phone, points, total_recognition) VALUES
('wx_test_001', '张三', 'https://via.placeholder.com/100', '13800138001', 100, 15),
('wx_test_002', '李四', 'https://via.placeholder.com/100', '13800138002', 85, 12),
('wx_test_003', '王五', 'https://via.placeholder.com/100', '13800138003', 120, 20),
('wx_test_004', '赵六', 'https://via.placeholder.com/100', '13800138004', 60, 8),
('wx_test_005', '孙七', 'https://via.placeholder.com/100', '13800138005', 95, 14)
ON DUPLICATE KEY UPDATE wechat_openid=wechat_openid;

-- 7. 插入测试识别历史数据
INSERT INTO recognition_history (user_id, image_url, garbage_type, category, confidence, recognition_result) VALUES
(1, 'https://via.placeholder.com/300', '塑料瓶', '可回收垃圾', 0.95, '{"result":"塑料瓶","category":"可回收垃圾"}'),
(1, 'https://via.placeholder.com/300', '电池', '有害垃圾', 0.92, '{"result":"电池","category":"有害垃圾"}'),
(2, 'https://via.placeholder.com/300', '纸箱', '可回收垃圾', 0.98, '{"result":"纸箱","category":"可回收垃圾"}'),
(2, 'https://via.placeholder.com/300', '剩菜', '厨余垃圾', 0.88, '{"result":"剩菜","category":"厨余垃圾"}'),
(3, 'https://via.placeholder.com/300', '易拉罐', '可回收垃圾', 0.96, '{"result":"易拉罐","category":"可回收垃圾"}'),
(3, 'https://via.placeholder.com/300', '废旧灯管', '有害垃圾', 0.91, '{"result":"废旧灯管","category":"有害垃圾"}'),
(4, 'https://via.placeholder.com/300', '玻璃瓶', '可回收垃圾', 0.94, '{"result":"玻璃瓶","category":"可回收垃圾"}'),
(5, 'https://via.placeholder.com/300', '烟蒂', '其他垃圾', 0.87, '{"result":"烟蒂","category":"其他垃圾"}'),
(1, 'https://via.placeholder.com/300', '报纸', '可回收垃圾', 0.97, '{"result":"报纸","category":"可回收垃圾"}'),
(2, 'https://via.placeholder.com/300', '果皮', '厨余垃圾', 0.93, '{"result":"果皮","category":"厨余垃圾"}');

-- 8. 显示表信息
SHOW TABLES;
SELECT '数据库初始化完成！' AS status;

