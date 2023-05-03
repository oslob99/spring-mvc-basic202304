<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .login-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #fff;
  padding: 30px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
}

.logo {
  background-image: url('https://maplestory.nexon.net/etc/designs/ms/nx2016-global/images/logo.png');
  background-repeat: no-repeat;
  background-size: 100% auto;
  width: 200px;
  height: 50px;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

label {
  font-weight: bold;
  margin-bottom: 5px;
}

input {
  border: none;
  border-radius: 5px;
  padding: 10px;
  font-size: 16px;
}

button {
  background-color: #ff6400;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px;
  font-size: 16px;
  cursor: pointer;
}

button:hover {
  background-color: #e64c00;
}

</style>
</head>
<body>
    <div class="login-form">
        <div class="logo"></div>
        <form>
          <div class="form-group">
            <label for="id">ID</label>
            <input type="text" id="id" name="id" required>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
          </div>
          <button type="submit">Login</button>
        </form>
      </div>
      
</body>
</html>