select * from tab;

create table ma_201612015 (
	id varchar2(30) not null primary key,
	pw varchar2(20) not null,
	name varchar2(30) not null,
	phone varchar2(13),
	email varchar2(30),
	country varchar2(50)
);

alter table ma_201612015 add (
	regdate date,
	rank number(11)
);


insert into ma_201612015 (id, pw, name) values ('admin', 'admin12', 'ParkSM');
select * from MA_201612015;

CREATE SEQUENCE pid_seq INCREMENT BY 1 START with 1;
CREATE TABLE p_201612015 (
	pid				int				not null	primary key,
	productId		varchar2(10)	not null,
	pname			varchar2(20)	not null,
	unitPrice		number(11),
	manufacturer	varchar2(20),
	description		varchar2(20),
	category		varchar2(20),
	unitsInStock	number(11),
	condition		varchar2(20),
	quantity		number(11),
	fileName		varchar2(50)
);

DROP TABLE p_201612015;
DROP sequence pid_seq;

insert into p_201612015 values (pid_seq.NEXTVAL, 'PP45', 'iPones', '800000', '4.7-inch', 'Smart Phone', 'Apple', 1000, 'New', 1,'upfile1.png');

select * from p_201612015;

delete from p_201612015 where productId='P1';





