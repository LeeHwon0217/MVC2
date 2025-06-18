<%--
  Created by IntelliJ IDEA.
  User: 훤
  Date: 2025-06-16
  Time: 오전 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판 목록</title>
</head>
<body>
    <h2>게시판</h2>
    <p><a href="write.board">[글쓰기]</a></p>
    <table border="1" width="100%">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>작성일</th>
        </tr>
        <c:forEach var="board" items="${boardList}">
        <tr>
            <td>${board.idx}</td>
            <td><a href="detail.board?idx=${board.idx}">${board.title}</a></td>
            <td>${board.userId}</td>
            <td>${board.viewCount}</td>
            <td>${board.regDate}</td>
        </tr>
        </c:forEach>
    </table>
    <hr>
    <!-- pagination -->
    <c:forEach begin="1" end="${totalPage}" var="i">
        <a href="list.board?page=${i}">[${i}]</a>
    </c:forEach>
</body>
</html>
