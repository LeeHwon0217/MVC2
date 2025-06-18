package com.koreait.mvc2.service;

import com.koreait.mvc2.dao.CommentDAO;
import com.koreait.mvc2.dto.CommentDTO;
import com.koreait.mvc2.dto.MemberDTO;
import com.koreait.mvc2.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommentServiceImpl implements CommentService{
    public CommentServiceImpl() {
        super();
    }

    @Override
    public void write(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        MemberDTO loginUser = (MemberDTO) session.getAttribute("user");
        if(loginUser == null) {
            resp.sendRedirect("login.member");
            return;
        }
        String writer = loginUser.getUserid();
        int boardIdx = Integer.parseInt(req.getParameter("board_idx"));
        String content = req.getParameter("content");
        CommentDTO dto = new CommentDTO();
        dto.setBoardIdx(boardIdx);
        dto.setUserId(writer);
        dto.setContent(content);

        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(true)) {
            new CommentDAO(sqlSession).insertComment(dto);
        }
    }
}
