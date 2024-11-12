package com.green.board;

/*
    src > main > resource > mappers 폴더 아래에 이름이 같은 xml파일을 만든다.
    (같은 이름을 할 필요는 없으나 관리상 용이하게 하기 위해 같은 이름을 쓴다.)

    xml+interface 파일을 이용해서 implements한 class파일을 만들고 빈등록까지 해준다. (빈등록을 받아야 DI를 받을 수 있다.)
    스프링 컨테이너가 빈등록한 class 파일을 객체화 할 것이다. 여기서만든 주소값을 BoardService 객체화 할 때까지 DI해준다.

    insert, update, delete의 리턴 타입은 int하면 됨.
*/
import com.green.board.model.*;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper //mybatis가 제공하는 에노테이션.
public interface BoardMapper { //BoardDao 인터페이스 역할
    int insBoard(BoardInsReq p);
    // 여기서 반환되는 것은 영향 받은 행의 갯수.
    // 0 아니면 1이 반환된다.

    List<BoardSelRes> selBoardList();

    BoardSelOneRes selBoardOne(int p);

    int updBoard(BoardUpdReq p);

    int delBoard(BoardDelReq p);
}

//추상메소드들은 BoardMapper.xml에서 매핑되어 구현화 됨