


-- 댓글 테이블
create table comment_tb
	(
		c_idx		int 			primary key auto_increment	-- 댓글일련번호
	   ,c_content 	varchar(2000)	not null					-- 댓글내용
	   ,c_ip		varchar(100)	not null					-- 아이피
	   ,c_regdate	datetime		not null					-- 작성일자
	   ,b_idx 		int											-- 글번호
	   ,mem_idx		int											-- 회원번호
	   ,mem_id		varchar(100)								-- 회원아이디
   )

   
-- 참조키(외래키)
alter table comment_tb
	add constraint fk_comment_tb_b_idx	foreign key(b_idx)
										references board(b_idx);
										
alter table comment_tb
	add constraint fk_comment_tb_mem_idx foreign key(mem_idx)
										 references member(mem_idx);
										 
