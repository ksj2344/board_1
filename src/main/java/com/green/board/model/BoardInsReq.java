package com.green.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//리퀘스트용  // 응답용이랑 중복되는게 많더라도 추후 수정이 용이하기 위해 분리를 해주는 것이 좋음

@Setter  //에노테이션으로 Setter/Getter 부여
@Getter
@ToString //toString 오버라이딩을 에노테이션으로 실행
public class BoardInsReq {
    private String title;
    private String contents;
    private String writer;
}
