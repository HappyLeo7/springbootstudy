
--입고
create sequence seq_product_in_idx

create table product_in
(
   idx   int primary key,--일련번호
   name  varchar2(255),   --상품명 
   cnt   int,			  --입고수량	
   regdate date           --입고일자
)

--출고
create sequence seq_product_out_idx

create table product_out
(
   idx   int primary key,--일련번호
   name  varchar2(255),   --상품명 
   cnt   int,			  --출고수량	
   regdate date           --출고일자
)

--출고
create sequence seq_product_remain_idx

create table product_remain
(
   idx   int primary key,--일련번호
   name  varchar2(255),   --상품명 
   cnt   int,			  --재고수량	
   regdate date          --재고일자
)

