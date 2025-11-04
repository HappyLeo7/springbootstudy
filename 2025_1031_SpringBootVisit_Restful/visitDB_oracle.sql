
-- 일련번호 관리객체(1부터시작 1씩증가)
create sequence  seq_visit_idx 

-- 방명록 테이블
create table visit
(
   idx  int,						-- 일련번호
   name varchar2(100) not null,		-- 이름 
   content varchar2(2000) not null,	-- 내용
   pwd  varchar2(100) not null,		-- 비밀번호
   ip   varchar2(100) not null,		-- 아이피
   regdate date						-- 등록일자
)

-- 기본키
alter table visit
   add constraint  pk_visit_idx primary key(idx) 
   
-- sample data
   
insert into visit values( seq_visit_idx.nextVal ,
                          '일길동', 
                          '내가 1등이야~~',
                          '1234',
                          '127.0.0.1',
                          sysdate )
                          
insert into visit values( seq_visit_idx.nextVal ,
                          '이길동', 
                          '아쉽네 내가 1등할 수 있었는데...',
                          '1234',
                          '127.0.0.1',
                          sysdate ) 
-- DAO용 SQL문                          
insert into visit values( seq_visit_idx.nextVal , ? , ? , ? , ? , sysdate )                                                                            

select * from visit order by idx desc

-- 삭제
delete from visit where idx = 15

select * from visit where idx=12


-- 수정
update visit set name='응길동',content='응',pwd='dmd',ip='192.168.219.234' where idx=12


commit







