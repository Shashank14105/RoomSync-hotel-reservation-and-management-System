# RoomSync - Hotel Reservation & Management System

## Project Overview

RoomSync is a backend-based Hotel Reservation & Management System developed using Spring Boot and MongoDB. The project focuses on managing hotel rooms, customers, and bookings through REST APIs while demonstrating CRUD operations, entity relationships, business logic, and exception handling.

---

## Project Structure

```text
com.roomsync

├── controller
│      RoomController
│      CustomerController
│      BookingController
│
├── service
│      RoomService
│      CustomerService
│      BookingService
│
├── repository
│      RoomRepository
│      CustomerRepository
│      BookingRepository
│
├── model
│      Room
│      Customer
│      Booking
│
├── exception
│      ResourceNotFoundException
│      RoomNotAvailableException
│      BookingOperationException
│      GlobalExceptionHandler
│
└── RoomSyncApplication
```

---

## Entities Given

### Room

* roomNumber
* roomType
* price
* status

### Customer

* name
* email
* phone

### Booking

```text
Booking
 ├── customerId
 ├── roomId
 ├── checkInDate
 ├── checkOutDate
 └── bookingStatus
```

---

## Business Proper Flow

```text
Customer
    ↓
Creates Booking
    ↓
Booking references Room
    ↓
Room becomes BOOKED
    ↓
Customer Check-In
    ↓
Room becomes OCCUPIED
    ↓
Customer Check-Out
    ↓
Room becomes AVAILABLE
```

---

## Room Status Proper Flow

```text
AVAILABLE
    ↓
Create Booking
    ↓
BOOKED
    ↓
Check In
    ↓
OCCUPIED
    ↓
Check Out
    ↓
AVAILABLE
```

---

## Booking Status Proper Flow

```text
CREATE BOOKING
      ↓
CONFIRMED
      ↓
CHECKED_IN
      ↓
CHECKED_OUT
```

### Invalid State Changes

```text
CHECKED_OUT → CANCELLED
CHECKED_OUT → CHECKED_IN
CANCELLED → CHECKED_IN
CONFIRMED → CHECKED_OUT
```

---

## Validation Logic I used

If:

```text
Room Status = BOOKED
```

or

```text
Room Status = OCCUPIED
```

Then:

```text
Room is not available
```

This prevents a room from being assigned to multiple active bookings.

---

## CRUD Operations performed Includes

## CRUD Operations

| Module   | Create | Get All | Get By Id | Update | Delete / Cancel |
| -------- | ------ | ------- | --------- | ------ | --------------- |
| Room     | ✅      | ✅       | ✅         | ✅      | ✅               |
| Customer | ✅      | ✅       | ✅         | ✅      | ✅               |
| Booking  | ✅      | ✅       | ✅         | ✅      | ✅               |

---

## Booking Management Features

* Create Booking
* Update Booking
* Cancel Booking
* Check-In
* Check-Out
* Room Status Management
* Date Validation
* Global Exception Handling

Example:

```java
public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
}
```

This method retrieves all booking records and is useful for hotel administration and booking management.

---

## Exception Handling (A Major Challenge)

Global exception handling is implemented using `@ControllerAdvice`.

### HTTP Status Codes Used

| Status Code | Description               |
| ----------- | ------------------------- |
| 404         | Resource not found        |
| 409         | Room unavailable          |
| 400         | Invalid booking operation |
| 500         | Unexpected server error   |

---

## Issues Faced and Fixes

### Bug 1

```java
if (!booking.getBookingStatus())
```

Issue: `getBookingStatus()` returns a String, not a boolean.

Fix: Compared the booking status using defined constants.

---

### Bug 2

During Check-In, room status was updated to OCCUPIED but booking status was not updated.

Fix:

```text
Booking Status → CHECKED_IN
Room Status → OCCUPIED
```

---

### Bug 3

During Check-Out, room status was updated to AVAILABLE but booking status was not updated.

Fix:

```text
Booking Status → CHECKED_OUT
Room Status → AVAILABLE
```

---

### Bug 4

Booking dates were not validated.

Example:

```text
Check-In  : 25 Jun 2026
Check-Out : 20 Jun 2026
```

Fix:

```java
if (!checkOutDate.isAfter(checkInDate))
```

This ensures the check-out date is always after the check-in date.

---

### Bug 5 (Major Logical Bug)

Double Booking Prevention

A room cannot be booked if its status is already:

```text
BOOKED
or
OCCUPIED
```

This prevents multiple active bookings for the same room.

---

## Future Enhancements

* Authentication & Authorization
* Payment Integration
* Admin Dashboard
* Room Availability Search
* Booking Reports & Analytics

---

## Author

Shashank Kumar

RoomSync - Hotel Reservation & Management System
