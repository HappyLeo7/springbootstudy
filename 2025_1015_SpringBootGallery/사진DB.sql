

--Photo table
create table photo
(
	 p_idx 				int			  primary key auto_increment					-- 일련번호
	,p_subject 			varchar(200) not null		-- 제목
	,p_content 			text 		  not null		-- 내용
	,p_filename 		varchar(200) not null		-- 사진파일명
	,p_ip 				varchar(200) not null		-- 아이피
	,p_regdate			varchar(200) not null		-- 등록일자
	,p_lastmodifydate 	varchar(200) not null		-- 최근수정일자
	,mem_idx			int					  		-- 회원번호
	,foreign key(mem_idx)
	references member(mem_idx)
);

-- 기본키설정
alter table photo
	add constraint pk_photo_p_idx primary key(p_idx);

-- 외래키 설정
alter table photo
	add constraint fk_photo_mem_idx foreign key(mem_idx)
									references member(mem_idx); -- member에 있는 mem_idx를 참조한다.
									
									
--전체조회	
select * from photo;

--전체게시물수 구하기 nvl(null,0)는 null이면 0을 넣어라 라는 의미
select nvl(count(*),0) from photo;

--Paging 처리를 위한 SQL
select * 
	from
		(
		select
		rank() over(order by p_idx) no
		,p.*
		from(select * from photo order by p_idx)p
		)
where no between 1 and 8 


-- p_idx 기준으로 나열
select * from photo order by p_idx
select * from photo order by p_idx asc
select * from photo order by p_idx desc




--더미 데이터 추가
insert into PHOTO values(seq_photo_p_idx.nextval,'제목','내용','filaname','0:0:0',sysdate,sysdate,1);






-- 수정하기
update photo set p_subject='제목'
				,p_content='축구'
				,p_ip='1010'
				,p_lastmodifydate=sysdate
				where p_idx=14;




-- 삭제.....
drop table photo