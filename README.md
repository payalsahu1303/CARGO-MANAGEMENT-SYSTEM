# Cargo Management System for Space Station

## Overview
The **Cargo Management System** A web-based platform for managing cargo in space stations, using Java Servlets and MySQL. Deployed using Docker.

## Features
- **Cargo Management**:
  - Add new cargo items with details such as weight, category, and expiry date.
  - View a list of stored cargo items with filtering options.
- **Container & Placement Logic**:
  - Assign cargo items to appropriate storage containers based on weight and type.
  - Optimize container utilization.
- **Cargo Retrieval & Waste Management**:
  - Retrieve cargo when needed.
  - Remove expired cargo and manage waste disposal.
- **System Logs (Audit Actions)**:
  - Track all cargo-related operations for auditing purposes.
- **Time Simulation**:
  - Fast forward days to update the expiry status of cargo items.
- **Modern UI with Navigation**:
  - Built with JSP, Bootstrap, and CSS for an intuitive user experience.
  - Back button for easy navigation between pages.
- **Deployment with Docker**:
  - Containerized setup for easy deployment and scalability.

## Technologies Used
- **Backend**: Java Servlets, JSP, JDBC
- **Database**: MySQL
- **Frontend**: HTML, CSS, Bootstrap, Outfit Font
- **Server**: Apache Tomcat
- **Deployment**: Docker

## Prerequisites
- Java Development Kit (JDK 8+)
- Apache Tomcat Server (9.0+)
- MySQL Database
- MySQL Connector for Java
- Docker (for deployment)

## License
This project is licensed under the MIT License.
