


-- 게시판 DB

create table board
(
	 b_idx 			int		 primary key auto_increment 	-- 게시물번호
	,b_subject		varchar(300)							-- 게시판제목
	,b_content		text									-- 내용
	,b_ip			varchar(100) 							-- 아이피
	,b_regdate 		datetime								-- 작성일자
	,b_readhit 		int										-- 조회수
	,mem_idx		int										-- 회원번호
	,mem_name		varchar(200)							-- 회원명
	,b_ref			int										-- 참조글번호
	,b_step			int										-- 글순서
	,b_depth		int										-- 글깊이
	,b_use			varchar(1)								-- 사용유무(삭제정보)
)

--외래키 설정
alter table board
	add constraint fk_board_mem_idx foreign key(mem_idx)
		references member(mem_idx);
		
-- 체크제약
alter table board
	add constraint ck_board_b_use check(b_use in ('y','n'));


--	sampledata
--	새글쓰기
insert into board values(
							 null
							,'내가 1등 나야나~'
							,'이번에도 1등'
							,'192.168.11.111'
							,now()
							,0
							,1
							,'경원이'
							,1
							,0
							,0
							,'y'
						)
						
insert into board values(
							 null
							,'아쉽네 2등 나야나~'
							,'다음엔 1등'
							,'192.168.12.121'
							,now()
							,0
							,2
							,'홍길동'
							,1
							,1
							,1
							,'y'
						)

insert into board values(
							 null
							,'내가 1등 나야나~ㅋㅋㅋ'
							,'이번에도 1등'
							,'192.168.11.111'
							,now()
							,0
							,1
							,'경원이'
							,1
							,2
							,2
							,'y'
						)	
						
insert into board values(
							 null
							,'내가 1등 나야나~ㅋㅋㅋ'
							,'이번에도 1등'
							,'192.168.11.111'
							,now()
							,0
							,1
							,'경원이'
							,1
							,2
							,3
							,'n'
						)
						
-- 전체 조회했을대 댓글순서에 따른 조회			
select * from board
	order by b_ref desc, b_step asc;
	
	
-- 페이징 처리를 위한 순번
select t.* from
	(
	select
	*
	,
	rank() over(order by b_ref desc, b_step asc) as no
	from board
	) t
where no between 1 and 5

-- 페이징 처리를 위한 순번 + 인라인뷰
select 
	t.* 
	,(select count(*) from comment_tb where b_idx=t.b_idx)as comment_count
from
	(
	select
	*
	,
	rank() over(order by b_ref desc, b_step asc) as no
	from board
	) t
where no between 1 and 5


--		[최근에 사용된 댓글 순서]
select * from
(select
	*,
	rank() over(order by c_idx desc) as no
from comment_tb where b_idx=7
) c
where no between 1 and 5


--	전체 게시물수( ifnull은 널이 들어오면 어떻게 처리할지 )
select ifnull(count(*),0) from board 


					
-- 수정
update board set b_use='y' where b_idx=2
	
	
-- 회원 정보 불러오기
select * from member

-- 게시판 삭제
drop table board

-- 데이터 삭제
delete from board where b_idx=6