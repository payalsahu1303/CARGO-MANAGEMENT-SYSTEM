
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Waste</title>
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
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card p-4 text-center">
            <h2>Manage Waste</h2>
            <p>Click the button below to remove expired or used-up items.</p>
            <form action="WasteManagementServlet" method="POST">
                <button type="submit" class="btn btn-custom w-100">Check & Remove Expired Items</button>
            </form>
            <div class="text-center mt-3">
                <button class="btn btn-secondary" onclick="history.back()">Back</button>
            </div>
        </div>
    </div>
</body>
</html>