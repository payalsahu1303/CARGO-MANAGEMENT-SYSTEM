
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Cargo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Outfit', sans-serif;
            background-color: #f4f4f9;
        }
        .container {
            max-width: 600px;
            margin-top: 50px;
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
    </style>
</head>
<body>
    <div class="container">
        <div class="card p-4">
            <h2 class="text-center">Add New Cargo</h2>
            <form action="CargoServlet" method="POST">
                <div class="mb-3">
                    <label class="form-label">Cargo Name</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Width</label>
                    <input type="number" name="width" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Depth</label>
                    <input type="number" name="depth" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Height</label>
                    <input type="number" name="height" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Mass (kg)</label>
                    <input type="number" step="0.1" name="mass" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Priority</label>
                    <input type="number" name="priority" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Preferred Zone</label>
                    <input type="text" name="preferredZone" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Usage Limit</label>
                    <input type="number" name="usageLimit" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Expiry Date</label>
                    <input type="date" name="expiryDate" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-custom w-100">Add Cargo</button>
            </form>
            <div class="text-center mt-3">
                <button class="btn btn-secondary" onclick="history.back()">Back</button>
            </div>
        </div>
    </div>
</body>
</html>
