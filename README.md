# 📱 True Caller Mobile Application

A native Android application built in Java that replicates core TrueCaller functionalities — including contact management, number search, call handling, SMS messaging, and location services.

---

## 🚀 Features

- **Splash Screen** — Custom branded launch screen on app startup
- **Contact Management** — Add, edit, and display contact information
- **Number Search** — Look up phone numbers within the local database
- **Call Handling** — Initiate direct phone calls from within the app
- **SMS Management** — Send SMS messages directly through the app
- **Location Services** — Access fine and coarse location data

---

## 🛠️ Tech Stack

| Layer        | Technology          |
|--------------|---------------------|
| Language     | Java                |
| Platform     | Android (Android Studio) |
| Database     | SQLite              |
| UI           | XML Layouts (Android Views) |

---

## 📂 Project Structure

```
True-Caller-Mobile-Application/
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml
│   ├── java/
│   │   └── com.example.truecaller/
│   │       ├── MainActivity.java
│   │       ├── SplashActivity.java
│   │       ├── ContactActivity.java
│   │       ├── SearchActivity.java
│   │       └── DatabaseHelper.java
│   └── res/
│       ├── layout/
│       ├── drawable/
│       └── values/
└── README.md
```

---

## ⚙️ Setup & Installation

### Prerequisites

- Android Studio (latest stable)
- Android SDK (API level 21+)
- Java JDK 8+

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/OnsElfekih/True-Caller-Mobile-Application.git
   ```

2. **Open in Android Studio**
   - File → Open → Select the cloned project folder

3. **Sync Gradle**
   - Android Studio will prompt to sync — click **Sync Now**

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click ▶ **Run**

---

## 🔐 Permissions

The following permissions are declared in `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.READ_CONTACTS" />
<uses-permission android:name="android.permission.WRITE_CONTACTS" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

---

## 🗄️ Database

Uses **SQLite** (via `SQLiteOpenHelper`) for local storage of contact data.

Key table: `contacts`

| Column       | Type    | Description             |
|--------------|---------|-------------------------|
| `id`         | INTEGER | Primary key             |
| `name`       | TEXT    | Contact full name       |
| `phone`      | TEXT    | Phone number            |
| `email`      | TEXT    | Email address           |

---

## 👩‍💻 Author

**Ons ELFEKIH**  
IT Engineering Student — Business Intelligence  
🔗 [GitHub](https://github.com/OnsElfekih) · [LinkedIn](https://www.linkedin.com/in/ons-elfekih)

---

## 📄 License

This project is for academic and portfolio purposes.
