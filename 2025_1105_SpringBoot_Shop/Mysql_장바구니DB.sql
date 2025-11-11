
-- 장바구니 테이블
create table cart
(
  c_idx  int  primary key auto_increment,
  c_cnt  int  not null,
  p_idx  int,
  mem_idx int
)

-- 상품테이블(product)의 idx와 cart의 p_idx간의 외래키 설정
alter table cart
  add constraint fk_cart_p_idx foreign key(p_idx) 
                               references product(idx)
                               
-- 회원테이블(member) mem_idx와 cart의 mem_idx간의 외래키 설정
alter table cart
  add constraint fk_cart_mem_idx foreign key(mem_idx) 
                               references member(mem_idx)
                               
-- 상품 전체조회
select * from product

-- 회원 전체조회
SELECT * FROM member

-- 샘플
INSERT INTO cart VALUES(null,1,1,1)
INSERT INTO cart VALUES(null,1,2,1)
INSERT INTO cart VALUES(null,1,2,2)


insert into cart values(seq_cart_idx.nextVal,1,1);

select * from cart

commit

-- Join을 통해서 조회정보를 추출
create or replace view cart_view
as
	select
	   p.idx,c_idx, p_model_num,p_name,p_price,p_saleprice,
	   c_cnt, c_cnt* p_saleprice amount,mem_idx
	from product p inner join  cart c on p.idx = c.p_idx  

-- 장바구니뷰 조회
select * from cart_view where mem_idx=1;
select * from cart_view where mem_idx=2;
select * from cart_view where mem_idx=3;

-- 장바구니 상품의 총계
select sum(amount) from cart_view where mem_idx=1;
select sum(amount) from cart_view where mem_idx=2;












