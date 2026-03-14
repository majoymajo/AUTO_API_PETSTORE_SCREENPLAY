# PetStore API Automation - Serenity BDD & Screenplay

Professional API test automation project for the [Swagger PetStore](https://petstore.swagger.io) using the modern **Screenplay Pattern** with **Serenity BDD**.

## 📑 Index

- [🚀 Project Overview](#-project-overview)
- [📂 Project Structure](#-project-structure)
- [📋 Prerequisites](#-prerequisites)
- [⚙️ Installation & Setup](#️-installation--setup)
- [Executing Tests](#executing-tests)
- [Reports](#reports)
- [✅ Buenas Prácticas de Programación](#-buenas-prácticas-de-programación)
  - [Design Patterns Implemented](#design-patterns-implemented)
  - [SOLID Principles](#solid-principles)
- [🛠 Tech Stack](#-tech-stack)

## 🚀 Project Overview

This project provides a comprehensive automated testing suite for the PetStore API, covering the complete lifecycle of a "Pet" resource through CRUD (Create, Read, Update, Delete) operations. It is designed to demonstrate high-quality automation standards, emphasizing maintainability, readability, and scalability.

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

## Executing Tests
To run the complete test suite and generate documentation:
```powershell
.\gradlew clean test
```

## Reports
After execution, the project generates several reports:

| Report Type | Path from Project Root | Description |
| :--- | :--- | :--- |
| **Serenity BDD** | `target/site/serenity/index.html` | High-quality living documentation with step-by-step REST logs. |
| **Gradle Test** | `build/reports/tests/test/index.html` | Standard Gradle execution report (pass/fail overview). |
| **JUnit XML** | `build/test-results/test/*.xml` | Machine-readable results for CI/CD integration. |

## ✅ Buenas Prácticas de Programación

Este proyecto se ha desarrollado garantizando estándares de calidad profesionales y buenas prácticas:

- **Código Limpio (Clean Code)**: 
  - Entorno de código completamente libre de comentarios (`//` o `/* */`) dentro de las clases. El código es autoexplicativo por su estructura, fluidez (gracias al patrón Screenplay) e intencionalidad, lo que reduce el ruido visual y facilita el mantenimiento a largo plazo.
- **Nomenclatura Semántica**: 
  - Nombres de variables, métodos y clases han sido estructurados de manera clara, descriptivos y legibles en formato CamelCase, para revelar su propósito real y contexto de negocio, garantizando gran legibilidad sin requerir documentación técnica o comentarios adicionales para entender su comportamiento.

### Design Patterns Implemented
- **Screenplay Pattern**: Decoupled architecture where actors perform specific tasks.
- **Builder Pattern**: Used for constructing complex API requests and tasks.
- **Factory Method**: Implemented in tasks (e.g., `AddPet.withDetails()`) for cleaner DSL.
- **Singleton**: Managed actor lifecycle via `OnStage`.

### SOLID Principles
- **SRP (Single Responsibility)**: Each class has a single purpose (e.g., `AddPet` only handles the creation logic).
- **OCP (Open/Closed)**: New API endpoints can be added by creating new tasks without modifying existing ones.
- **DIP (Dependency Inversion)**: High-level test steps depend on Task abstractions, not low-level RestAssured calls.


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
