# LIVEMart

Role-based Android grocery commerce platform for customers, retailers, wholesalers, and administrators.

LIVEMart is a full-stack Android application built with Java, Firebase Authentication, and Firebase Realtime Database. It models a multi-sided grocery marketplace with B2C and B2B flows, role-specific dashboards, inventory moderation, order management, location-aware filtering, OTP authentication, voice-assisted offline reminders, and feedback workflows.

This is not a single-page shopping cart demo. The app implements separate workflows for customers, retailers, wholesalers, and admins, backed by a hierarchical Firebase data model and role-aware UI/navigation logic.

## What It Demonstrates

- Android application architecture with Java and XML layouts
- Firebase Authentication with phone/OTP flows
- Firebase Realtime Database schema for users, products, orders, feedback, and admin flags
- Role-based dashboards for customers, retailers, wholesalers, and administrators
- B2C and B2B order flows across marketplace participants
- Product approval, visibility controls, and moderation workflows
- Location-aware filtering with Google Geocoding APIs
- Cart, orders, delivery details, feedback, and offline reminder flows
- Voice-to-text reminder capture using Google speech APIs

## System Roles

| Role | Capabilities |
|---|---|
| Customer | Browse stores/items, filter by distance and stock, manage cart, place orders, submit feedback |
| Retailer | Source inventory from wholesalers, manage customer transactions, update item availability |
| Wholesaler | Add and manage bulk inventory, fulfill retailer orders |
| Admin | Approve products, hide/unhide listings, moderate marketplace visibility |

## Architecture

```text
Android Java app
    |
    +-- role-aware activities/fragments
    +-- Firebase Auth / OTP
    +-- Google Geocoding
    +-- voice reminders
    |
    v
Firebase Realtime Database
    |
    +-- users
    +-- products
    +-- orders
    +-- feedback
    +-- admin moderation flags
```

## Core Modules

### Authentication and Onboarding

- Role-based registration
- Phone number OTP verification
- Persistent user/session state

### Marketplace Search

- Category and subcategory browsing
- Distance-aware filtering
- Stock availability checks
- Store and item discovery

### Order Management

- Cart-based online ordering
- Unique transaction/order IDs
- Delivery details and cash-on-delivery flow
- Retailer and wholesaler order handling

### Offline Reminder Workflow

- Calendar-based reminders
- Voice input for reminder memos
- Alarm receiver and notification handling

### Admin Moderation

- Product approval
- Listing visibility controls
- Backend flags for hiding/unhiding products

## Firebase Data Model

The database stores:

- users and roles
- product categories and subcategories
- retailer/wholesaler inventory
- cart and order state
- feedback records
- admin moderation flags

Schema visualization:

- [Realtime DB Schema Visualization](https://drive.google.com/file/d/1G5YEXlrrd9oTsYyQz4_6TEx_2MoTLTua/view?usp=sharing)

## Getting Started

### Prerequisites

- Android Studio
- Android emulator or device
- Firebase project
- `google-services.json`

### Setup

```bash
git clone https://github.com/abdulazeem-tk4vr/LIVEMart.git
cd LIVEMart
```

Then:

1. Open the project in Android Studio.
2. Add your Firebase `google-services.json` under `app/`.
3. Sync Gradle.
4. Build and run on an emulator or connected device.

## Project Structure

| Path | Purpose |
|---|---|
| `app/src/main/java/.../Main/Customer` | Customer shopping, cart, and order flows |
| `app/src/main/java/.../Main/Retailer` | Retailer inventory and transaction flows |
| `app/src/main/java/.../Main/Wholesaler` | Wholesaler inventory management |
| `app/src/main/java/.../Main/Admin` | Admin moderation and approval logic |
| `app/src/main/java/.../Main` | Authentication, navigation, reminders, feedback, shared models |

## Contributors

- [Abdul Azeem](https://github.com/abdulazeem-tk4vr) - backend logic, Firebase integration, role-based workflows
- [Anirudh Sundar](https://github.com/Anirudh-Sundar) - UI/UX, XML layouts, navigation
- [Karthik Suresh](https://github.com/karths8) - Firebase schema design, voice API integration
- [Aryan Arora](https://github.com/aryanarora180) - feedback module support and UI testing

Contribution graph:

- https://github.com/abdulazeem-tk4vr/LIVEMart/graphs/contributors

## Additional Resources

- [Detailed Report](https://drive.google.com/file/d/1vJVX2_IoBgb7cZaiypGeQTA6cj_qEzAo/view?usp=sharing)
- [Repository](https://github.com/abdulazeem-tk4vr/LIVEMart)
