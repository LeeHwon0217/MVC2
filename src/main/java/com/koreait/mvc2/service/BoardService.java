package com.koreait.mvc2.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

public interface BoardService {
    void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void write(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void editForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
