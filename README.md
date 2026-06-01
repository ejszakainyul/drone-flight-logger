# Drone Flight Logger 🚁

Egy modern Android alkalmazás, amely segít a drón repülések naplózásában, kezelésében és elemzésében.

## Funkciók

### 📊 Főoldal
- **Telemetria összefoglalás**: Összes repülés száma, teljes repülési idő
- **Statisztikák**: Átlagos magasság, átlagos sebesség
- **Legutóbbi repülések**: Gyors hozzáférés az elmúlt repülésekhez

### 🚁 Drón Kezelés
- Több drón regisztrálása
- Drón specifikációk tárolása (modell, gyártó, sorozatszám, teljesítmény)
- Drón törlése

### ✈️ Repülési Naplózás
- Repülés adatok rögzítése:
  - Magasság, sebesség, távolság
  - Akkumulátor szint (kezdet/vég)
  - GPS szignál erősség
  - Hőmérséklet
  - Helyadatok
  - Időjárási körülmények
  - Megjegyzések
- Dátum/idő alapú keresés

### 💾 Adatbázis
- **Room ORM** - Helyi SQLite adatbázis
- Relációs adatbázis struktúra
- Automatikus migráció és verziókezelés

## Technológiai Stack

### Android & Kotlin
- **Language**: Kotlin
- **Min SDK**: API 26
- **Target SDK**: API 37
- **Build System**: Gradle with Version Catalog

### Architecture
- **MVVM**: ViewModel + StateFlow
- **Dependency Injection**: Hilt
- **Database**: Room ORM
- **Async**: Kotlin Coroutines

### UI Framework
- **Jetpack Compose**: Modern deklaratív UI
- **Material Design 3**: Material3 komponensek
- **Navigation**: Jetpack Navigation Compose

### Libraries
```
- androidx.room:room-runtime:2.6.0
- com.google.dagger:hilt-android:2.48
- org.jetbrains.kotlinx:kotlinx-coroutines:1.7.3
- androidx.compose.*:* (Compose BOM 2023.10.00)
```

## Projekt Struktúra

```
src/main/java/com/dronelogger/app/
├── data/
│   ├── database/
│   │   ├── FlightDatabase.kt
│   │   ├── FlightLogDao.kt
│   │   ├── DroneInfoDao.kt
│   │   └── DateConverters.kt
│   ├── model/
│   │   ├── FlightLog.kt
│   │   └── DroneInfo.kt
│   └── repository/
│       ├── FlightLogRepository.kt
│       └── DroneRepository.kt
├── di/
│   └── DatabaseModule.kt
├── ui/
│   ├── screen/
│   │   ├── HomeScreen.kt
│   │   ├── AddFlightScreen.kt
│   │   ├── DroneScreen.kt
│   │   └── AddDroneScreen.kt
│   ├── viewmodel/
│   │   ├── FlightLogViewModel.kt
│   │   └── DroneViewModel.kt
│   ├── navigation/
│   │   └── AppNavigation.kt
│   └── theme/
│       ├── Theme.kt
│       └── Type.kt
├── MainActivity.kt
└── DroneFlightLoggerApplication.kt
```

## Adatbázis Schema

### flight_logs
```sql
CREATE TABLE flight_logs (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    drone_id INTEGER NOT NULL,
    start_time TEXT NOT NULL,
    end_time TEXT,
    duration_minutes INTEGER,
    max_altitude REAL,
    max_speed REAL,
    distance_traveled REAL,
    battery_start INTEGER,
    battery_end INTEGER,
    gps_signal INTEGER,
    temperature REAL,
    location_latitude REAL,
    location_longitude REAL,
    location_name TEXT,
    notes TEXT,
    weather_condition TEXT,
    wind_speed REAL,
    FOREIGN KEY(drone_id) REFERENCES drone_info(id)
)
```

### drone_info
```sql
CREATE TABLE drone_info (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    model TEXT NOT NULL,
    manufacturer TEXT,
    serial_number TEXT,
    color TEXT,
    purchase_date TEXT,
    max_altitude REAL,
    max_speed REAL,
    battery_capacity INTEGER,
    weight REAL,
    notes TEXT
)
```

## Telepítés & Futtatás

### Előfeltételek
- Android Studio Flamingo vagy újabb
- Java 17+
- Gradle 8.1.1+

### Lépések

1. **Repository klónozása**
```bash
git clone <repo-url>
cd drone-flight-logger
```

2. **Build futtatása**
```bash
./gradlew build
```

3. **Alkalmazás telepítése**
```bash
./gradlew installDebug
```

4. **Emulátoron/Eszközön futtatás**
   - Android Studio → Run → Select Device

## Jövőbeli Fejlesztések

### Tervezett Funkciók
- [ ] GPS nyomkövetés Real-time
- [ ] Térkép integráció (Google Maps)
- [ ] Exportálás (CSV, PDF)
- [ ] Bluetooth drón kapcsolat
- [ ] Mesterséges intelligencia alapú telemetria elemzés
- [ ] Felhő szinkronizáció
- [ ] Többnyelvű támogatás
- [ ] Offline üzemmód

### Opcionális Integrációk
- DJI SDK
- ArduPilot API
- OpenWeatherMap API
- Firebase Analytics

## Licenc

MIT License - Szabadon használható és módosítható

## Szerző

Drone Flight Logger App by Drone Enthusiasts

---

🚁 Boldog drón pilótázást! 🎉
