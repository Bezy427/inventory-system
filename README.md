# 🏪 Inventory Management System (Spring Boot + PostgreSQL + JWT Security)

A modern **Inventory Management System** built with **Spring Boot**, **PostgreSQL**, and **JWT-based Authentication**.  
This system provides robust role-based access control for **Admins**, **Users**, and **Cashiers**, and manages key business entities like **Products**, **Suppliers**, **Categories**, **Sales**, and **Stock Movements**.

---

## 🚀 Features

- 🔐 **JWT Authentication** (Login, Register, Role-based Access)
- 👤 **Role Management** (Admin, User, Cashier)
- 📦 **Product & Category Management**
- 🧾 **Supplier and Customer Management**
- 💰 **Sales and Purchase Tracking**
- 🔄 **Stock Movement Tracking** (IN / OUT logic)
- 🗃️ **PostgreSQL Database Integration**
- 🧱 **Spring Data JPA + Hibernate ORM**
- 🧪 **Secure REST API Endpoints**
- 🐳 **Docker & pgAdmin Support**

---

## 🏗️ Tech Stack

| Layer | Technology |
|-------|-------------|
| **Backend** | Spring Boot 3, Spring Data JPA, Spring Security |
| **Database** | PostgreSQL |
| **Authentication** | JWT (JSON Web Token) |
| **Build Tool** | Maven |
| **Containerization** | Docker & Docker Compose |
| **Database GUI** | pgAdmin 4 |
| **Language** | Java 17+ |

---

---

## Endpoints Example

| Module              | Endpoint                                 | Method                 |
| ------------------- | ---------------------------------------- | ---------------------- |
| **Products**        | `/api/v1/products`                       | GET, POST, PUT, DELETE |
| **Categories**      | `/api/v1/categories`                     | GET, POST, PUT, DELETE |
| **Suppliers**       | `/api/v1/suppliers`                      | GET, POST, PUT, DELETE |
| **Sales**           | `/api/v1/sales`                          | GET, POST              | 
| **Stock Movements** | `/api/v1/stock-movement/in/{productId}`  | POST                   |
| **Stock Movements** | `/api/v1/stock-movement/out/{productId}` | POST                   | 


---

## ⚙️ Installation

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/yourusername/inventory-system.git
cd inventory-system
