<%--
  Created by IntelliJ IDEA.
  User: 훤
  Date: 2025-06-16
  Time: 오전 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글쓰기</title>
</head>
<body>
    <h2>글쓰기</h2>
    <form method="post" action="writeForm.board">
      <p>제목: <input type="text" name="title"></p>
      <p>내용: <textarea name="content" rows="5" cols="50"></textarea></p>
      <p><button type="submit">작성하기</button></p>
    </form>
    <p><a href="list.board">목록</a></p>
</body>
</html>
