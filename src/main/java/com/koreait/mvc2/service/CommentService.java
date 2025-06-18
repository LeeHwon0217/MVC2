package com.koreait.mvc2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommentService {
    void write(HttpServletRequest req, HttpServletResponse resp) throws SecurityException, IOException;
}
