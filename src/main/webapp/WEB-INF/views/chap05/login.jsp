<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .login-form {
width: 350px;
margin: 0 auto;
background: #fff;
padding: 30px;
border-radius: 5px;
box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
}

h2 {
text-align: center;
margin-bottom: 20px;
}

input[type="text"],
input[type="password"] {
width: 100%;
padding: 10px;
border: 1px solid #ddd;
border-radius: 5px;
margin-bottom: 20px;
}

button[type="submit"] {
width: 100%;
background: #4CAF50;
color: #fff;
border: none;
border-radius: 5px;
padding: 10px;
margin-top: 10px;
font-size: 16px;
}


 </style>
</head>
<body>
   
    <div class="login-form">
        <h2>Login</h2>
        <form action="/board/login" method="post">
          <input type="text" name="id" placeholder="ID">
          <input type="password" name="pwd" placeholder="Password">
          <button type="submit">Sign In</button>
        </form>
      </div>
      
      
</body>
</html>