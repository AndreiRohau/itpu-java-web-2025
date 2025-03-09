<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>JSTL Demo</title>
</head>
<body>
<h1>JSTL Example</h1>

<p> ${requestScope.param.name} </p>
<form>
	<label for="name">Enter your name:</label>
	<input type="text" id="name" name="name" />
	<button type="submit">Submit</button>
</form>
</body>
</html>