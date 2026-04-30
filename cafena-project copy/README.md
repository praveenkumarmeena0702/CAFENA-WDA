# ☕ Cafena Coffee Shop — React + Spring Boot

A full-stack coffee shop web application with cart, user profile, login, and smooth animations.

---

## 📁 Project Structure

```
cafena-project/
├── frontend/          ← React (Vite) app
│   ├── src/
│   │   ├── App.jsx
│   │   ├── index.css
│   │   ├── main.jsx
│   │   ├── context/
│   │   │   └── AppContext.jsx        ← Global state (cart, user, page)
│   │   └── components/
│   │       ├── Navbar.jsx            ← Fixed navbar with cart icon & profile
│   │       ├── Hero.jsx              ← Auto-sliding carousel
│   │       ├── MenuSection.jsx       ← Home page featured 4 coffees
│   │       ├── FullMenu.jsx          ← Full 8-item menu page (page2)
│   │       ├── LoginSection.jsx      ← Login/register form with validation
│   │       ├── UserTable.jsx         ← Registered users table
│   │       ├── Cart.jsx              ← Slide-in cart drawer
│   │       ├── Profile.jsx           ← Profile visible after login
│   │       ├── SuccessModal.jsx      ← Login success popup
│   │       └── Footer.jsx
│   ├── public/index.html
│   ├── package.json
│   └── vite.config.js               ← Proxies /api → localhost:8080
│
└── backend/           ← Spring Boot (Java 17)
    ├── pom.xml
    └── src/main/
        ├── java/com/cafena/
        │   ├── CafenaApplication.java
        │   ├── CorsConfig.java
        │   ├── controller/
        │   │   ├── AuthController.java   ← /api/auth/*
        │   │   └── CartController.java   ← /api/cart/*
        │   ├── model/
        │   │   ├── User.java
        │   │   ├── CartItem.java
        │   │   ├── LoginRequest.java
        │   │   └── CartRequest.java
        │   ├── repository/
        │   │   ├── UserRepository.java
        │   │   └── CartItemRepository.java
        │   └── service/
        │       ├── AuthService.java
        │       └── CartService.java
        └── resources/
            └── application.properties
```

---

## 🚀 How to Run

### Prerequisites
- Node.js 18+ and npm
- Java 17+ and Maven 3.8+

---

### 1. Start the Backend (Spring Boot)

```bash
cd cafena-project/backend
mvn spring-boot:run
```

- Runs on **http://localhost:8080**
- H2 Console: **http://localhost:8080/h2-console**
  - JDBC URL: `jdbc:h2:mem:cafenadb`
  - Username: `sa` | Password: *(empty)*

---

### 2. Start the Frontend (React + Vite)

```bash
cd cafena-project/frontend
npm install
npm run dev
```

- Runs on **http://localhost:5173**
- Vite automatically proxies `/api/*` → `http://localhost:8080`

---

## 🔗 API Endpoints

### Auth
| Method | Endpoint              | Description              |
|--------|-----------------------|--------------------------|
| POST   | `/api/auth/register`  | Register a new user      |
| POST   | `/api/auth/login`     | Login with email+password|
| GET    | `/api/auth/users`     | Get all registered users |

**Register body:**
```json
{
  "username": "John",
  "email": "john@gmail.com",
  "password": "secret123",
  "phone": "9876543210"
}
```

### Cart
| Method | Endpoint                      | Description            |
|--------|-------------------------------|------------------------|
| GET    | `/api/cart/{userId}`          | Get user's cart        |
| POST   | `/api/cart/add`               | Add/update item        |
| PUT    | `/api/cart/{itemId}/quantity` | Change item quantity   |
| DELETE | `/api/cart/{itemId}`          | Remove single item     |
| DELETE | `/api/cart/clear/{userId}`    | Clear entire cart      |

---

## ✨ Features

| Feature           | Details                                              |
|-------------------|------------------------------------------------------|
| 🎠 Carousel       | Auto-sliding hero with dot navigation + arrow buttons|
| ☕ Menu            | Home preview + Full menu page with 8 drinks          |
| 🛒 Cart           | Slide-in drawer, qty controls, total calculation     |
| 👤 Profile        | Visible only after login, shows cart summary         |
| 🔐 Login/Register | Full validation (email regex, 10-digit phone)        |
| 📋 User Table     | Displays all registered users                        |
| 🎬 Animations     | fadeIn, slideIn, modalPop, cartBounce, drawerIn      |
| 🔗 Backend        | Spring Boot + JPA + H2 (swap for MySQL in prod)      |

---

## 🔄 Switching to MySQL (Production)

Replace in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cafenadb
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

Add MySQL dependency to `pom.xml`:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

---

## 🎨 Tech Stack

- **Frontend:** React 18, Vite, React Context API, CSS Animations
- **Backend:** Spring Boot 3.2, Spring Data JPA, H2/MySQL, Lombok
- **Styling:** Pure CSS (matches original Cafena design — warm coffee tones)
