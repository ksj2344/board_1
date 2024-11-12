package com.green.board.model;

import lombok.*;
// 응답용

@Getter
@Setter
public class BoardSelRes {
    private int boardId;
    private String title;
    private String writer;
    private String createdAt;
}
