# Grocery Shop eCommerce Android Application  
**(Java + Firebase Realtime Database)**

**LIVEMart** is a full-stack Android-based mobile eCommerce platform enabling **B2C** and **B2B** grocery transactions between **Customers**, **Retailers**, and **Wholesalers**. Built with **Java (Android SDK)** and **Firebase**, the app supports real-time syncing, role-based dashboards, offline reminders via speech input, and product moderation via admin controls.

📄 [Detailed Report](https://drive.google.com/file/d/1vJVX2_IoBgb7cZaiypGeQTA6cj_qEzAo/view?usp=sharing)  
🔗 [GitHub Repository](https://github.com/abdulazeem-tk4vr/LIVEMart)

---

## Table of Contents

- [System Overview](#system-overview)
- [Architecture & Stack](#architecture--stack)
- [Core Modules & Features](#core-modules--features)
- [Firebase Schema](#firebase-schema)
- [Getting Started](#getting-started)
- [Resources](#resources)
- [Contributors](#contributors)

---

## System Overview

LIVEMart simulates a real-world grocery eCommerce flow with three main user roles:

- 🧍 **Customers** place orders for household groceries.
- 🧑‍💼 **Retailers** source items in bulk from wholesalers and sell to customers.
- 🏬 **Wholesalers** add and manage high-volume stock, servicing retailers.

The application supports both **online transactions** (with delivery tracking) and **offline orders** via **calendar reminders** and **voice input** using the Google Voice API.

---

## Architecture & Stack

### 📦 Backend
- **Firebase Realtime Database** (NoSQL, hierarchical JSON tree)
- **Firebase Authentication** (email/password + OTP)
- Admin controls for inventory visibility (approve/hide products)

### 📱 Frontend
- **Java** (Android SDK)
- **XML** for UI layouts
- **Material Design** principles for UI consistency
- **Google Geocoding API** for location-based filtering
- **Voice Recognition API** for offline reminder memos

### 🧰 Development Tools
- **Android Studio** (Gradle-based build system)
- **Canva** for layout prototyping and visual design

---

## Core Modules & Features

### 🔐 Registration & OTP Authentication
- Role-based sign-up: Customer, Retailer, Wholesaler
- Phone number OTP verification via Firebase Auth
- Google Geocoding API to capture latitude/longitude

### 📊 Dashboards
- Role-specific dashboards:
  - **Customer**: Browse items, filter by distance/stock, view cart/orders
  - **Retailer**: Purchase from wholesalers, manage customer transactions
  - **Wholesaler**: Add/update inventory, fulfill retailer orders

### 🔎 Category & Product Search
- Filter by:
  - **Distance** (via user-entered km radius)
  - **Stock availability**
  - **Retailer/Wholesaler proximity**
- Dynamic UI with distance and stock validation warnings

### 🛒 Order Management
- **Online orders**:
  - Cart-based ordering with unique order IDs
  - Delivery details, cash-on-delivery, and feedback submission
- **Offline orders**:
  - Set future reminders using **calendar + voice input**
  - Voice-to-text conversion via Google Speech API

### 🗣 Feedback & Queries
- Product-specific feedback stored in Firebase
- Displayed to other users while browsing the item

### 🛠 Admin Approval System
- Admin can:
  - Approve new products
  - Temporarily hide/unhide listings
- Controlled via backend flags in Firebase

---

## Firebase Schema

The app uses a hierarchical JSON schema for:
- Users (with roles)
- Products (per category/store)
- Orders (with unique transaction IDs)
- Feedback
- Admin flags for moderation

📂 [Realtime DB Schema Visualization](https://drive.google.com/file/d/1G5YEXlrrd9oTsYyQz4_6TEx_2MoTLTua/view?usp=sharing)

🔍 Use [CodeBeautify JSON Viewer](https://codebeautify.org/jsonviewer) to visualize the schema from the exported `.json` tree.

---

## Getting Started

### 🛠 Prerequisites

- Android Studio (latest)
- Android device/emulator (API 28+)
- Firebase account and `google-services.json`

### 🚀 Setup

1. Clone this repository
2. Import it into Android Studio
3. Sync Gradle and build the project
4. Run on an emulator or connected device

---

## Resources

- [CodePath Android Guides](https://github.com/codepath/android_guides)  
- [Android Roadmap](https://github.com/coder2hacker/Android-Development-RoadMap)  
- [Udacity Android Basics](https://classroom.udacity.com/courses/ud9012)  
- [Android Studio Setup](https://www.youtube.com/watch?v=FeKfIWJyQMs)  
- [Running First App](https://www.youtube.com/watch?v=13DPpfuP1Zs)

---

## Contributors

- [**Abdul Azeem**](https://github.com/abdulazeem-tk4vr) – Backend, Firebase, Role-based logic  
- [**Anirudh Sundar**](https://github.com/Anirudh-Sundar) – UI/UX, XML layouts, navigation  
- [**Karthik Suresh**](https://github.com/karths8) – Firebase schema design, voice API integration

### 🙌 Special Mention
- [**Aryan Arora**](https://github.com/aryanarora180) – Feedback module support and UI testing

📈 [Contribution Graph](https://github.com/abdulazeem-tk4vr/LIVEMart/graphs/contributors)

---

> 💬 For questions, contact **sabdulazeem01@gmail.com** | **https://www.linkedin.com/in/abdulazeem-shaik/**
