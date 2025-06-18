<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글수정</title>
</head>
<body>
<h2>글수정</h2>
<form method="post" action="editForm.board">
    <input type="hidden" name="idx" value="${board.idx}">
    <p>제목: <input type="text" name="title" value="${board.title}"></p>
    <p>내용: <textarea name="content" rows="5" cols="50">${board.content}</textarea></p>
    <p><button type="submit">수정하기</button></p>
</form>
<p><a href="list.board">목록</a></p>
</body>
</html>