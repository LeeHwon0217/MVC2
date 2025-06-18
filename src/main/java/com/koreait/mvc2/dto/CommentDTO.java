package com.koreait.mvc2.dto;

public class CommentDTO {
    private int idx;
    private int boardIdx;
    private String userId;
    private String content;
    private String regDate;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(int boardIdx) {
        this.boardIdx = boardIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
