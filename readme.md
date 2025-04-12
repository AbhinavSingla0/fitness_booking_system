🧘‍♀️ Fitness Booking System

This is a simple in-memory fitness class booking system built in Java using Spring Boot (though Spring is not heavily used here). It demonstrates basic OOP concepts and service interaction without persistence or REST controllers — ideal for simulations or as a base for future enhancements.

📦 Features

Register and login users with different membership tiers (Silver, Gold, Platinum).
Create and manage fitness classes (Yoga, Cardio, Dance, etc.).
Users can book available classes or be added to a waitlist if full.
Waitlist users are automatically promoted when a booking is cancelled.
Enforces booking limits based on user tier.
Prevents double booking and handles edge cases like too-late-to-cancel.
🛠️ Tech Stack

Java 17+
JUnit 5 for testing
Spring Boot (structure only, no web layer)
Pure in-memory logic — no DB or external services
🚀 How It Works

UserService manages users and login.
ClassService manages creation and cancellation of fitness classes.
BookingService handles booking, waitlisting, and cancellations.
Main application logic is run in the main() method under FitnessBookingSystemApplication.
💡 Example Flow:

Alice (Platinum) and Bob (Silver) register.
A Yoga class with capacity 1 is created.
Alice books first, Bob is waitlisted.
Alice cancels — Bob is promoted to booked!
✅ Running Tests

Tests cover:

Booking behavior and waitlist promotion
User tier limits
Class creation and cancellation
Edge cases (already booked, booking too late, etc.)
Run tests with: mvn test

📁 Project Structure

src ├── main │ ├── java │ │ └── com.phonepe.fitness_booking_system │ │ ├── model │ │ ├── service │ │ └── utils │ └── resources ├── test │ └── java │ └── com.phonepe.fitness_booking_system

