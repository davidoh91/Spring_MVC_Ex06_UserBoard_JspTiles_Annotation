
drop table member;

create table member
(
	user_id			varchar2(10)	primary key,
	pwd				varchar2(10),
	name			varchar2(10)
)

select * from member;



insert into member values('jang', '1234', '장희정');
insert into member values('lee', '1234', '이가현');


commit