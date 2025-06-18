<%--
  Created by IntelliJ IDEA.
  User: 훤
  Date: 2025-06-16
  Time: 오후 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>글 상세</title>
</head>
<body>
    <h2>${board.title}</h2>
    <p>작성자: ${board.userId} | 조회수: ${board.viewCount} | 작성일: ${board.regDate}</p>
    <hr>
    <p>${board.content}</p>
    <hr>
    <a href="edit.board?idx=${board.idx}">수정</a>
    <a href="delete.board?idx=${board.idx}">삭제</a>
    <a href="list.board">목록</a>
    <hr>
    <!-- 댓글 목록 영역 -->
    <h3>댓글 작성</h3>
    <form action="write.comment" method="post">
        <input type="hidden" name="board_idx" value="${board.idx}">
        <p><input type="text" name="user_id" value="${user.userid}" readonly>:
        <input type="text" name="content" placeholder="댓글 내용을 입력하세요" required>
        <button type="submit">댓글 등록</button></p>
    </form>
    <hr>
    <c:forEach var="comment" items="${commentList}" >
        <div>
            <strong>${comment.userId}: ${comment.content}</strong>
            (${comment.regDate})
            <button>수정</button> <button>삭제</button>
        </div>
    </c:forEach>

</body>
</html>
