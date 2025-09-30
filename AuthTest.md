# Authentication Testing Guide

This project demonstrates how to integrate a **custom `UserDetailsService`** with a **`PasswordEncoder` (BCrypt)** in Spring Security.

---

## 🔹 How Authentication Works

1. User submits login form at `/login` with **username + raw password**.
2. Spring Security passes these credentials to the **`AuthenticationManager`**.
3. `DaoAuthenticationProvider` asks your **`CustomUserDetailService`** to load the user by username.
    - If the user does not exist → `UsernameNotFoundException`.
4. If the user exists:
    - Spring retrieves the **stored encoded password** (`$2a$...` BCrypt hash).
    - Calls `PasswordEncoder.matches(rawPassword, encodedPassword)` internally.
    - If passwords match → authentication succeeds.
    - Otherwise, → authentication fails with **Bad credentials**.

---

## 🔹 Demo Users

Two demo users are pre-configured inside `CustomUserDetailService`:

| Username | Password (raw) | Role  |
|----------|----------------|-------|
| alice    | password       | USER  |
| admin    | admin          | ADMIN |

⚠️ Passwords are stored **encoded with BCrypt** in memory.

---

## 🔹 How to Test

### 1. Start the application

```bash
mvn spring-boot:run

Go to: http://localhost:8080/login