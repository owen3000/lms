
select * from user;
select * from item;

select * from dvd;
select * from record;
select * from book;

select * from rent;
select * from reserve;

select * from dvd_category;
select * from record_category;
select * from book_category;

-- -------- insert ----------------------------------------------------
-- insert admin
insert into user values(null,'admin@admin.com','male','관리자','admin','admin');

-- insert categorys 
insert into dvd_category values(null,'영화-드라마');
insert into dvd_category values(null,'영화-액션');
insert into dvd_category values(null,'영화-판타지');

insert into record_category values(null,'음반(락)');
insert into record_category values(null,'음반(클래식)');
insert into record_category values(null,'음반(발라드)');

insert into book_category values(null,'도서(IT)');
insert into book_category values(null,'도서(요리)');
insert into book_category values(null,'도서(소설)');

-- insert items
-- book
insert into item values('B',null,'no','JAVA의 정석');
insert into book values('ISBN978-89-954321-2-9',1,1);

insert into item values('B',null,'no','C프로그래밍');
insert into book values('ISBN978-89-111121-2-9',2,1);

insert into item values('B',null,'no','열혈 TCP/IP');
insert into book values('ISBN978-89-222221-2-9',3,1);

insert into item values('B',null,'no','짜파게티 요리사');
insert into book values('ISBN978-89-954388-2-9',4,2);

insert into item values('B',null,'no','소세지 볶음 레시피');
insert into book values('ISBN978-89-111144-2-9',5,2);

insert into item values('B',null,'no','달빛 조각사');
insert into book values('ISBN978-89-224277-2-9',6,3);

-- dvd
insert into item values('D',null,'no','밥 잘사주는 예쁜 누님');
insert into dvd values('넷마블',7,1);

insert into item values('D',null,'no','쾌걸 춘향');
insert into dvd values('더드라마',8,1);

insert into item values('D',null,'no','범죄 도시');
insert into dvd values('마블',9,2);

insert into item values('D',null,'no','반지의 제왕');
insert into dvd values('골룸 컴퍼니',10,3);

-- record
insert into item values('R',null,'no','아이유 리믹스');
insert into record values('JYP',11,1);

insert into item values('R',null,'no','What the Hell');
insert into record values('avril',12,1);

insert into item values('R',null,'no','The classic');
insert into record values('Beethoven',13,2);

insert into item values('R',null,'no','The Spring');
insert into record values('BenHur',14,2);

insert into item values('R',null,'no','부탁해요');
insert into record values('보이스메모리',15,3);

insert into item values('R',null,'no','청춘연가');
insert into record values('넬',16,3);

insert into item values('R',null,'no','Home');
insert into record values('로이킴',17,3);

insert into item values('R',null,'no','Love Love Love');
insert into record values('로이킴',18,3);

insert into item values('R',null,'no','선인장');
insert into record values('심규선',19,3);
-- ------- end insert -----------------------------------------------