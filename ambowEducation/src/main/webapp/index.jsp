<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8"/>
</head>
<body>
<h2>Hello World!</h2>

<form action="${pageContext.request.contextPath}/api/v1/tutor/addStudents" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile">
    <input type="submit">
</form>
</body>
</html>
