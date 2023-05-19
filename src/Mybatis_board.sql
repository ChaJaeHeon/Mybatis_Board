--회원제로 운영되는 게시판

--회원테이블
create table member(
   id               varchar2(20) primary key,
   password  varchar2(30) not null,
   name        varchar2(30) not null
);

insert into member values ('chajaeheon', 'wogjsdk123', '차재헌');


--게시글 테이블
create table board (
    num         number(11)   primary key,
    id              varchar2(30),
    title           varchar2(300),
    contents   varchar2(3000),
    inputdate  date  default sysdate,
    constraint  board_id_fk  foreign key(id) references member(id) on delete set null   --on delete 후 cascasde: 부모키 삭제시 모두 같이 삭제, set: 부모만 삭제되고 임시 값으로 저장
);

insert into board (num,id,title,contents) values (board_seq.nextval, 'chajaeheon', '집에가고 싶으면 개추', '일단 나부터');

--글번호 시퀀스
create sequence BOARD_SEQ;