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
| Database     | SQLite & MySQL            |
| Services     | Google Play Services (Location), SmsManager, Telephony            |
| UI           | XML Layouts (Android Views) |

---

## 📂 Project Structure

```
True-Caller-Mobile-Application/
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml
│   ├── java/
│   │   └── ELFEKIHOns.truecaller/
│   │       ├── Affiche.java
            ├── Ajout.java
            ├── Contact.java
            ├── ContactHelper.java
            ├── ContactManager.java
            ├── DataBaseConfig.java
            ├── Edition.java
            ├── EnvoiSms.java
            ├── Home.java
            ├── JsonParser.java
            ├── MainActivity.java
            ├── MyContactAdapter.java
            ├── MyContactRecyclerAdapter.java
│   │       ├── SplashActivity.java
│   └── res/
│       ├── drawable/
│       ├── layout/
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
    <uses-feature android:name="android.hardware.telephony" android:required="false" />
    <uses-permission android:name="android.permission.CALL_PHONE" />
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

---

## 🗄️ Database

Uses **SQLite** (via `SQLiteOpenHelper`) for local storage of contact data.

Key table: `Contact.`

| Column       | Type    | Description             |
|--------------|---------|-------------------------|
| `id`         | INTEGER | Contact id             |
| `first`       | TEXT    | Contact first name       |
| `last`      | TEXT    | Contact last name           |
| `phone`      | TEXT    | Contact phone number          |
| `isFav`      | INTEGER    |  Indicates if contact is favorite          |

---

## 👩‍💻 Author

**Ons ELFEKIH**  
IT Engineering Student — Business Intelligence  
🔗 [LinkedIn](https://www.linkedin.com/in/ons-elfekih) · [Portfolio](https://portfolio-elfekih-ons.vercel.app/)
