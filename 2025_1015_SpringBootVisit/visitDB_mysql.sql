


-- 방명록 테이블
create table visit
(
   idx  	int  			primary key auto_increment,		-- 일련번호
   name 	varchar(100) 	not null,						-- 이름 
   content 	varchar(2000)	not null,						-- 내용		
   pwd  	varchar(100) 	not null,						-- 비밀번호
   ip  		varchar(100) 	not null,						-- 아이피
   regdate 	datetime										-- 등록일자
)


   
-- sample data
   
insert into visit values( null ,
                          '일길동', 
                          '내가 1등이야~~',
                          '1234',
                          '127.0.0.1',
                          now() );
                          
insert into visit values( null,
                          '이길동', 
                          '아쉽네 내가 1등할 수 있었는데...',
                          '1234',
                          '127.0.0.1',
                          now() ) ;

-- 전체조회 (인덱스 역순으로)
select * from visit order by idx desc

-- 삭제
delete from visit where idx = 15

select * from visit where idx=12


-- 수정
update visit set name='응길동',content='응',pwd='dmd',ip='192.168.219.234' where idx=12


commit







