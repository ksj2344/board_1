package com.green.board;
/*
    Controller의 역할 : 요청(request)을 받고 응답(response)을 처리하는 객체. 로직처리는 하지 않는다.

        사용하는 애노테이션(Annotation)
        @Controller - 응답을 html로 함(데이터로 만든 화면을 응답)
        @RestController - 응답을 JSON으로 함(데이터만 응답)

        @RequestMapping - URL과 클래스 아래에 있는 Method 맵핑(연결)
                          class에 RequestMapping 전체 메소드 주소가 맵핑

        @PostMapping - URL + Post 방식으로 요청이 왔을 시 담당자 요청과 응답은 (header, body)로 이루어져 있음

    요청과 응답은 (header, body)로 이루어져 있음.
    header에는 목적지(url), 방식, 인코딩 등등
    body에는 값, 데이터 담겨져있음

    브라우저를 통해 요청을 보낼 때 URL과 method를 함께 요청을 보낸다.
    브라우저의 주소창에 주소값을 적고 엔터는 URL+GET+데이터 방식(key/value)으로 요청을 보낸다.
    데이터를 보낼 때 보여지나 안보여지나 차이로 보낼 수 있는데
    1. 쿼리스트링 방식(파라미터라고 부르기도 함), URL(헤더)에 데이터를 포함하는 방식.
    2. body에 담아서 보내는 방식. 응답을 보통 이 방식으로 했음.

    쿼리스트링 모양: URL + 쿼리스트링(?로 시작. key=value, 여러개라면 & 구분)
                  www.naver.com?name=홍길동&age=12&height=172.1

    대용량의 데이터를 보내야할 때도 body에 데이터를 담아서 보낸다. URL은 길이제한이 있기 때문에
    URL에 데이터를 포함하는 쿼리스트링은 대용량을 보낼 수 없다.

    Restful 이전에는 get, post 방식 밖에 없었다.
    get방식은 주로 쿼리스트링 방식을 사용하고  - 데이터를 읽어올 때 사용(간혹 삭제할 때도 사용)
    post방식은 body에 데이터를 담아서 보내는 방식을 사용했었다.  - 데이터를 저장/수정/삭제할 때 사용
    ※ 데이터가 있었을 때는 get방식이 처리 속도가 빠르다. 데이터 처리가 아닌 단순 화면을 띄울 때도 get방식을 사용한다.

    예를 들어 로그인을 하는 상황에서 로그인을 하는 화면이 띄워져야 한다.
    작업(1): 로그인 하는 화면은 get방식으로 URL은 /login을 요청하면 로그인하는 화면이 화면에 나타났다.
            (get)/login 이렇게 표현하겠다.
    작업(2): 그 다음, 아이디/비번을 작성하고 로그인 버튼을 누르면 (post) /login
            , 아이디/비번은 body에 담아서 요청을 보냈다.

    URL은 같으나 method(get/post)로 작업을 구분했다.(마치 if문 처럼)

    위 작업예시는 2가지 밖에 없었기 때문에 같은 주소값으로 method를 구분할 수 있었다.
    그런데 CRUD(작업 4가지)를 해야하는 상황에서는 작업 구분을 주소값으로 해야했었다.

    (get) /board - 게시판 리스트 보기 화면
    (get) /board_detail - 게시판 글 하나 보기 화면
    (get) /board_create - 게시판 글 등록하는 화면
    (post) /board_create - 게시판 글 등록하는 작업 처리
    (get) /board_modify - 게시판 글 수정하는 화면
    (post) /board_modify - 게시판 글 수정하는 작업 처리
    (get/post) /board_delete - 게시판 글 삭제하는 작업 처리

    첫 페이지(index화면)을 띄울 때 소프트웨어(FrontEnd 작업 코드)가 모두 다운로드 됨.
    화면 이동은 모두 FE 코드가 작동하는 것. 화면 만들기는 Client 리소스를 사용하여 그린다.(Rendering)
    화면마다 데이터가 필요하면 BackEnd에게 요청을 한다.
    누가? FE 작업코드가 요청을 보낸다.
    ☆ 그래서 BE는 이제 화면은 신경쓰지 않아도 된다.
    FE 코드가 요청한 작업에 응답만 잘 해주면 된다.

    Client 리소스: Client, 즉 요청을 보낸 컴퓨터의 자원을 사용한다. (cpu, ram, 하드디스크 등등)

    Restful 방식은 화면은 없고 작업만 신경쓰면 된다.
    (요청의 method는 크게 4가지로 나눠진다.)
    - POST 방식 : Create - Insert 작업
    - GET 방식
    - PUT / PATCH 방식
    - DELETE

    POST, PUT/PATCH 방식은 주로 데이터를 body에 담아서 보내고
    GET, DELETE방식은 Query String or Path Variable을 사용해서 데이터를 보낸다.
    FE가 BE에게 (URL + method + 데이터)로 요청(Request)를 하고 BE는 JSON(혹은 html 혹은 다른 문자열. 보통 JSON)으로 응답(Response)
        서로 통신은 String으로 한다.

    (post) /board - 글 등록
    (get) /board?page=1 - 리스트 데이터 (row가 여러개)
       (get) /board/ - 끝에 /만 붙어도 위의 /board와는 다른 요청이 된다.
       (get) /board?aaa=2 - 얘는 /board?page=1와 같은 요청이다. URL이 같으면 같은 요청.
    (get) /board/1 - 튜플 1개 데이터(row가 1줄), 1은 PK. Path Variable
            (하나를 선택해서 정보를 가져오는 것이라면 PK를 가져옴. 유일하게 중복없는값.)
    (put / patch) /board - 글 수정
    (delete) /board - 글 삭제 (Path Variable or Query String으로 PK값 전달. 보통 쿼리스트링)
 */

import com.green.board.model.BoardInsReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("board")
public class BoardController {

    //insert(Create)
    @PostMapping// (post) /board 요청이 오면 이 메소드가 응답 담당자.
    //위에 @RequestMapping("/board")가 없었다면 URL을 작성해줘야한다.
    //@PostMapping("/board") 요렇게
    //@RequestBody는 요청이 올 때 데이터가 JSON형태로 오니까 거기에 맞춰서 데이터를 받자.
    public int insBoard(@RequestBody BoardInsReq p){
        System.out.println(p);
        return 1;
    }
}
