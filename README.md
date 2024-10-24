# Androidlytics

A testing application designed to demonstrate Firebase Analytics, Crashlytics, and Events integration in Android applications.

## Overview

Androidlytics is a simple yet effective testing ground for Firebase services implementation. The app provides a controlled environment to test various Firebase features including event tracking, crash reporting, and analytics.

## Features

### Counter Module
- Incremental counter with visual display
- Tracks user interaction events
- Demonstrates basic Analytics event logging

### Exception Testing
- Generates controlled exceptions
- Demonstrates exception handling and reporting
- Integrates with Firebase Crashlytics for error tracking

### Crash Testing
- Simulates app crashes
- Demonstrates fatal error reporting
- Tests Crashlytics crash reporting capabilities

## Technical Specifications

### Built With
- Kotlin
- Jetpack Compose
- Material Design 3
- Firebase SDK

### Architecture & Design
- Single activity architecture
- Composable-based UI components
- Resource-based string management for localization
- Centralized error handling

### Firebase Integration
- Analytics for user interaction tracking
- Crashlytics for crash and exception reporting
- Custom events for detailed analytics

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/cklabs/androidlytics/
│   │   │   ├── MainActivity.kt
│   │   │   └── ui/theme/
│   │   └── res/
│   │       ├── values/
│   │       │   └── strings.xml
│   │       ├── values-pt/
│   │       │   └── strings.xml
│   │       └── drawable/
│   │           └── firebase_logo.png
```

## Features Implementation

### UI Components
- Title section with Firebase branding
- Centralized container for all testing modules
- Material Design-based components
- Responsive layout design

### Localization
- Multi-language support
- Currently supported languages:
  - English (default)
  - Portuguese

## Setup Instructions

1. Clone the repository
2. Add your `google-services.json` file to the app directory
3. Sync project with Gradle files
4. Run the application

## Firebase Configuration

(To be completed after Firebase integration)

## Development

### Prerequisites
- Android Studio Arctic Fox or newer
- JDK 11 or newer
- Android SDK 27 or newer

### Build Configuration
- minSdk: 27
- targetSdk: (latest)
- Kotlin DSL build scripts

## Testing

(To be added with test implementation)

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details

## Acknowledgments

- Firebase team for their excellent documentation
- Jetpack Compose team for the modern Android UI toolkit
- Material Design team for the design system

## To Do

- [ ] Implement Firebase Analytics
- [ ] Implement Crashlytics
- [ ] Add custom events tracking
- [ ] Add unit tests
- [ ] Add UI tests
- [ ] Expand language support
- [ ] Add detailed analytics documentation

## Contact

Your Name - [@yourtwitter](https://twitter.com/yourtwitter) - email@example.com

Project Link: [https://github.com/yourusername/androidlytics](https://github.com/yourusername/androidlytics)