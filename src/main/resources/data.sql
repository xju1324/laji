-- 初始化管理员账号（密码：admin123）
-- BCrypt加密后的密码
INSERT INTO admin (username, password, email, phone, created_at, updated_at) VALUES
('admin', '$2a$10$YxVF.yCvQ8gJvC9NkYJF4.3qQVB7KqVmL3TkTKqxJ5o5G3G5MNiJC', 'admin@example.com', '13800138000', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 插入测试用户数据
INSERT INTO user (wechat_openid, nickname, avatar_url, phone, points, total_recognition, created_at, updated_at) VALUES
('wx_test_001', '张三', 'https://via.placeholder.com/100', '13800138001', 100, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('wx_test_002', '李四', 'https://via.placeholder.com/100', '13800138002', 85, 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('wx_test_003', '王五', 'https://via.placeholder.com/100', '13800138003', 120, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('wx_test_004', '赵六', 'https://via.placeholder.com/100', '13800138004', 60, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('wx_test_005', '孙七', 'https://via.placeholder.com/100', '13800138005', 95, 14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 插入测试识别历史数据
INSERT INTO recognition_history (user_id, image_url, garbage_type, category, confidence, recognition_result, created_at) VALUES
(1, 'https://via.placeholder.com/300', '塑料瓶', '可回收垃圾', 0.95, '{"result":"塑料瓶","category":"可回收垃圾"}', CURRENT_TIMESTAMP),
(1, 'https://via.placeholder.com/300', '电池', '有害垃圾', 0.92, '{"result":"电池","category":"有害垃圾"}', CURRENT_TIMESTAMP),
(2, 'https://via.placeholder.com/300', '纸箱', '可回收垃圾', 0.98, '{"result":"纸箱","category":"可回收垃圾"}', CURRENT_TIMESTAMP),
(2, 'https://via.placeholder.com/300', '剩菜', '厨余垃圾', 0.88, '{"result":"剩菜","category":"厨余垃圾"}', CURRENT_TIMESTAMP),
(3, 'https://via.placeholder.com/300', '易拉罐', '可回收垃圾', 0.96, '{"result":"易拉罐","category":"可回收垃圾"}', CURRENT_TIMESTAMP),
(3, 'https://via.placeholder.com/300', '废旧灯管', '有害垃圾', 0.91, '{"result":"废旧灯管","category":"有害垃圾"}', CURRENT_TIMESTAMP),
(4, 'https://via.placeholder.com/300', '玻璃瓶', '可回收垃圾', 0.94, '{"result":"玻璃瓶","category":"可回收垃圾"}', CURRENT_TIMESTAMP),
(5, 'https://via.placeholder.com/300', '烟蒂', '其他垃圾', 0.87, '{"result":"烟蒂","category":"其他垃圾"}', CURRENT_TIMESTAMP),
(1, 'https://via.placeholder.com/300', '报纸', '可回收垃圾', 0.97, '{"result":"报纸","category":"可回收垃圾"}', CURRENT_TIMESTAMP),
(2, 'https://via.placeholder.com/300', '果皮', '厨余垃圾', 0.93, '{"result":"果皮","category":"厨余垃圾"}', CURRENT_TIMESTAMP);

