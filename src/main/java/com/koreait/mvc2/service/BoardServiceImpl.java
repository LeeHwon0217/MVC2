package com.koreait.mvc2.service;

import com.koreait.mvc2.dao.BoardDAO;
import com.koreait.mvc2.dao.CommentDAO;
import com.koreait.mvc2.dto.BoardDTO;
import com.koreait.mvc2.dto.CommentDTO;
import com.koreait.mvc2.dto.MemberDTO;
import com.koreait.mvc2.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardServiceImpl implements BoardService{
    public BoardServiceImpl() {
        super();
    }

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int pageSize = 10;
        if(req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int start = (page - 1) * pageSize;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            BoardDAO dao = new BoardDAO(sqlSession);
            Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("start", start);
                paramMap.put("pageSize", pageSize);

            List<BoardDTO> list = dao.getBoardList(paramMap);
            int totalCount = dao.getTotalCount();
            int totalPage = (int) Math.ceil((double) totalCount / (double) pageSize);
            req.setAttribute("boardList", list);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPage", totalPage);
            req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
        }
    }

    @Override
    public void write(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberDTO sessionUser = (MemberDTO) session.getAttribute("user");
        if (sessionUser == null) {
            resp.sendRedirect("login.member");
            return;
        }
        String writer = sessionUser.getUserid();
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        BoardDTO dto = new BoardDTO();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setUserId(writer);

        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(true)){
            BoardDAO dao = new BoardDAO(sqlSession);
            dao.insertBoard(dto);
        }
        resp.sendRedirect("list.board");
    }

    @Override
    public void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idx = Integer.parseInt(req.getParameter("idx"));

        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(true)) {
            BoardDAO dao = new BoardDAO(sqlSession);
            CommentDAO commentDao = new CommentDAO(sqlSession);

            BoardDTO board = dao.getBoard(idx);
            List<CommentDTO> commentList = commentDao.getCommentsByBoardIdx(idx);

            dao.increaseViewCount(idx);
            req.setAttribute("board", board);
            req.setAttribute("commentList", commentList);

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/detail.jsp");
            rd.forward(req, resp);
        }catch(Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idx = Integer.parseInt(req.getParameter("idx"));
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(true)) {
            BoardDTO dto = new BoardDAO(sqlSession).getBoard(idx);
            req.setAttribute("board", dto);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/board/edit.jsp");
            rd.forward(req, resp);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void editForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idx = Integer.parseInt(req.getParameter("idx"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        BoardDTO dto = new BoardDTO();
        dto.setIdx(idx);
        dto.setTitle(title);
        dto.setContent(content);

        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(true)) {
            new BoardDAO(sqlSession).updateBoard(dto);
            resp.sendRedirect("detail.board?idx=" + idx);
        }
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idx = Integer.parseInt(req.getParameter("idx"));
        try(SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(true)) {
            new BoardDAO(sqlSession).deleteBoard(idx);
            resp.sendRedirect("list.board");
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
