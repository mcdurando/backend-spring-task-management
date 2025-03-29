# 📝 **Task Management System**  

### 🚀 **Enterprise-Grade Spring Boot Application**  

This project is a **task management system** built with **Spring Boot 3** following **Hexagonal Architecture**. It includes **JWT authentication, PostgreSQL, caching, Kafka messaging, resilience, and observability** for production-ready deployment.

---

## 📌 **Features**  
✅ **Hexagonal Architecture (Ports & Adapters)**  
✅ **Spring Security (JWT + Role-Based Access Control + OAuth2 Ready)**  
✅ **Spring Data JPA + QueryDSL + Flyway Migration**  
✅ **Database: PostgreSQL with Advanced Indexing**  
✅ **Caching (Redis + Caffeine)**  
✅ **Observability (Spring Boot Actuator + Prometheus + Grafana)**  
✅ **Logging (Logback + ELK Stack Ready)**  
✅ **Containerization (Docker & Kubernetes Configs)**  
✅ **Resilience (Circuit Breaker with Resilience4j)**  
✅ **Async Processing (Spring Events + Kafka Integration)**  
✅ **CI/CD Ready (GitHub Actions + Docker Deployment)**  

---

## 📁 **Project Structure**  
```
task-management
│── src
│   ├── main/java/com/example/taskmanagement
│   │   ├── application  (Use Cases / Services)
│   │   ├── domain  (Entities / Aggregates / Business Logic)
│   │   ├── infrastructure  (Persistence, Security, Config, Kafka)
│   │   │   ├── adapters  (Repositories, External Services)
│   │   │   ├── config  (Spring Configurations)
│   │   │   ├── security  (JWT + OAuth2)
│   │   ├── web  (Controllers / DTOs)
│   ├── test  (Unit, Integration & Contract Tests)
│── pom.xml
│── docker-compose.yml
│── k8s/ (Kubernetes Deployment Configs)
│── README.md
```

---

## 🛠 **Setup Instructions**  

### **1️⃣ Prerequisites**  
Ensure you have the following installed:  
- **JDK 17+**  
- **Maven 3+**  
- **Docker & Docker Compose**  
- **PostgreSQL & Redis (or use Docker)**  

### **2️⃣ Clone the Repository**  
```sh
git clone https://github.com/YOUR_GITHUB/task-management.git
cd task-management
```

### **3️⃣ Configure the Application**  
Modify `application.properties`:  
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskdb
spring.datasource.username=admin
spring.datasource.password=password
spring.cache.type=redis
jwt.secret=MySuperSecretKey
```

### **4️⃣ Start PostgreSQL & Redis using Docker**  
```sh
docker-compose up -d
```

### **5️⃣ Run the Application**  
```sh
mvn spring-boot:run
```
or with Docker:  
```sh
docker build -t task-management .
docker run -p 8080:8080 task-management
```

---

## 🔐 **Authentication & Security**  
### **Register a User**
```sh
POST /api/auth/register
{
    "username": "admin",
    "password": "securePass",
    "role": "ADMIN"
}
```

### **Login and Get JWT Token**
```sh
POST /api/auth/login
{
    "username": "admin",
    "password": "securePass"
}
```

### **Access a Protected Endpoint**
Use the token in headers:
```sh
GET /api/tasks
Authorization: Bearer <your-jwt-token>
```

---

## 📌 **API Endpoints**  
| Method | Endpoint | Description | Auth Required |
|--------|---------|-------------|--------------|
| `POST` | `/api/auth/register` | Register a new user | ❌ No |
| `POST` | `/api/auth/login` | Login and get JWT | ❌ No |
| `GET` | `/api/tasks` | Get all tasks | ✅ Yes (USER) |
| `POST` | `/api/tasks` | Create a new task | ✅ Yes (ADMIN) |
| `PUT` | `/api/tasks/{id}` | Update task | ✅ Yes (ADMIN) |
| `DELETE` | `/api/tasks/{id}` | Delete task | ✅ Yes (ADMIN) |

---

## 🛡 **Resilience with Circuit Breaker**  
This project uses **Resilience4j** to prevent failures. If the task service is down, a fallback method returns an empty list instead of an error.  

```java
@CircuitBreaker(name = "taskService", fallbackMethod = "fallbackMethod")
public List<Task> getAllTasks() {
    return taskRepository.findAll();
}

private List<Task> fallbackMethod(Throwable ex) {
    return Collections.emptyList();
}
```

---

## 📊 **Observability & Monitoring**  
The application exposes **Prometheus metrics** and integrates with **Grafana**.  

### **Enable Monitoring**
1. Start Prometheus & Grafana:
   ```sh
   docker-compose -f monitoring.yml up -d
   ```
2. Access:
   - **Prometheus:** `http://localhost:9090`
   - **Grafana:** `http://localhost:3000` (User: admin / Password: admin)

---

## ✅ **Testing & CI/CD**  
This project uses:  
✔ **Unit Tests (JUnit, Mockito)**  
✔ **Integration Tests (Testcontainers)**  
✔ **Contract Testing (Spring Cloud Contract)**  
✔ **Mock APIs (WireMock)**  
✔ **CI/CD with GitHub Actions**  

### **Run Tests Locally**
```sh
mvn test
```

### **GitHub Actions CI/CD Pipeline**
📄 **`.github/workflows/deploy.yml`**
```yaml
name: CI/CD Pipeline

on: push

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout Code
        uses: actions/checkout@v3

      - name: Build & Test
        run: mvn clean verify
```

---

## 🐳 **Docker & Kubernetes Deployment**  

### **1️⃣ Build Docker Image**
```sh
docker build -t task-management .
```

### **2️⃣ Run in Docker**
```sh
docker run -p 8080:8080 task-management
```

### **3️⃣ Deploy to Kubernetes**
```sh
kubectl apply -f k8s/deployment.yaml
```

---

## 📌 **Future Enhancements**
🚀 **Multi-Tenancy Support**  
🚀 **GraphQL API Support**  
🚀 **Event Sourcing with Kafka**  
🚀 **Full OAuth2 with Google & GitHub**  

---

## ❤️ **Contributing**
1. **Fork the repository**  
2. **Create a new branch:** `feature-xyz`  
3. **Commit changes & push:**  
   ```sh
   git commit -m "Added feature XYZ"
   git push origin feature-xyz
   ```
4. **Create a Pull Request**  

---

## 📄 **License**
This project is **open-source** and available under the **MIT License**.  

---

## 📞 **Contact & Support**
For issues or questions, feel free to:  
📧 **Email:** support@example.com  
🐙 **GitHub Issues:** [Open an Issue](https://github.com/YOUR_GITHUB/task-management/issues)  

