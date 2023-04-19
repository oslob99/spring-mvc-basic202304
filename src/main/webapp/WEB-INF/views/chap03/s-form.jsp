<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>로그인하기 ~</h1>
<form action="/hw/s-login-check" method="post">
# 아이디 : <input type="text" name="id">
# 비밀번호 : <input type="text" name="pwd">
<button type="submit">로그인</button>
</form>


</body>
</html>