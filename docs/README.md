# RaisinChat 🍇

![Java](https://img.shields.io/badge/Java-17-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-17-blue)
![Build](https://img.shields.io/badge/build-passing-brightgreen)

**RaisinChat** is a desktop application for managing tasks, optimized for users who prefer a Command Line Interface (CLI) experience but want the visual benefits of a Graphical User Interface (GUI).
![Product Screenshot](docs/Ui.png)
> **Note:** This project is currently in active development.

---

## 📖 Table of Contents
- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Data Storage](#data-storage)
- [Testing](#testing)

---

## About
RaisinChat helps you keep track of your daily tasks, deadlines, and events. It bridges the gap between a text-based chatbot and a modern desktop application.

## Features

* **GUI Interface:** A clean, responsive interface built with JavaFX.
* **Task Management:** Add, delete, and list various types of tasks.
* **Persistent Storage:** Automatically saves your tasks to the hard drive so you never lose data.
* **Smart Parsing:** Understands natural language commands (e.g., "deadline submit report /by Monday").

---

## Getting Started

### Prerequisites
Ensure you have the following installed on your machine:
* **JDK 11** or higher (JDK 17 recommended)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Recommended IDE)

### Installation
1.  **Clone the repository**
    ```bash
    git clone [https://github.com/RaisinMoldyBread/RaisinChat.git](https://github.com/YOUR_USERNAME/RaisinChat.git)
    ```
2.  **Open in IntelliJ IDEA**
    * File > Open > Select the `RaisinChat` folder.
    * Let Gradle/Maven sync the dependencies.

3.  **Run the Application**
    * Locate the `src/main/java/raisinchat/Main.java` file.
    * Right-click and select `Run 'Main.main()'`.

---

## Usage

Once the application launches, you can interact with RaisinChat using the text input box.

### Common Commands

| Action | Command Format | Example |
| :--- | :--- | :--- |
| **Add Todo** | `todo <description>` | `todo Buy grapes` |
| **Add Deadline** | `deadline <desc> /by <date>` | `deadline Return book /by 2024-02-20` |
| **Add Event** | `event <desc> /from <start> /to <end>` | `event Meeting /from 2pm /to 4pm` |
| **List Tasks** | `list` | `list` |
| **Mark Done** | `mark <task_index>` | `mark 1` |
| **Delete Task** | `delete <task_index>` | `delete 3` |
| **Exit** | `bye` | `bye` |

*(Note: Replace the commands above with the specific syntax your Parser logic supports if different)*

---

## Data Storage

RaisinChat automatically saves your data to a local file.
* **File Location:** `./data/RaisinChatTaskDb.txt`
* **Behavior:** The file is updated after every command that modifies the task list. If the file does not exist, it will be created automatically on startup.

---

## Testing

We use JUnit for testing. The project includes a `TestFixtures` class to provide consistent objects for unit tests.

To run tests:
1.  Open the **Gradle** (or Maven) tab in your IDE.
2.  Navigate to `Tasks` > `verification`.
3.  Double-click `test`.

The tests use a separate database file (`./data/RaisinChatTaskTestDb.txt`) to ensure your actual data is not overwritten during testing.

---

## Contact
Created by **RaisinMoldyBread** - feel free to contact me at **dylankoh@u.nus.edu**!