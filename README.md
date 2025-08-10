# Travel API Backend

This is the **Spring Boot backend** for the Travel API project, responsible for managing all core data related to flights, passengers, airports, airlines, and more.

## Technologies Used

- Java 21
- Spring Boot 3.5.4
- Spring Data JPA
-  MySQL
- Maven
- JUnit 5 & Mockito (unit tests)


---

## Entities & Relationships

- **City** ⟶ One-to-Many with Passengers, Airports
- **Airport** ⟶ Belongs to a City; has Many Gates, Arrivals & Departures
- **Flight** ⟶ Belongs to Airline, Aircraft, Gate, Departure & Arrival Airports
- **Passenger** ⟶ Many-to-Many with Flights
- **Airline** ⟶ Has Many Aircraft & Flights
- **Aircraft** ⟶ Belongs to Airline; has Many Flights
- **Gate** ⟶ Belongs to Airport; has Many Flights

---

## REST Endpoints

| Entity      | Endpoint | Methods                            |
|-------------|----------|------------------------------------|
| City        | `/cities` | GET, POST, GETByID, UPDATE, DELETE          |
| Airport     | `/airports` | GET, POST, GETByID, UPDATE, DELETE          |
| Passenger   | `/passengers` | GET, POST, GETByID, UPDATE, DELETE          |
| Airline     | `/airlines` | GET, POST, GETByID, UPDATE, DELETE          |
| Aircraft    | `/aircraft` | GET, POST, GETByID, UPDATE, DELETE          |
| Gate        | `/gates` | GET, POST, GETByID, UPDATE, DELETE          |
| Flight      | `/flights` | GET, POST, GETByID, UPDATE, DELETE |

Each POST expects a raw entity (not DTO). Each GET returns a DTO.

---

## DTO Mapping Logic

- We created lightweight **DTO classes** for each entity to:
    - Avoid cyclic serialization issues
    - Only expose relevant fields
- Mapping is done in service layer using Java Streams:

```java
public List<CityDTO> getAllCities() {
    return cityRepository.findAll().stream()
           .map(CityDTO::new)
           .collect(Collectors.toList());
}
```

---

## Sample Create Logic (with ID resolution)

```java
public AirportDTO createAirport(Airport airport) {
    if (airport.getCity() != null && airport.getCity().getId() != null) {
        airport.setCity(cityRepository.findById(airport.getCity().getId()).orElse(null));
    }
    return new AirportDTO(airportRepository.save(airport));
}
```

---

## Unit Tests (Mockito)

- Unit tests were written for each `Service` class
- Mocked repositories with `@Mock`
- Injected service under test with `@InjectMocks`

Example:

```java
@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock private CityRepository cityRepository;
    @InjectMocks private CityService cityService;

    @Test
    void testCreateCity() {
        City input = new City(null, "Calgary", "Alberta", 1000000, null, null);
        City saved = new City(1L, "Calgary", "Alberta", 1000000, null, null);

        when(cityRepository.save(input)).thenReturn(saved);

        CityDTO result = cityService.createCity(input);
        assertEquals("Calgary", result.getName());
    }
}
```

Test classes:
- `CityServiceTest`
- `AirportServiceTest`
- `FlightServiceTest`
- `GateServiceTest`
- `AircraftServiceTest`
- `AirlineServiceTest`
- `PassengerServiceTest`

---

## Running the App

Use your IDE or terminal:

```bash
./mvnw spring-boot:run
```

By default, MySQL console available at: `http://localhost:8080`

---

## Sample Flight JSON

```json
{
  "flightNumber": "AC101",
  "status": "SCHEDULED",
  "departureTime": "2025-08-07T10:00:00",
  "arrivalTime": "2025-08-07T12:30:00",
  "departureAirport": {"id": 1},
  "arrivalAirport": {"id": 2},
  "airline": {"id": 1},
  "aircraft": {"id": 1},
  "gate": {"id": 1},
  "passengers": []
}
```
---


## Running with Docker

### Step 1: Build the App
```
mvn clean package
```
### Step 2: Build Docker Image
```
docker build -t airport_management_api:latest .
```

### Step 3: Run with Docker Compose
```
docker-compose up
```

### Step 4: Tag your image
```
 docker tag airport_management_api:latest walidjer/airport_management_api:latest

```

### Step 5: Push the image to Docker Hub
```
 docker push walidjer/airport_management_api:latest

```

OR
Simply Use this image in docker-compose.yml
```yaml
image: walidjer/airport_management_api:latest
```

## Deploying the Spring Boot Backend on AWS (RDS + EC2 )

This guide moves your backend from local to AWS using:
- **RDS (MySQL)** for the database
- **EC2** to run your Spring Boot app in **Docker**
- Environment variables for secrets (recommended)

> **Tip:** Keep your **React frontend** pointed at your backend’s **EC2 public IP** (or domain) on port `8080` or `80` depending on how you run the container.

---

### 0) Prerequisites

- AWS Account with permissions for **RDS**, **EC2**, **IAM**, **S3** (optional later)
- Docker installed locally (for image build & push)
- Docker Hub account 
- Java 21+ and Maven (for building the jar)

---

## 1) Create RDS MySQL

1. Go to **RDS → Databases → Create database**
2. **Standard create** → **MySQL** (e.g., 8.0.x)
3. Settings:
  - DB instance identifier: `travel-app-db`
  - Master username: `admin`
  - Master password: `********`
4. DB instance class: `db.t3.micro` (Free Tier where available)
5. Storage: `20 GB` GP (default is fine)
6. Connectivity:
  - VPC: Default (or your VPC)
  - **Public access: Yes** (for initial testing; for prod, use private + EC2 SG)
  - Security group: select or create (we’ll adjust rules next)

> ⚠️ **Security**: Public access is convenient for testing, but **not recommended** for production. Prefer private RDS with access only from your EC2’s Security Group.

---

## 2) Security Group Rules (RDS + EC2)

### RDS Security Group (Inbound)
- **Type:** MySQL/Aurora
- **Port:** `3306`
- **Source:**
  - Testing from your laptop → **My IP**
  - From EC2 only (recommended) → **EC2 instance’s Security Group**

### EC2 Security Group (Inbound)
- **SSH**: TCP `22` from **My IP**
- **App**: TCP `8080` (or `80` if you map Docker to 80) from **0.0.0.0/0** (public)

> **Outbound** on both SGs can be left as “Allow all” (default) so EC2 can reach RDS on `3306`.

---

## 3) Get Your RDS Endpoint

- RDS → Databases → your instance → copy **Endpoint**, e.g.  
  `travel-app-db.xxxxxx.us-east-1.rds.amazonaws.com`

Also decide on your DB name (e.g. `airport_management`). RDS won’t create it automatically unless you set `spring.jpa.hibernate.ddl-auto=create` (careful with prod). You can create it once with MySQL Workbench.

## 4) Configure Spring Boot for RDS
   You can use either application.properties or environment variables.
   Recommended for Docker/EC2: use env vars.

### application.properties (local testing)
properties
```
spring.datasource.url=jdbc:mysql://<RDS-ENDPOINT>:3306/airport_management
spring.datasource.username=admin
spring.datasource.password=YOUR_STRONG_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```
Rebuild JAR & Docker Image and push it to Docker Hub to accept the changes

## 5) Launch EC2 & Install Docker
- EC2 → Launch instance

- AMI: Amazon Linux 2 (or Ubuntu), x86_64 recommended

- Type: t2.micro (Free Tier)

- Key pair: select/create

- Security Group: as per step 2

- Connect to EC2

```
 sudo yum update -y
 sudo yum install docker -y
 sudo usermod -aG docker ec2-user
 id ec2-user  # confirm docker group added
 newgrp docker  # apply docker group without logout
 sudo systemctl enable docker.service
 sudo systemctl start docker.service
 sudo systemctl status docker.service
```
## 6) Login to DockerHub

```
docker login
Username: walidjer
Password: <DockerHub password>
```

## 7) Pull and Run the Image
```
docker pull walidjer/airport_management_api:latest
# Run the container on port 8080
docker run -it -p 80:8080 walidjer/airport_management__api:latest
```


