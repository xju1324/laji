# 垃圾识别管理系统

基于Spring Boot的垃圾识别后台管理系统，集成百度EasyDL图像识别API。

## 项目简介

这是一个垃圾识别管理系统的后台管理平台，主要功能包括：

- 管理员登录认证（JWT）
- 用户管理（增删改查）
- 图片识别历史查看
- 与百度EasyDL集成进行垃圾识别

用户通过微信小程序上传图片进行垃圾识别，管理员可以在后台查看和管理用户及识别历史。

## 技术栈

### 后端
- Spring Boot 3.5.7
- Spring Security + JWT
- Spring Data JPA
- MySQL / H2 Database
- Lombok
- OkHttp（调用百度API）

### 前端
- HTML5 + CSS3 + JavaScript
- Thymeleaf模板引擎

## 功能特性

1. **管理员登录**
   - JWT Token认证
   - 密码加密存储（BCrypt）
   - 会话管理

2. **用户管理**
   - 查看所有用户
   - 分页显示
   - 查看用户识别历史
   - 删除用户

3. **识别历史管理**
   - 查看所有识别记录
   - 按用户筛选
   - 按垃圾分类筛选
   - 分页显示
   - 图片预览
   - 删除记录

4. **数据统计**
   - 用户总数
   - 识别总次数
   - 仪表板概览

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 5.7+ (可选，开发环境使用H2)

### 配置说明

1. 克隆项目
```bash
git clone <repository-url>
cd laji
```

2. 配置数据库（可选）

如果使用MySQL，修改`src/main/resources/application.properties`：

```properties
# 注释掉H2配置，取消注释MySQL配置
spring.datasource.url=jdbc:mysql://localhost:3306/laji_db?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

3. 配置百度EasyDL API

修改`src/main/resources/application.properties`中的百度API配置：

```properties
baidu.api.key=your_api_key_here
baidu.secret.key=your_secret_key_here
baidu.easydl.url=your_easydl_url_here
```

4. 编译运行

```bash
# 编译项目
mvn clean package

# 运行项目
mvn spring-boot:run

# 或者运行打包的jar
java -jar target/laji-0.0.1-SNAPSHOT.jar
```

5. 访问系统

打开浏览器访问：http://localhost:8080

默认管理员账号：
- 用户名：admin
- 密码：admin123

## 项目结构

```
laji/
├── src/
│   ├── main/
│   │   ├── java/org/example/laji/
│   │   │   ├── config/          # 配置类
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/      # 控制器
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── RecognitionHistoryController.java
│   │   │   │   └── WebController.java
│   │   │   ├── dto/             # 数据传输对象
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── LoginResponse.java
│   │   │   │   └── ApiResponse.java
│   │   │   ├── entity/          # 实体类
│   │   │   │   ├── Admin.java
│   │   │   │   ├── User.java
│   │   │   │   └── RecognitionHistory.java
│   │   │   ├── repository/      # 数据访问层
│   │   │   │   ├── AdminRepository.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── RecognitionHistoryRepository.java
│   │   │   ├── security/        # 安全配置
│   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   ├── service/         # 业务逻辑层
│   │   │   │   ├── AdminService.java
│   │   │   │   ├── UserService.java
│   │   │   │   ├── RecognitionHistoryService.java
│   │   │   │   └── BaiduAIService.java
│   │   │   ├── util/            # 工具类
│   │   │   │   └── JwtUtil.java
│   │   │   └── LajiApplication.java
│   │   └── resources/
│   │       ├── templates/       # 前端页面
│   │       │   ├── login.html
│   │       │   ├── dashboard.html
│   │       │   ├── users.html
│   │       │   └── history.html
│   │       ├── application.properties
│   │       └── data.sql         # 初始化数据
│   └── test/
├── .gitignore
├── pom.xml
└── README.md
```

## API接口

### 认证接口

- POST `/api/auth/login` - 管理员登录
- POST `/api/auth/register` - 注册管理员（可选）

### 用户管理接口

- GET `/api/users` - 获取所有用户
- GET `/api/users/page` - 分页获取用户
- GET `/api/users/{id}` - 获取单个用户
- POST `/api/users` - 创建用户
- PUT `/api/users/{id}` - 更新用户
- DELETE `/api/users/{id}` - 删除用户
- GET `/api/users/count` - 用户总数统计

### 识别历史接口

- GET `/api/recognition-history` - 获取所有识别记录
- GET `/api/recognition-history/page` - 分页获取识别记录
- GET `/api/recognition-history/user/{userId}` - 获取指定用户的识别记录
- GET `/api/recognition-history/{id}` - 获取单条记录
- POST `/api/recognition-history` - 创建识别记录
- DELETE `/api/recognition-history/{id}` - 删除记录
- GET `/api/recognition-history/count` - 记录总数统计

## 开发计划

### 已完成功能 ✅
- [x] 项目初始化和Git配置
- [x] 数据库设计和实体类创建
- [x] JWT认证实现
- [x] 管理员登录功能
- [x] 用户管理CRUD
- [x] 识别历史查看
- [x] 前端页面（登录、仪表板、用户管理、识别历史）

### 待开发功能 📝
- [ ] 图片上传功能
- [ ] 实际调用百度EasyDL API
- [ ] 微信小程序端开发
- [ ] 数据导出功能
- [ ] 统计报表功能
- [ ] 系统配置管理
- [ ] 操作日志记录

## Git使用

```bash
# 添加文件到暂存区
git add .

# 提交更改
git commit -m "feat: 完成基础功能开发"

# 查看状态
git status

# 查看提交历史
git log

# 创建分支
git branch feature/new-feature

# 切换分支
git checkout feature/new-feature

# 合并分支
git merge feature/new-feature
```

## 注意事项

1. **默认使用H2内存数据库**：重启后数据会丢失，生产环境请使用MySQL
2. **百度API配置**：需要自行申请百度EasyDL账号并配置API密钥
3. **密码安全**：生产环境请修改默认管理员密码
4. **JWT密钥**：生产环境请修改JWT密钥配置
5. **跨域配置**：如需要配合小程序使用，请根据实际情况调整CORS配置

## 许可证

本项目仅用于学习和演示目的。

## 联系方式

如有问题，请提Issue或联系开发者。

