# 垃圾识别管理系统

一个完整的垃圾识别管理系统，采用前后端分离架构，集成第三方AI图像识别服务。

## 项目简介

这是一个基于Spring Boot + Vue.js + 微信小程序的垃圾识别管理系统，采用前后端分离架构，主要功能包括：

- **用户端（微信小程序）**：分类展示、图片识别、用户管理等
- **管理后台（Vue.js）**：用户管理、数据统计、识别历史查看
- **后端服务（Spring Boot）**：RESTful API接口，提供稳定的数据服务
- **图像识别**：集成第三方AI服务（百度EasyDL）进行垃圾识别

用户通过微信小程序上传图片进行垃圾识别，管理员可以在Vue.js管理后台查看和管理用户及识别历史。

## 技术栈

### 后端服务
- **Spring Boot 3.5.7** - 后端框架
- **Spring Security + JWT** - 安全认证
- **Spring Data JPA** - 数据访问层
- **MySQL** - 关系型数据库
- **Lombok** - 代码简化
- **OkHttp** - HTTP客户端（调用第三方AI API）

### 管理后台（Vue.js）
- **Vue.js 3.x** - 前端框架
- **Vue Router** - 路由管理
- **Vuex/Pinia** - 状态管理
- **Element Plus / Ant Design Vue** - UI组件库
- **Axios** - HTTP请求库

### 用户端（微信小程序）
- **微信小程序框架** - 小程序开发
- **微信小程序API** - 图片上传、用户信息等

### 数据存储
- **MySQL 5.7+** - 关系型数据库

### 第三方服务
- **百度EasyDL** - 图像识别AI服务

## 系统架构

```
┌─────────────────┐         ┌─────────────────┐
│  微信小程序      │         │  管理后台(Vue.js)│
│  (用户端)        │         │  (管理员)        │
└────────┬────────┘         └────────┬────────┘
         │                             │
         │  HTTP/HTTPS                 │  HTTP/HTTPS
         │  RESTful API                │  RESTful API
         │                             │
         └─────────────┬───────────────┘
                       │
              ┌────────▼────────┐
              │  Spring Boot     │
              │  RESTful API     │
              │  后端服务        │
              └────────┬────────┘
                       │
         ┌─────────────┼─────────────┐
         │             │             │
    ┌────▼────┐  ┌────▼────┐  ┌────▼────┐
    │  MySQL  │  │百度EasyDL│  │ 其他服务 │
    │  数据库  │  │  AI服务  │  │         │
    └─────────┘  └─────────┘  └─────────┘
```

### 架构说明

- **前后端分离**：后端提供RESTful API，前端（小程序和管理后台）通过HTTP请求调用API
- **统一数据源**：微信小程序和管理后台共享同一个后端服务和数据库
- **第三方服务集成**：通过HTTP API调用百度EasyDL进行图像识别
- **可扩展性**：支持添加更多前端应用（如移动App）和第三方服务

## 功能特性

### 微信小程序端（用户端）

1. **垃圾识别**
   - 图片上传和识别
   - 垃圾分类结果展示
   - 识别历史记录查看

2**分类展示**
   - 可回收垃圾
   - 有害垃圾
   - 厨余垃圾
   - 其他垃圾

3**用户管理**
   - 识别历史查看
   - 个人中心

### 管理后台（Vue.js）

1.**用户管理**
   - 用户列表查看
   - 用户信息管理
   - 用户权限管理
   - 用户数据统计
2.**识别历史管理**
   - 查看所有识别记录
   - 按用户筛选
   - 按垃圾分类筛选
   - 分页显示
   - 图片预览
   - 删除记录
3.**数据统计**
   - 用户总数统计
   - 识别总次数统计
   - 分类统计
   - 数据可视化图表
   - 仪表板概览

### 后端API服务

1. **RESTful API接口**
   - 统一的API响应格式
   - 完整的错误处理
   - 接口文档支持

2. **安全认证**
   - JWT Token认证
   - 权限控制
   - 跨域配置（CORS）

3. **数据服务**
   - 用户数据管理
   - 识别历史管理
   - 数据统计分析

### 环境要求

#### 后端服务
- JDK 17+
- Maven 3.6+
- MySQL 5.7+

#### 管理后台（Vue.js）
- Node.js 16+
- npm 或 yarn

#### 微信小程序
- 微信开发者工具

### 配置说明

#### 1. 克隆项目
```bash
git clone <repository-url>
cd laji
```

#### 2. 后端服务配置

**2.1 配置数据库**

修改`src/main/resources/application.properties`中的数据库连接信息：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/laji_db?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8&allowPublicKeyRetrieval=true
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

然后执行 `init_mysql.sql` 脚本初始化数据库：
```bash
mysql -u root -p < init_mysql.sql
```

**2.2 配置百度EasyDL API**

修改`src/main/resources/application.properties`中的百度API配置：

```properties
baidu.api.key=your_api_key_here
baidu.secret.key=your_secret_key_here
baidu.easydl.url=your_easydl_url_here
```

**2.3 编译运行后端服务**

```bash
# 编译项目
mvn clean package

# 运行项目
mvn spring-boot:run

# 或者运行打包的jar
java -jar target/laji-0.0.1-SNAPSHOT.jar
```

后端服务默认运行在：http://localhost:8080

#### 3. 管理后台（Vue.js）配置

**3.1 安装依赖**

```bash
cd admin-frontend  # 进入管理后台目录
npm install
# 或
yarn install
```

**3.2 配置API地址**

修改配置文件中的后端API地址：

```javascript
// .env.development
VUE_APP_API_BASE_URL=http://localhost:8080/api
```

**3.3 启动开发服务器**

```bash
npm run serve
# 或
yarn serve
```

管理后台默认运行在：http://localhost:3000

#### 4. 微信小程序配置

**4.1 使用微信开发者工具**

1. 打开微信开发者工具
2. 导入小程序项目目录（`miniprogram`）
3. 配置AppID（需要申请微信小程序账号）

**4.2 配置后端API地址**

修改小程序配置文件中的后端API地址：

```javascript
// config.js
const API_BASE_URL = 'https://your-api-domain.com/api'
```

**4.3 编译运行**

在微信开发者工具中点击"编译"按钮即可预览。

#### 5. 默认管理员账号

- 用户名：admin
- 密码：admin123

## 项目结构

```
laji/
├── backend/                     # 后端服务（Spring Boot）
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/org/example/laji/
│   │   │   │   ├── config/          # 配置类
│   │   │   │   │   └── SecurityConfig.java
│   │   │   │   ├── controller/      # RESTful API控制器
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   ├── UserController.java
│   │   │   │   │   └── RecognitionHistoryController.java
│   │   │   │   ├── dto/             # 数据传输对象
│   │   │   │   │   ├── LoginRequest.java
│   │   │   │   │   ├── LoginResponse.java
│   │   │   │   │   └── ApiResponse.java
│   │   │   │   ├── entity/          # 实体类
│   │   │   │   │   ├── Admin.java
│   │   │   │   │   ├── User.java
│   │   │   │   │   └── RecognitionHistory.java
│   │   │   │   ├── repository/      # 数据访问层
│   │   │   │   │   ├── AdminRepository.java
│   │   │   │   │   ├── UserRepository.java
│   │   │   │   │   └── RecognitionHistoryRepository.java
│   │   │   │   ├── security/        # 安全配置
│   │   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   │   ├── service/         # 业务逻辑层
│   │   │   │   │   ├── AdminService.java
│   │   │   │   │   ├── UserService.java
│   │   │   │   │   ├── RecognitionHistoryService.java
│   │   │   │   │   └── BaiduAIService.java
│   │   │   │   ├── util/            # 工具类
│   │   │   │   │   └── JwtUtil.java
│   │   │   │   └── LajiApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── .gitignore
│   ├── init_mysql.sql           # MySQL初始化脚本
│   └── pom.xml
│
├── admin-frontend/              # 管理后台（Vue.js）
│   ├── src/
│   │   ├── assets/              # 静态资源
│   │   ├── components/           # 组件
│   │   ├── views/                # 页面
│   │   │   ├── Login.vue
│   │   │   ├── Dashboard.vue
│   │   │   ├── UserManagement.vue
│   │   │   ├── RecognitionHistory.vue
│   │   │   └── ContentManagement.vue
│   │   ├── router/               # 路由配置
│   │   ├── store/                 # 状态管理
│   │   ├── api/                   # API接口
│   │   ├── utils/                 # 工具函数
│   │   └── App.vue
│   ├── public/
│   ├── package.json
│   └── vue.config.js
│
├── miniprogram/                 # 微信小程序
│   ├── pages/                    # 页面
│   │   ├── index/                # 首页（识别）
│   │   ├── query/                # 查询页
│   │   ├── category/             # 分类页
│   │   └── profile/               # 个人中心
│   ├── components/               # 组件
│   ├── utils/                    # 工具函数
│   │   ├── api.js                # API封装
│   │   └── config.js             # 配置
│   ├── app.js
│   ├── app.json
│   └── app.wxss
│
├── .gitignore
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
## 许可证
本项目仅用于学习和演示目的。


