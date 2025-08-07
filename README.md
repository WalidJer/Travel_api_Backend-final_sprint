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



