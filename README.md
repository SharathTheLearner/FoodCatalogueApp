# 🍔 Food Delivery App — Microservices Architecture

A backend food delivery application built using **Java Spring Boot** following a **Microservices Architecture**. The system handles restaurant listings, food catalogue, user management, and order processing — all as independent services communicating via **OpenFeign** and registered with **Eureka Service Discovery**.

---

## 📐 Architecture Overview

```
                        ┌──────────────────────┐
                        │     Eureka Server     │
                        │      Port: 8761       │
                        └──────────┬───────────┘
                                   │ Service Discovery
              ┌────────────────────┼─────────────────────┐
              │                    │                      │
   ┌──────────▼──────┐  ┌──────────▼──────┐  ┌──────────▼──────┐
   │  Restaurant     │  │  Food Catalogue  │  │ User Information │
   │  Listing MS     │  │       MS         │  │       MS         │
   │  Port: 9091     │  │   Port: 9092     │  │   Port: 9093     │
   │  MySQL DB       │  │   MySQL DB       │  │   MySQL DB       │
   └─────────────────┘  └──────────┬───────┘  └──────────────────┘
                                   │ OpenFeign
                        ┌──────────▼───────────┐
                        │      Order MS         │
                        │     Port: 9094        │
                        │     MongoDB           │
                        └───────────────────────┘
```

---

## 🛠️ Tech Stack

| Category | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot 4.0.6 |
| Service Discovery | Spring Cloud Netflix Eureka 2025.1.1 |
| Inter-Service Communication | Spring Cloud OpenFeign 5.0.1 |
| ORM | Spring Data JPA / Hibernate |
| Database (SQL) | MySQL |
| Database (NoSQL) | MongoDB |
| Boilerplate Reduction | Lombok |
| Build Tool | Maven |
| API Testing | Insomnia |

---

## 📦 Microservices Overview

| Service | Port | Database | Description |
|---|---|---|---|
| Eureka Server | 8761 | — | Service registry and discovery |
| Restaurant Listing | 9091 | MySQL | Manage restaurant data |
| Food Catalogue | 9092 | MySQL | Manage food items per restaurant |
| User Information | 9093 | MySQL | Manage user data |
| Order MS | 9094 | MongoDB | Place and manage orders |

---

## 🚀 How to Run

### Prerequisites
- Java 17+
- Maven
- MySQL
- MongoDB
- An IDE (STS / IntelliJ)

### Step-by-step

**1. Clone the repository**
```bash
git clone https://github.com/your-username/food-delivery-app.git
cd food-delivery-app
```

**2. Set up MySQL databases**

Create separate databases for each service:
```sql
CREATE DATABASE restaurant_db;
CREATE DATABASE food_catalogue_db;
CREATE DATABASE user_db;
```

**3. Configure `application.properties` for each service**

Update the DB credentials in each service's `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<db_name>
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**4. Start services in this exact order:**

```
1. Eureka Server        → http://localhost:8761
2. Restaurant Listing   → http://localhost:9091
3. Food Catalogue       → http://localhost:9092
4. User Information     → http://localhost:9093
5. Order MS             → http://localhost:9094
```

**5. Verify all services are registered**

Open `http://localhost:8761` in your browser — all 4 client services should show status **UP**.

---

## 📡 API Endpoints

### 🏪 Restaurant Listing Service (Port: 9091)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/restaurant/fetchAllRestaurants` | Fetch all restaurants |
| GET | `/restaurant/fetchRestaurant/{id}` | Fetch restaurant by ID |
| GET | `/restaurant/fetchRestaurants?page=0&size=5&sortBy=name&direction=asc&search=pizza` | Fetch restaurants with pagination, sorting and search |
| POST | `/restaurant/addRestaurant` | Add a new restaurant |

**Sample POST payload:**
```json
{
    "name": "Restaurant 1",
    "address": "Test Address",
    "city": "Bengaluru",
    "restaurantDescription": "Test Description"
}
```

---

### 🍕 Food Catalogue Service (Port: 9092)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/foodCatalogue/fetchRestauAndFoodById/{restaurantId}` | Fetch restaurant details with food items |
| GET | `/foodCatalogue/fetchFoodItems?page=0&size=5&sortBy=itemName&direction=asc&search=dosa&isVeg=true` | Fetch food items with pagination, sorting, search and veg filter |

---

### 👤 User Information Service (Port: 9093)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/user/addUser` | Register a new user |
| GET | `/user/fetchById/{id}` | Fetch user by ID |
| GET | `/user/fetchUsers?page=0&size=5&sortBy=userName&direction=asc&search=Bengaluru` | Fetch users with pagination, sorting and search |

**Sample POST payload:**
```json
{
    "userName": "User 1",
    "userPassword": "1234",
    "address": "MG Road",
    "city": "Bengaluru"
}
```

---

### 📦 Order MS (Port: 9094)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/order/saveOrder` | Place a new order |
| GET | `/order/viewAllOrders` | View all orders |
| GET | `/order/fetchOrderById/{id}` | Fetch order by ID |

**Sample POST payload:**
```json
{
    "foodItemsList": [
        {
            "id": 1,
            "itemName": "Dosa",
            "itemDescription": "Delicious Dosa",
            "price": 100,
            "quantity": 1,
            "restaurantId": 1,
            "veg": true
        }
    ],
    "restaurantDTO": {
        "id": 1,
        "name": "Restaurant 1",
        "address": "Test Address",
        "city": "Bengaluru",
        "restaurantDescription": "Test Description"
    },
    "userID": 2
}
```

---

## 🔧 Key Design Decisions

### Entity-DTO Mapper Pattern
All services use a custom **Mapper** class to convert between Entity and DTO, ensuring the database entity is never directly exposed via the API.

```
Controller → DTO → Service → Mapper → Entity → Repository
```

### OpenFeign for Inter-Service Communication
Services communicate using **Spring Cloud OpenFeign** — a declarative REST client that integrates with Eureka for automatic service discovery and load balancing.

```java
@FeignClient(name = "restaurant-service")
public interface RestaurantClient {
    @GetMapping("/restaurant/fetchRestaurant/{id}")
    Restaurant fetchRestaurantById(@PathVariable("id") Integer id);
}
```

### MongoDB Sequence Generator
Since MongoDB doesn't auto-generate sequential IDs like SQL databases, a custom **SequenceGenerator** is implemented using `MongoOperations` to maintain an auto-incrementing `orderId`.

### Pagination & Sorting
All listing endpoints support pagination, sorting, and search using Spring Data's `Pageable` interface — no manual SQL queries needed.

```
GET /restaurant/fetchRestaurants?page=0&size=5&sortBy=name&direction=asc&search=pizza
```

---

## 📁 Project Structure

```
food-delivery-app/
│
├── eureka-server/
│   └── src/main/
│       ├── java/.../EurekaApplication.java
│       └── resources/application.properties
│
├── restaurant-listing/
│   └── src/main/java/.../
│       ├── controller/RestaurantController.java
│       ├── service/RestaurantService.java
│       ├── mapper/RestauMapper.java
│       ├── entity/Restaurant.java
│       ├── dto/RestaurantDTO.java
│       └── repo/RestaurantRepo.java
│
├── food-catalogue/
│   └── src/main/java/.../
│       ├── controller/FoodCatalogueController.java
│       ├── service/FoodCatalogueService.java
│       ├── feign/RestaurantClient.java
│       ├── entity/FoodItem.java
│       ├── dto/FoodCataloguePage.java
│       └── repo/FoodItemRepo.java
│
├── user-information/
│   └── src/main/java/.../
│       ├── controller/UserController.java
│       ├── service/UserService.java
│       ├── mapper/UserMapper.java
│       ├── entity/User.java
│       ├── dto/UserDTO.java
│       └── repo/UserRepo.java
│
└── order-ms/
    └── src/main/java/.../
        ├── controller/OrderController.java
        ├── service/OrderService.java
        ├── service/SequenceGenerator.java
        ├── feign/UserClient.java
        ├── mapper/OrderMapper.java
        ├── entity/Order.java
        ├── entity/Sequence.java
        ├── dto/OrderDTO.java
        ├── dto/OrderDTOFromFE.java
        └── repo/OrderRepo.java
```

---

## 🌱 Future Enhancements

- [ ] Add API Gateway (Spring Cloud Gateway)
- [ ] Add Circuit Breaker (Resilience4j)
- [ ] Add Spring Security with JWT authentication
- [ ] Dockerize all services with Docker Compose
- [ ] Add Swagger/OpenAPI documentation
- [ ] Add unit and integration tests

---

## 👨‍💻 Author

**Sharath Kumar U B**
Software Engineering Analyst | Java & Spring Boot | GenAI/ML
