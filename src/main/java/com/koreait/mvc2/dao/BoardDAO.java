package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.BoardDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class BoardDAO {
    private final SqlSession sqlSession;

    public BoardDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void insertBoard(BoardDTO dto) {
        sqlSession.insert("board.insertBoard", dto);
    }

    public List<BoardDTO> getBoardList(Map<String, Object> paramMap) {
        return sqlSession.selectList("board.getBoardList", paramMap);
    }

    public int getTotalCount(){
        return sqlSession.selectOne("getTotalCount");
    }

    public BoardDTO getBoard(int idx){
        return sqlSession.selectOne("board.getBoard", idx);
    }

    public void increaseViewCount(int idx){
        sqlSession.update("board.increaseViewCount", idx);
    }

    public void updateBoard(BoardDTO dto){
        sqlSession.update("board.updateBoard", dto);
    }

    public void deleteBoard(int idx){
        sqlSession.delete("board.deleteBoard", idx);
    }
}
