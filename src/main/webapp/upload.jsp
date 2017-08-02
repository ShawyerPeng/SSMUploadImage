<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="pictureFile">
    <input type="text" name="description">描述
    <button type="submit">提交</button>
</form>
</body>
</html>
