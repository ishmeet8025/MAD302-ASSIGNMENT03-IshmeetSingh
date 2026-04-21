MAD302 Assignment 3 – Smart Utility App

Name: Ishmeet Singh
Student ID: A00202436
Course: MAD302 Android Development
Assignment: 3

---

Project Title
Smart Utility App

---

Project Description

This Android application demonstrates advanced Android development concepts including asynchronous operations, permission handling, error handling, and basic security practices.

The app allows users to:

* Fetch simulated data asynchronously using Kotlin Coroutines
* Request and use location permissions
* Display current GPS coordinates
* Navigate between two screens using Intents
* Handle errors such as invalid input, permission denial, and data fetching failures

---

Features

1. Async Data Fetching

* Uses Kotlin Coroutines
* Simulates API call with delay
* Displays loading and result states

2. Permission-Based Feature

* Uses Location permission
* Retrieves device GPS coordinates
* Handles permission denial gracefully

3. Error Handling

* Handles network simulation errors
* Handles permission denial
* Validates user input (prevents empty input)

4. Security Practices

* Input validation before processing
* No sensitive data stored in plain text
* Safe error messages shown to user

5. UI and Navigation

* Minimum 2 screens: MainActivity and ResultActivity
* Uses Intent for navigation
* Clean and simple UI layout

---

Technologies Used

* Kotlin
* Android Studio
* Kotlin Coroutines
* Google Location Services
* Intents and Activities

---

Project Structure

* MainActivity.kt – Core logic (async, permissions, navigation)
* ResultActivity.kt – Displays results
* activity_main.xml – Main UI
* activity_result.xml – Result UI
