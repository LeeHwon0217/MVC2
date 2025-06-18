package com.koreait.mvc2.controller;

import com.koreait.mvc2.service.CommentService;
import com.koreait.mvc2.service.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.comment")
public class CommentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CommentService service = new CommentServiceImpl();

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
            case "/write.comment":
                service.write(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}






