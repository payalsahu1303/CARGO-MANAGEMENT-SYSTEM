# Cargo Management System for Space Station – National Space Hackathon 2025

A smart cargo stowage and optimization system designed for space stations. Built using Java Servlets, JSP, and MySQL, and containerized with Docker. This project supports intelligent cargo placement, retrieval, waste management, and time simulation features.

![Screenshot 2025-03-18 214653](https://github.com/user-attachments/assets/d703717c-4a50-4476-ad46-819165c629e1)


## 🚀 Project Features

- **Add & Place Cargo**: Efficiently place containers based on weight, expiry, and type.
- **Retrieve Items**: Quickly locate and retrieve specific items.
- **Waste Management**: Identify expired cargo and simulate safe undocking.
- **System Logs**: Audit logs of all cargo actions.
- **Time Simulation**: Fast-forward simulation to test expiry-based logic.
- **Dockerized**: Fully containerized with Ubuntu + Java + Tomcat stack.

## 📁 Folder Structure

```
/cargo-management-system/
├── Dockerfile
├── README.md
├── src/               # Java Servlets
├── web/               # JSP files, CSS
├── sql/               # Database init script
├── logs/              # System audit logs
```

## 🛠️ Technologies Used

- Java Servlets
- JSP
- MySQL
- Apache Tomcat
- HTML/CSS (Bootstrap + Outfit font)
- Docker (Ubuntu 22.04)

## 🧪 API Endpoints

| Functionality              | Endpoint                          |
|---------------------------|-----------------------------------|
| Placement Recommendation  | `/api/placement`                 |
| Cargo Search & Retrieval  | `/api/search`, `/api/retrieve`   |
| Place Container           | `/api/place`                     |
| Waste Management          | `/api/waste/identify`, `/api/waste/return-plan`, `/api/waste/complete-undocking` |
| Time Simulation           | `/api/simulate/day`              |
| Import/Export             | `/api/import/items`, `/api/import/containers`, `/api/export/arrangement` |
| Logs                      | `/api/logs`                      |

## 🐳 Run with Docker

```bash
# Clone the repository
git clone https://github.com/payalsahu1303/CARGO-MANAGEMENT-SYSTEM.git
cd CARGO-MANAGEMENT-SYSTEM

# Build Docker image
docker build -t cargo-system .

# Run container on port 8000
docker run -p 8000:8080 cargo-system
```

Make sure the application listens on `0.0.0.0`.

## 📜 Authors

- **Payal Sahu** – Developer, UI/UX
- Hackathon: National Space Hackathon 2025

## ✅ Submission Checklist

- [x] Code pushed to GitHub
- [x] Dockerfile included (Ubuntu 22.04)
- [x] Technical Report (see Drive)
- [x] Demo Video (see Drive)

---

## 🔗 Important Links

- **Demo Video**: [Google Drive Link](https://drive.google.com/...)
- **Technical Report**: [Google Drive Link](https://drive.google.com/...)
- **Submission Form**: [https://forms.gle/2vbXR9KgKn6rTXkz6](https://forms.gle/2vbXR9KgKn6rTXkz6)
