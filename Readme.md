# 📚 **RentRead: A Rental Management System** 📚

Welcome to **RentRead**, a rental management application designed to manage book rentals and user interactions seamlessly. This application allows users to rent books, track their rentals, and more, all while maintaining a clean and efficient backend system.

---

## 🌟 **Features**

- **User Management:** Manage users, track their rentals, and keep their rental history intact.
- **Book Rentals:** Rent and return books with ease.
- **Book Availability:** Easily track available and rented books with status updates.
- **Data Storage:** All data is securely stored in MySQL, ensuring that users’ data and book inventory are safe.
- **RESTful APIs:** Interact with the application using RESTful APIs for seamless integration with other applications.

---

## 📌 **Technologies Used**

- **Backend:** Spring Boot
- **Database:** MySQL
- **API Interaction:** RESTful APIs
- **ORM:** Spring Data JPA
- **Build Tool:** Gradle

---

## ⚙️ **Setup Instructions**

### Prerequisites

Before running this project, ensure that you have the following installed on your local machine:

- **Java 11** or higher
- **MySQL Database** (preferably MySQL 8.x)
- **Gradle** for building the project
- **IDE:** IntelliJ IDEA.

### 1. Clone the Repository

First, clone this repository to your local machine:

```bash
git clone https://github.com/yourusername/RentRead.git
```

## 2. **Setup the Database** 

### 1. **Create a New Database in MySQL:**
First, create a new database for the project in MySQL:

```sql
CREATE DATABASE rentread_db;
```

### 2. **Update Database Configuration:**
Next, update the database configuration in the `application.properties` file located at `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:mysql://localhost:3306/rentread_db
spring.datasource.username=root
spring.datasource.password=yourpassword

```
### 3. Build and Run
    
    Finally, build and run the project using the following command:
```bash

gradle bootRun

 ```

## 📚 **Endpoints**

### 1. **Get All Books**
- **URL:** `/api/books`
- **Method:** `GET`
- **Response:** List of all books available for rent.

### 2. **Rent a Book**
- **URL:** `/api/rent`
- **Method:** `POST`
- **Request Body:**
```json
{
  "userId": 1,
  "bookId": 101
}
```


## 📊 **Database Schema**
1. Users
   Stores details about users, such as name, email, and rental history.

2. Books
   Contains details about the books available for rent, including title, author, and availability status.

3. Rentals
   Links users to the books they have rented, tracking rental dates and return status.
