

-- 상품테이블
CREATE TABLE product	(
	idx		 			int primary key auto_increment,		-- 일련번호
	p_model_num			varchar(100)	unique	Not Null,	-- 상품번호(모델번호)
	p_name		 		varchar(50)		Not Null,		-- 상품명
	p_company    		varchar(50)		Not Null,		-- 제조사
	p_price				int				Not Null,		-- 가격(단가)
	p_saleprice  		int			Not Null,			-- 할인가
	p_image_s	 		varchar(255)		Null,			-- 상품이미지(소)
	p_image_l			varchar(255)		Null,			-- 상품이미지(대)
	p_content	 		longtext	Not Null,					-- 상품설명
	p_date				datetime					Not Null	-- 등록일자	
	,cate_idx			int								-- 카테고리번호
	,constraint uk_product_p_model_num unique(p_model_num)	
);



insert into product values(null, 'RC-113',
'로체스 인라인','로체스',3200,1150,'pds1.jpg','pds1_z.jpg',
'바이오맥스 통풍 나일론-HGPU SHELL * 특수 충격 흡수 밑창 * 신발끈 메모리 버클 * 힐 락에 의한 신속한 신발끈 시스템 * 느린 메모리 포말에 의한 편안한 통풍성의 숨쉬는 라이너 * 쿨 통풍 시스템 * 통풍성의 인체공학적 신발밑창 * 손쉬운 엔트리 시스템(신기 편한 입구) * 몰디드 알루미늄 프레임 * 80mm 82a hyper dubbs 휠 * 강철 스페이서 * ABEC-5 베어링',now(),3);

insert into product values(null, 'vC-13',
'사니PDP-TV','사니',9200,4750,'pds4.jpg','pds4_z.jpg',
'질러~ 질러! 
무조건 질러봐~ 후회 하지 않아~~',now(),2);


select * from product;

commit









