<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>System Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
  <c:set var="contextPath" value="${pageContext.request.contextPath}" />

  <div class="container">
    <form action="${contextPath}/login" method="post">
      <div class="form-group">
        <label for="username">Username:</label> <input type="text"
          class="form-control" placeholder="Enter username" name="uname">
      </div>
      <div class="form-group">
        <label for="password">Password:</label> <input type="password"
          class="form-control" placeholder="Enter password" name="pwd">
      </div>
      <button type="submit" class="btn btn-primary">Login</button>
    </form>
  </div>

</body>
</html>