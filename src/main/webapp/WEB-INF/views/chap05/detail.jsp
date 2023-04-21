<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/assets/css/list.css">
<head>
	<meta charset="UTF-8">
	<title>게시글 상세보기 페이지</title>
	<style>
		body {
			font-family: sans-serif;
			background-color: #f0f0f0;
		}
		h1 {
			margin-top: 20px;
			margin-bottom: 30px;
			text-align: center;
		}
		.container {
			margin: auto;
			max-width: 600px;
			background-color: #fff;
			padding: 20px;
			border-radius: 5px;
			box-shadow: 0px 0px 5px rgba(0,0,0,0.3);
		}
		.post-info {
			margin-bottom: 20px;
			display: flex;
			justify-content: space-between;
			align-items: center;
			font-size: 16px;
			font-weight: bold;
			color: #777;
		}
		.post-info span {
			margin-right: 10px;
		}
		.post-content {
			font-size: 18px;
			line-height: 1.5;
			padding: 20px;
			border: 1px solid #ccc;
			border-radius: 5px;
		}
	</style>
</head>
<body>
	<h1>게시글 상세보기 페이지</h1>
	<div class="container">
		<div class="post-info">
			<span>${b.title}</span>
			<span>작성일: {b.regDateTime}</span>
		</div>
        <div class="view">
            <i class="fas fa-eye"></i>
            <span class="view-count">${b.viewCount}</span>
        </div>
		<div class="post-content">
			${b.content}
		</div>
	</div>
</body>
</html>

