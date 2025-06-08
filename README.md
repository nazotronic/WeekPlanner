# 🗓️ WeekPlanner

**WeekPlanner** is a desktop application designed to create weekly schedules of employee work hours. It provides an intuitive interface for managers (primarily directors) to manage staff, working days, scheduling rules, constraints, and to generate and approve weekly plans.

---

## 🚀 Features Overview

Upon launching the application, the user is presented with four main options:

### 👥 Employees
- Add and remove employees
- Available settings for each employee:
  - First name, last name, patronymic
  - Physical work capability (yes/no)
  - Total working hours available per month
  - A counter showing how many hours are already used

### 📆 Days (Calendar)
- Configure each day of the week with:
  - Start and end of the workday
  - Minimum and maximum number of employees
  - Whether a meeting is scheduled
  - Meeting start time and duration
  - Whether physical work is required
  - Day status (active/inactive — working or non-working)

### ⚖️ Rules
- Currently available rules:
  - Maximum number of working hours per week
  - Minimum time required between shifts

### 🧩 Create Plan
- When clicking this option, a new `Plan` object is created, copying:
  - All current global employee settings
  - Calendar configuration
- Step-by-step planning:
  1. Adjust employees (local to the plan)
  2. Adjust calendar (local to the plan)
  3. Add **constraints** (e.g. sick leave, vacation)
     - Choose employee
     - Choose day
     - Choose type of constraint
     - For vacation: specify time range (start-end)

### 📋 Final Schedule
- The result is a weekly schedule:
  - Each day shows working hours assigned to each employee

---

## 🛠️ Plan Actions

- 🔙 **Back** — return to the main menu
- 🗑️ **Delete Plan** — cancel and return to start
- ✅ **Approve Plan**:
  - Deduct monthly hours for each employee
  - Save all employees and calendar data to the database
  - Log:
    - Overtime
    - Meeting attendance
    - Sick leave and vacation
    - Days worked during the week
- 📸 **Export as Image** — save the visual schedule as an image file

---

## 💾 Persistence & Logging

- Global data (employees, calendar, approved plans) is saved to a database
- Each employee’s weekly record includes:
  - Days worked
  - Overtime
  - Meeting participation
  - Sick leave and vacation
- All actions in the application are logged to a file via an integrated logger

---

## 🧪 Testing

- The application includes unit tests for core planning logic
- ✅ **Test coverage > 90%**
- Designed for **positive, stable tests** only
- Tests do not mock the database and do not rely on GUI frameworks

---

## 📸 Screenshots

> _Coming soon

---

## 📦 Installation & Run

> _Coming soon

---

## ✅ License

This project is proprietary. All rights reserved by the author.
