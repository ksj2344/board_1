package com.green.board;

import com.green.board.model.BoardInsReq;
import com.green.board.model.BoardSelOneRes;
import com.green.board.model.BoardSelRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/*
    @Service - 빈 등록, 서비스가 로직처리 담당. 로직처리가 있다면 여기서 처리한다. 없으면 연결작업만.
               연결 작업이 Controller와 Persistence(DB)연결
    빈 등록: 스프링 컨테이너에게 객체생성을 대리로 맡기는 것. 기본적으로 싱글톤으로 객체화.
 */


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    public int insBoard(BoardInsReq p){
        return mapper.insBoard(p);
    }

    public List<BoardSelRes> selBoardList(){
        return mapper.selBoardList();
    }
    public BoardSelOneRes selBoardOne(int p){
       return mapper.selBoardOne(p);
    }
}
