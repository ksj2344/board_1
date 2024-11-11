package com.green.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter  //에노테이션으로 Setter/Getter 부여
@Getter
@ToString //toString 오버라이딩을 에노테이션으로 실행
public class BoardInsReq {
    private String title;
    private String contents;
    private String writer;
}
