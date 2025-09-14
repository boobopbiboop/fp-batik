# Indonesian Traditional Fabric Recognition App

A mobile application for recognizing Indonesian traditional fabrics including batik, songket, tenun, and ulos using machine learning, built with Android (Java) frontend and Node.js/TypeScript backend.

## Features

- **Traditional Fabric Recognition**: Camera-based scanning to identify Indonesian traditional fabrics
- **Fabric Gallery**: Browse collection of traditional fabric patterns with detailed information
- **Pattern Details**: Learn about different fabric types and their cultural significance
- **Real-time Processing**: Fast pattern recognition using RF-DETR model via Roboflow

## Tech Stack

### Frontend (Android)
- **Language**: Java
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 35
- **Permissions**: Camera, Storage, Internet

### Backend (API)
- **Runtime**: Node.js
- **Language**: TypeScript
- **Database**: Prisma ORM
- **Framework**: Express.js
- **ML Model**: RF-DETR via Roboflow API

## Project Structure

```
fpbatik/
├── frontend/           # Android application
│   └── app/
│       ├── src/
│       └── build.gradle.kts
├── backend/            # API server
│   ├── src/
│   │   ├── controller/
│   │   ├── service/
│   │   └── router/
│   ├── prisma/
│   └── kain/          # Traditional fabric pattern assets
└── README.md
```

## Installation

### Backend Setup

1. Navigate to backend directory:
```bash
cd backend
```

2. Install dependencies:
```bash
npm install
```

3. Set up database:
```bash
npx prisma generate
npx prisma db push
```

4. Start development server:
```bash
npm run dev
```

### Frontend Setup

1. Open `frontend` directory in Android Studio
2. Sync Gradle files
3. Build and run on device/emulator

## API Endpoints

- `GET /kain/:class` - Get traditional fabric information by pattern class
- Additional endpoints for pattern recognition and data management

## Traditional Fabrics Supported

### Batik Patterns
- Batik Buketan
- Batik Jlamprang  
- Batik Megamendung Cirebon

### Other Indonesian Traditional Fabrics
- Songket
- Tenun
- Ulos

## Development

This project was developed as part of a mobile programming course (PPB - Pemrograman Perangkat Bergerak) focusing on Indonesian cultural heritage preservation through technology.

## License

Educational project - All rights reserved.
