use musthave;

drop table if exists member;
drop table if exists board;

-- [예제 5-1] 회원 테이블 생성
create table member (
    id varchar(10) not null,
    pass varchar(10) not null,
    name varchar(30) not null,
    regidate date default (curdate()) not null,
    primary key (id)
);

-- [예제 5-2] 모델1 방식의 게시판 테이블 생성
create table board (
    num int not null auto_increment primary key,
    title varchar(200) not null,
    content varchar(2000) not null,
    id varchar(10) not null,
    postdate date default (curdate()) not null,
    visitcount int(6)
);
 
-- [예제 5-5] 더미 데이터 입력
insert into member (id, pass, name) values ('musthave', '1234', '머스트해브');
insert into board  (title, content, id, visitcount) values ('제목1입니다', '내용1입니다', 'musthave', 0);
