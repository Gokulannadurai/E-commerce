<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; padding: 50px; }
        .error-container { display: inline-block; text-align: left; }
        h1 { color: #d9534f; }
        p { font-size: 1.2em; }
        .status-code { color: #777; }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>An Error Occurred</h1>
        <p class="status-code">${statusCode}</p>
        <p>${message}</p>
        <a href="/">Go to Homepage</a>
    </div>
</body>
</html> 