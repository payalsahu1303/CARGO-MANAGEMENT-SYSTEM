
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cargo Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Outfit', sans-serif;
            background-color: #f4f4f9;
        }
        .container {
            max-width: 1000px;
            margin-top: 50px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .left-content {
            width: 55%;
        }
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .btn-custom {
            background-color: #6a0dad;
            color: white;
            border-radius: 8px;
        }
        .btn-custom:hover {
            background-color: #4b0082;
            color: white;
        }
        .right-image {
            width: 40%;
            display: flex;
            justify-content: flex-end;
        }
        .right-image img {
            width: 100%;
            max-width: 400px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Left Side Content (Buttons) -->
        <div class="left-content">
            <h1 class="mb-4 ">Cargo Management System</h1>
            <div class="row g-3">
                <div class="col-md-6">
                    <a href="addContainer.jsp" class="btn btn-custom w-100 py-2">Add Container</a>
                </div>
                <div class="col-md-6">
                    <a href="ContainerServlet" class="btn btn-custom w-100 py-2">View Containers</a>
                </div>
                <div class="col-md-6">
                    <a href="addCargo.jsp" class="btn btn-custom w-100 py-2">Add Cargo</a>
                </div>
                <div class="col-md-6">
                    <a href="CargoServlet" class="btn btn-custom w-100 py-2">View Cargo</a>
                </div>
                <div class="col-md-6">
                    <a href="placeCargo.jsp" class="btn btn-custom w-100 py-2">Place Cargo</a>
                </div>
                <div class="col-md-6">
                    <a href="retrieveCargo.jsp" class="btn btn-custom w-100 py-2">Retrieve Cargo</a>
                </div>
                <div class="col-md-6">
                    <a href="manageWaste.jsp" class="btn btn-custom w-100 py-2">Manage Waste</a>
                </div>
                <div class="col-md-6">
                    <a href="viewLogs.jsp" class="btn btn-custom w-100 py-2">View System Logs</a>
                </div>
                <div class="col-md-12">
                    <a href="simulateDays.jsp" class="btn btn-custom w-100 py-2">Simulate Time</a>
                </div>
            </div>
        </div>

        <!-- Right Side Image -->
        <div class="right-image">
            <img src="images/image.jpg" alt="Astronaut Cargo">
        </div>
    </div>
</body>
</html>
