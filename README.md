# UCMS - University Course Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Version](https://img.shields.io/badge/version-2.0-blue?style=for-the-badge)
![License](https://img.shields.io/badge/license-MIT-green?style=for-the-badge)
![Status](https://img.shields.io/badge/status-active-success?style=for-the-badge)

**A comprehensive and colorful terminal-based University Course Management System**

👉 [![GitHub Repository](https://img.shields.io/badge/GitHub-Repository-blue?style=for-the-badge&logo=github)](https://github.com/SergiyKochenko/UCMS) 👈 **Click to visit the repository!**

[Features](#features) • [Installation](#installation) • [Usage](#usage) • [Project Structure](#project-structure)

</div>

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Recent Updates](#recent-updates)
- [System Requirements](#system-requirements)
- [Installation](#installation)
- [Usage](#usage)
- [User Roles](#user-roles)
- [Project Structure](#project-structure)
- [UI Components](#ui-components)
- [Validation Features](#validation-features)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

---

## Overview

UCMS (University Course Management System) is a modern, colorful, and user-friendly terminal application designed to streamline academic administration. Built with Java, it features a vibrant ANSI-colored interface that enhances user experience while managing students, courses, instructors, and enrollments.

### Why UCMS?

- ✨ **Colorful Interface** - Beautiful ANSI-colored UI with icons and emojis
- 🛡️ **Robust Validation** - Comprehensive input validation for all user entries
- 🎯 **Role-Based Access** - Different interfaces for Admin, Lecturer, and Student
- 📊 **Data Management** - Efficient handling of academic records
- 🚀 **Easy to Use** - Intuitive menus and clear navigation

[⬆ Back to Table of Contents](#table-of-contents)

---

## Features

### Admin Features
- 👤 **User Management**
  - Add, view, update, and delete students
  - Manage lecturer accounts
  - View all system users
- 📚 **Course Management**
  - Create and manage courses
  - Assign lecturers to courses
  - Manage course modules
  - Set course capacity and credits
- 📊 **Reporting**
  - Generate comprehensive system reports
  - View enrollment statistics
  - Monitor system activity

### Lecturer Features
- 📖 **Course Management**
  - View assigned courses
  - Access student rosters
  - Manage course modules
- 📝 **Grade Management**
  - Input and update student grades
  - View grade distributions
  - Track student performance
- 📧 **Student Interaction**
  - View enrolled students
  - Access student information

### Student Features
- 📚 **Course Enrollment**
  - Browse available courses
  - Enroll in courses
  - Drop courses
  - View course modules
- 📊 **Academic Records**
  - View enrolled courses
  - Check grades
  - Monitor academic progress
- 👤 **Profile Management**
  - Update personal information
  - View academic history

[⬆ Back to Table of Contents](#table-of-contents)

---

## Recent Updates

### ✨ Version 2.0 - Full Implementation Complete (November 2025)

All placeholder TODOs have been fully implemented, making the UCMS system 100% functional:

#### 🎯 Course Management Enhancements
- ✅ **Update Course Details** - Admins can now modify course names and codes
- ✅ **Archive Courses** - Full archiving functionality with confirmation prompts
- ✅ **Course Code Validation** - Enhanced search and validation for course operations

#### 📚 Module Management System
- ✅ **Complete Module Updates** - Update module name, code, level, semester, and credits
- ✅ **Archive Modules** - Remove modules from courses with safety confirmations
- ✅ **Module Navigation** - Seamless navigation between course and module management

#### 👥 Enhanced User Management
- ✅ **Student Operations**
  - Remove students with confirmation
  - Update student information (name, email, phone, address, GPA)
  - Full CRUD operations for student records
  
- ✅ **Lecturer Management**
  - Add new lecturers with department assignments
  - View all lecturers and their assignments
  - Assign lecturers to courses
  - Remove lecturers from the system

- ✅ **Admin Capabilities**
  - Archive user accounts
  - Update user information across all user types
  - Comprehensive course update functionality

#### 🖥️ Role-Based Interfaces
- ✅ **Student Portal**
  - View enrolled courses
  - Check grades and GPA
  - Enroll in new courses
  - Drop courses with confirmation
  
- ✅ **Lecturer Dashboard**
  - View assigned courses
  - Access student rosters
  - View course modules
  - Grade management interface

- ✅ **Admin Console**
  - Complete user management
  - Course and module administration
  - System-wide reporting
  - Full control over all system entities

#### 🔧 Technical Improvements
- Enhanced error handling and validation
- Consistent menu navigation across all modules
- Improved user feedback with success/error messages
- Better data persistence and retrieval
- Modular code structure for maintainability

[⬆ Back to Table of Contents](#table-of-contents)

---

## System Requirements

- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Terminal**: Any terminal with ANSI color support
  - Windows 10+ (Command Prompt, PowerShell, Windows Terminal)
  - macOS Terminal
  - Linux Terminal
- **IDE** (Optional): IntelliJ IDEA, Eclipse, or VS Code with Java extensions

[⬆ Back to Table of Contents](#table-of-contents)

---

## Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/UCMS.git
cd UCMS
```

### Step 2: Compile the Project

```bash
javac -d out src/**/*.java src/*.java
```

### Step 3: Run the Application

```bash
java -cp out Main
```

### Alternative: Using IntelliJ IDEA

1. Open IntelliJ IDEA
2. Select **File → Open** and navigate to the project folder
3. Ensure JDK 8+ is configured in **File → Project Structure**
4. Right-click on `Main.java` and select **Run 'Main.main()'**

[⬆ Back to Table of Contents](#table-of-contents)

---

## Usage

### Starting the Application

When you run UCMS, you'll be greeted with a colorful ASCII art banner:
```
============================================================
                    PICK YOUR ROLE
============================================================
    1. Admin
    2. Lecturer
    3. Student
    4. Exit
************************************************************
Enter your choice: 
```

### Admin Workflow
1. Select **Admin** role
2. Enter login credentials (firstname, lastname, email, phone, password)
3. Access Admin menu:
   - Manage Courses
   - Manage Students
   - Manage Lecturers
   - Generate Reports

### Sample Report Output
```
============================================================

                   UCMS SYSTEM REPORT

============================================================
Total Number of Courses           : 10
Total Number of Students Enrolled : 500
Total Number of Lecturers         : 50
------------------------------------------------------------
Report generated by: Admin Name
Date: 08-Nov-2025
============================================================
```

[⬆ Back to Table of Contents](#table-of-contents)

---

## User Roles

- **Admin**: Full access to all system features, including user and course management, and reporting.
- **Lecturer**: Access to courses they teach, ability to manage grades, and communicate with students.
- **Student**: Access to enrolled courses, ability to check grades, and manage personal information.

[⬆ Back to Table of Contents](#table-of-contents)

---

## Project Structure

```
SoftDev_UCMS/
│
├── .idea/                          # IntelliJ IDEA project settings
├── assets/
│   └── Exercise_2.pdf             # Project specification document
│
├── out/                            # Compiled class files
│
├── src/
│   ├── CourseManagement/
│   │   ├── Course.java            # Course entity and management
│   │   ├── CourseManagementModule.java
│   │   ├── Module.java            # Course module entity
│   │   └── ModuleManagement.java  # Module operations
│   │
│   ├── Interfaces/
│   │   ├── Assignable.java        # Interface for assignable entities
│   │   ├── Authenticatable.java   # Interface for authentication
│   │   ├── Displayable.java       # Interface for displayable entities
│   │   └── Searcheable.java       # Interface for searchable entities
│   │
│   ├── UserManagement/
│   │   ├── Admin.java             # Admin user class
│   │   ├── Lecturer.java          # Lecturer user class
│   │   ├── LecturerManagementModule.java
│   │   ├── Student.java           # Student user class
│   │   ├── StudentManagementModule.java
│   │   └── User.java              # Base user class
│   │
│   ├── Utilities/
│   │   └── Utility.java           # Utility functions and helpers
│   │
│   ├── HelperFunctions.java       # General helper functions
│   ├── Main.java                  # Application entry point
│   ├── UCMS.java                  # Main system controller
│   ├── UIHelper.java              # UI utility functions
│   └── UserRoles.java             # User role definitions
│
├── .gitignore                      # Git ignore file
├── README.md                       # This file
├── README.pdf                      # PDF version of README
└── SoftDev_UCMS.iml               # IntelliJ IDEA module file
```

[⬆ Back to Table of Contents](#table-of-contents)

---

## UI Components

### UIHelper Class
The `UIHelper.java` file provides utility functions for creating a colorful terminal interface:
- **ANSI Color Codes**: Utilizes ANSI escape codes for vibrant colors and text formatting
- **Icons and Emojis**: Enhances visual appeal and user guidance
- **Formatted Menus**: Creates consistent and attractive menu displays
- **Headers and Dividers**: Provides visual separation and structure

### Key UI Features
- Color-coded messages (success in green, errors in red, warnings in yellow)
- Formatted tables for displaying data
- Progress indicators and status messages
- Clear and intuitive navigation prompts

[⬆ Back to Table of Contents](#table-of-contents)

---

## Validation Features

### Input Validation (Utility.java)
- **Email Validation**: Ensures valid email format
- **Phone Number Validation**: Checks for proper phone number format
- **Password Strength**: Validates password complexity requirements
- **Numeric Input**: Validates integer and numeric inputs
- **Date Validation**: Ensures valid date formats
- **Range Checks**: Validates values within acceptable ranges

### Error Handling
- Graceful handling of invalid inputs
- User-friendly error messages
- Input retry mechanisms
- Exception handling throughout the application
- Confirmation prompts for critical actions (e.g., deletions)

[⬆ Back to Table of Contents](#table-of-contents)

---

## Contributing

We welcome contributions! Please follow these guidelines:

1. **Fork the repository**
2. **Create a feature branch**: `git checkout -b feature/amazing-feature`
3. **Commit changes**: `git commit -m 'Add amazing feature'`
4. **Push to branch**: `git push origin feature/amazing-feature`
5. **Open a Pull Request`

### Development Guidelines

- Follow Java coding conventions and best practices
- Use meaningful variable and method names
- Write clear comments for complex logic
- Test all features thoroughly before submitting
- Update documentation for any changes
- Ensure code compiles without errors or warnings

### Code Style
- Use proper indentation (4 spaces)
- Follow Object-Oriented Programming principles
- Implement proper encapsulation
- Use interfaces where appropriate
- Write clean, maintainable code

[⬆ Back to Table of Contents](#table-of-contents)

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

[⬆ Back to Table of Contents](#table-of-contents)

---

## Contact

For questions or feedback regarding this implementation:
- Review the code comments for detailed explanations
- Check the [exercise specification document](assets/Exercise_2.pdf)
- Refer to course materials for OOP concepts

[⬆ Back to Table of Contents](#table-of-contents)

---

<div align="center">

**Made with ❤️ for academic excellence**

</div>


