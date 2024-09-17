# UniEventos

## Description

**UniEventos** is a mobile application designed for selling tickets to concerts and events across various cities in Colombia. The platform allows customers to register, purchase tickets, and manage their accounts, while administrators can manage events and discount coupons.

## Features

### For Customers

- **Registration and Authentication**
  - Register with ID number, full name, address, phone number, email, and password.
  - Registration confirmation through a code sent to the email, valid for 15 minutes.
  - Login using email and password.
  - Password recovery via a code sent to the email, valid for 15 minutes.

- **Event Management**
  - Filter events by name, type, and/or city.
  - Select events and seats.
  - Create, review, and cancel purchase orders.
  - Persistent shopping cart across sessions.
  - Apply discount coupons at checkout.
  - View purchase history.
  - Manage personal account (edit and delete information).

### For Administrators

- **Authentication**
  - Login with preconfigured credentials.

- **Event Management**
  - Create, modify, search, list, and delete events.
  - Define events with complete details (name, address, city, description, type, images, date, and seating).

- **Coupon Management**
  - Create discount coupons for special events or dates.

- **Password Recovery**
  - Recover password if forgotten.

## System Requirements

- **Platform:**
  - Mobile application development using Jetpack Compose with Kotlin.
  - Data and image persistence through Firebase.

- **Validations:**
  - Verify seating capacity before completing a purchase.
  - Restrict purchases to a maximum of two days before the event.

## Contribution

The project source code is available on [GitHub](https://github.com/blandoncj/UniEventos/). All team members are required to contribute to the development and maintenance of the project.

## Creators

- **[Jacobo Blandón Castro](https://github.com/blandoncj)**
- **[Johan Antonio Peña López](https://github.com/Johan0425)**

