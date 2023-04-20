<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<body>

    <div>
        <h2>${s.name}님 성적 정보</h2>
        <ul class="score-list">
            <li>
                # 국어 : ${s.kor}점
            </li>
            <li>
                # 영어 : ${s.eng}점
            </li>
            <li>
                # 수학 : ${s.math}점
            </li>
            <li>
                # 총점 : ${s.total}점
            </li>
            <li>
                # 평균 : ${s.average}점
            </li>
                # 학점 : ${s.grade}점
            </li>
            <li>
            <a class="list-btn" href="/score/list">목록</a>
            </li>
            <li>
                <a href="/score/modify?num=${s.stuNum}">수정</a>
            </li>
        </ul>
    </div>


</body>

</html>