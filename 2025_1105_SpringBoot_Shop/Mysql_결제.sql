-- 결제 테이블
create table payment(
	pay_idx int primary key auto_increment
	,pay_order_num int 							-- 주문번호
	,pay_model_num VARCHAR(100)					-- 상품모델번호
	,pay_name 		VARCHAR(200)				-- 상품명
	,pay_cnt		INT							-- 상품수량
	,pay_price		INT							-- 상품단가
	,pay_regdate	datetime					-- 결제일자
	,mem_idx		INT							-- 고객번호
	,p_idx			INT							-- 상품번호
)

-- 외래키
ALTER TABLE payment
	ADD CONSTRAINT fk_payment_mem_idx FOREIGN KEY(mem_idx) REFERENCES member(mem_idx);

ALTER TABLE payment
	ADD CONSTRAINT fk_payment_p_idx FOREIGN KEY(p_idx) REFERENCES product(idx);