package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.CommentDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CommentDAO {
    private SqlSession sqlSession;

    public CommentDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void insertComment(CommentDTO dto) {
        sqlSession.insert("comment.insertComment", dto);
    }

    public List<CommentDTO> getCommentsByBoardIdx(int boardIdx){
        return sqlSession.selectList("comment.getCommentsByBoardIdx", boardIdx);
    }
}
