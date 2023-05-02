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
				box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.3);
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
			.comment-form {
  margin-bottom: 20px;
}

.comment-form textarea {
  width: 100%;
  height: 100px;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  resize: none;
}

.comment-form button[type="submit"] {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.comment-list {
  border-top: 1px solid #ccc;
}

.comment {
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.comment-author {
  font-weight: bold;
}

.comment-date {
  color: #888;
}

.comment-body {
  line-height: 1.5;
}
		</style>
	</head>

<body>
	<h1>게시글 상세보기 페이지</h1>
	<div class="container">
		<div class="post-info">
			<span>${b.title}</span>
			<span>작성일: ${b.date}</span>
		</div>
		<div class="view">
			<i class="fas fa-eye"></i>
		</div>
		<div class="post-content">
			${b.content}
		</div>
		<div class="comment-form">
			<form action="/board/reply" method="post">
				<input type="hidden" name="pageNo" value="${s.pageNo}">
				<input type="hidden" name="type" value="${s.type}">
				<input type="hidden" name="keyword" value="${s.keyword}">
				<input type="hidden" name="boardNo" value="${b.boardNo}">
				<textarea name="replyText" placeholder="댓글을 입력하세요"></textarea>
				<button type="submit">댓글 등록</button>
			</form>
		</div>
		<div class="comment-list">
			<div class="comment">
				<c:forEach var="r" items="${rList}">
					<div class="comment-header">
						<span class="comment-author">${r.replyWriter}</span>
						<span class="comment-date">${r.replyDate}</span>
					</div>
					<div class="comment-body">
						${r.replyText}
					</div>
				</c:forEach>
			</div>
			<!-- 댓글이 추가될 때마다 위의 구조를 반복해서 생성 -->
		</div>
		<button class="list-btn" type="button"
			onclick="window.location.href='/board/list?pageNo=${s.pageNo}&type=${s.type}&keyword=${s.keyword}'">뒤로가기</button>
		<button class="list-btn" type="button"
			onclick="window.location.href='/board/modify?bno=${b.boardNo}'">수정</button>
	</div>
</body>

</html>