package com.koreait.mvc2.controller;

import com.koreait.mvc2.service.BoardService;
import com.koreait.mvc2.service.BoardServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardService service = new BoardServiceImpl();

    public BoardController() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String context = req.getContextPath();
        String command = uri.substring(context.length());

        switch (command) {
            case "/list.board":
                service.list(req, resp);
                break;
            case "/write.board":
                req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);
                break;
            case "/writeForm.board":
                service.write(req, resp);
                break;
            case "/detail.board":
                service.detail(req, resp);
                break;
            case "/edit.board":
                service.edit(req, resp);
                break;
            case "/editForm.board":
                service.editForm(req, resp);
                break;
            case "/delete.board":
                service.delete(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}