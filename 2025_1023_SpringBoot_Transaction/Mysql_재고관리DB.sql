
-- 입고
create table product_in
(
   idx   int primary key auto_increment,	--	일련번호
   name  varchar(255),  					--	상품명 
   cnt   int,								--	입고수량	
   regdate datetime      					--	입고일자
)


-- 출고
create table product_out
(
   idx   int primary key auto_increment, --	일련번호
   name  varchar(255),   --	상품명 
   cnt   int,			  --	출고수량	
   regdate datetime       --	출고일자
)

-- 재고
create table product_remain
(
   idx   int primary key auto_increment, --    일련번호
   name  varchar(255),   --    상품명 
   cnt   int,			  --    재고수량	
   regdate datetime       --    재고일자
)


-- 재고 테이블에 idx 오토 설정 수정
alter table product_remain
	modify column idx int not null auto_increment


-- 제약조건
alter table product_remain
	add constraint unique_product_remain_name unique(name);