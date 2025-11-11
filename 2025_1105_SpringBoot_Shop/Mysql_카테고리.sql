
-- 카테고리 테이블 생성
create table category
(
	 cate_idx int primary key auto_increment -- 카테고리 일련번호
	,cate_name varchar(200) not null
)

-- sample data
insert into category values(null,'컴퓨터');
insert into category values(null,'가전제품');
insert into category values(null,'스포츠');
insert into category values(null,'전체');

update category set cate_idx=0 where cate_idx=4

select * from category;