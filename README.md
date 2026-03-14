# PetStore API Automation - Serenity BDD & Screenplay

Professional API test automation project for the [Swagger PetStore](https://petstore.swagger.io) using the modern **Screenplay Pattern** with **Serenity BDD**.

## 🚀 Project Overview

This project provides a comprehensive automated testing suite for the PetStore API, covering the complete lifecycle of a "Pet" resource through CRUD (Create, Read, Update, Delete) operations. It is designed to demonstrate high-quality automation standards, emphasizing maintainability, readability, and scalability.

## 🛠 Tech Stack

| Component | Technology | Version |
| :--- | :--- | :--- |
| **Language** | Java | 23+ |
| **Build Tool** | Gradle | 8.12.1 |
| **Testing Framework**| Serenity BDD | 4.0.15 |
| **Pattern** | Screenplay | - |
| **BDD Tool** | Cucumber | 7.14.0 |
| **Assertions** | AssertJ / Hamcrest | 3.24.2 / 2.2 |
| **REST Client** | RestAssured | Included in Serenity |

## 📋 Prerequisites

- **Java Development Kit (JDK)**: Version 23 or higher is required.
- **IDE**: IntelliJ IDEA (recommended), Eclipse, or VS Code with Java extensions.
- **Gradle**: The project uses the Gradle Wrapper (`gradlew`), so a global install is not required.

## ⚙️ Installation & Setup

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd AUTO_API_PETSTORE_SCREENPLAY
   ```

2. **Configure Java Home**:
   The project is pre-configured to use JDK 23. If your installation path differs, update `gradle.properties`:
   ```properties
   org.gradle.java.home=C:/Program Files/Java/jdk-23
   ```

3. **Build the Project**:
   ```powershell
   .\gradlew clean build
   ```

## 🏃 Usage

### Executing Tests
To run the complete test suite and generate documentation:
```powershell
.\gradlew clean test
```

### Interpreting Results
After execution, the project generates several reports:

| Report Type | Path from Project Root | Description |
| :--- | :--- | :--- |
| **Serenity BDD** | `target/site/serenity/index.html` | High-quality living documentation with step-by-step REST logs. |
| **Gradle Test** | `build/reports/tests/test/index.html` | Standard Gradle execution report (pass/fail overview). |
| **JUnit XML** | `build/test-results/test/*.xml` | Machine-readable results for CI/CD integration. |

> [!TIP]
> Always use the **Serenity Report** for debugging failures, as it captures the full request and response bodies for every API interaction.

## 🏗 Architecture & Patterns

The project follows the **Screenplay Pattern**, which shifts the focus from "Page Objects" (which can become bloated) to "Actors" who perform "Tasks" and ask "Questions".

```mermaid
graph TD
    subgraph "Screenplay Architecture"
        Actor((Actor: User)) -->|performs| Task[Tasks: Add, Consult, Update, Delete]
        Task -->|uses| Interaction[Interactions: Post, Get, Put, Delete]
        Interaction -->|executes| API((PetStore API))
        
        Actor -->|asks| Question[Questions: ThePetDetails, LastResponseStatusCode]
        Question -->|reads| Response[SerenityRest: lastResponse]
        Response -->|maps to| Model[Models: Pet, Category, Tag]
        
        Task -.->|manages| Model
    end
```

### Design Patterns Implemented
- **Screenplay Pattern**: Decoupled architecture where actors perform specific tasks.
- **Builder Pattern**: Used for constructing complex API requests and tasks.
- **Factory Method**: Implemented in tasks (e.g., `AddPet.withDetails()`) for cleaner DSL.
- **Singleton**: Managed actor lifecycle via `OnStage`.

### SOLID Principles
- **SRP (Single Responsibility)**: Each class has a single purpose (e.g., `AddPet` only handles the creation logic).
- **OCP (Open/Closed)**: New API endpoints can be added by creating new tasks without modifying existing ones.
- **DIP (Dependency Inversion)**: High-level test steps depend on Task abstractions, not low-level RestAssured calls.

## 📂 Project Structure

```text
src
├── main/java/com/petstore
│   ├── models        # Data Transfer Objects (DTOs)
│   ├── questions     # Classes to query system state
│   └── tasks         # Executable business actions
└── test
    ├── java/com/petstore
    │   ├── hooks            # Setup/Teardown logic
    │   └── stepdefinitions  # Cucumber step mappings
    └── resources/features   # Gherkin feature files
```

## 📊 Reporting

Serenity BDD provides rich reporting capabilities, including:
- **Narrative overviews** of test results.
- **Step-by-step execution** details with request/response payloads.
- **Traceability** between requirements (Features) and test results.
