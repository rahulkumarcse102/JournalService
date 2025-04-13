# Journal Service - Apica Backend Assignment

This is a Spring Boot microservice responsible for journaling user-related events consumed from Kafka (`user-events` topic). Events are persisted in a PostgreSQL database and can be retrieved via a secured REST API.

---

##  Features
- Kafka consumer (listens to `user-events` topic)
- Stores incoming event messages with timestamps
- Exposes API to fetch journal entries
- Role-based access control (only `ADMIN` role can access journal data)

---

##  Tech Stack
- Java 17
- Spring Boot 3.4.4
- Spring Kafka
- Spring Data JPA
- Spring Security
- PostgreSQL
- Docker Compose

---

##  Running Locally

### Prerequisites:
- Java 17
- Maven
- Docker & Docker Compose (for Kafka + PostgreSQL)

### Steps:
1. Clone the repo and navigate into it:
```bash
git clone https://github.com/your-username/journalservice.git
cd journalservice
```

2. Start required services (Kafka + PostgreSQL):
```bash
docker-compose up -d
```

3. Run the journal service:
```bash
mvn clean install
mvn spring-boot:run
```

4. Service runs at `http://localhost:8081`

---

## Security
- API access is protected using Spring Security
- Only authenticated users with `ADMIN` role can access journal data
- Uses HTTP Basic Auth for simplicity

---

##  REST API

| Method | Endpoint           | Access Role | Description            |
|--------|--------------------|-------------|------------------------|
| GET    | `/api/journals`    | ADMIN       | Fetch all journaled events |

### Example Response:
```json
[
  {
    "id": 1,
    "message": "User registered: john",
    "timestamp": "2025-04-12T12:34:56"
  }
]
```

---

## 🔧 application.properties
```properties
spring.application.name=journalservice
spring.datasource.url=jdbc:postgresql://localhost:5432/journaldb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.kafka.bootstrap-servers=localhost:9092
server.port=8081
```

---

##  Docker Compose (Shared Setup)
Use the same `docker-compose.yml` from UserService:
```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: journaldb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"

  zookeeper:
    image: confluentinc/cp-zookeeper:7.4.0
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:7.4.0
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
```


