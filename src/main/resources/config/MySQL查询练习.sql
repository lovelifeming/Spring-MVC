-- 数据库操作练习 http://www.cnblogs.com/aqxss/p/6563625.html
use webapp;
show tables;
describe student
select * from teacher
drop table course

create table if not exists student( 
id tinyint unsigned not null auto_increment,
Sno varchar(20) not null,
Sname varchar(20) not null,
Ssex varchar(20) not null,
Sbirthday datetime,Class varchar(20), 
Class varchar(20),
CreateTime datetime not null,
UpdateTime datetime not null,
primary key(id,Sno)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB

create table if not exists teacher(
id tinyint unsigned not null auto_increment,
Tno varchar(20) not  null,
Tname varchar(20) not null,
Tsex varchar(20) not null,
Tbirthday datetime,
Prof varchar(20),
Depart varchar(20) not null,
CreateTime datetime not null,
UpdateTime datetime not null,
primary key(id,Tno)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB

create table if not exists course(
id tinyint unsigned not null auto_increment,
Cno varchar(20) not null,
Cname varchar(20) not null,
Tno varchar(20) not null references teacher(Tno),
CreateTime datetime not null,
UpdateTime datetime not null,
primary key(id,Cno,Tno)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB

create table if not exists score(
id tinyint unsigned not null auto_increment,
Sno varchar(20) not null references student(Sno),
Cno varchar(20) not null references course(Cno),
Degree Decimal(4,1),
CreateTime datetime not null,
UpdateTime datetime not null,
primary key(id,Sno,Cno)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB

alter table student add column Class varchar(20);

insert student(Sno,Sname,Ssex,Sbirthday,Class,CreateTime,UpdateTime) values
(103,'陆君','男','1974-06-03','95031',now(),now());

insert teacher(Tno,Tname,Tsex,Tbirthday,Prof,Depart,CreateTime,UpdateTime) values
(831,'刘冰','女','1977-08-14','助教','电子工程系',now(),now());

insert course (Cno,Cname,Tno,CreateTime,UpdateTime)values
('9-888','高等数学',831,now(),now());

insert score (Sno,Cno,Degree,CreateTime,UpdateTime)values
('108','6-166',81,now(),now());
 
select Sname,Ssex,Class from student 

select distinct Depart from teacher

select * from student

select * from score where Degree between '68' and '79'

select * from score where Degree in (85,86,88)

select * from student where Class='95031' or Ssex='女'
select * from student order by Class desc

select * from score order by Cno asc,Degree desc;

select count(*) from student where Class='95031';

select Sno,Cno,Degree from score where Degree=(select max(Degree) from score)
select Sno,Cno,Degree from score order by Degree desc limit 0,1;

select Cno,avg(Degree) from score group by Cno

select Cno,avg(Degree) from score where 
Cno in (select Cno from score group by Cno having count(*) >=5) 
and Cno like '3%';

select Cno,avg(Degree) from score where Cno like '3%' group by Cno having count(*)>=5;

select Sno,Degree from score where Degree>70 and  Degree<90;

select Sname,Cno,Degree from student as s,score as c where s.Sno= c.Sno;
select Sno,Cname,Degree from score,course where score.Cno=course.Cno;
select Sname,Cname,Degree from student as st,score as sc,course as co where
st.Sno=sc.Sno and co.Cno=sc.Cno;
select Sname,Cname,Degree from student join score on score.Sno=student.Sno 
join course on course.Cno=score.Cno;

select avg(Degree) from score where 
Sno in(select Sno from student where Class='95033')
group by score.Cno;

select * from score where score.Cno='3-105' and Degree >
(select max(Degree) from score where Sno='109' and Cno='3-105');

select * from score where Degree >
(select max(Degree) from score where Sno='109' and Cno='3-105');

select Sno,Sname,Sbirthday from student where year(Sbirthday)=
(select year(Sbirthday) from student where Sno='108') or year(Sbirthday)=
(select year(Sbirthday) from student where Sno='101');

select s.Sname,r.Degree from student as s,score as r,course as c where 
c.Tno=(select Tno from teacher where Tname='张旭') and r.Cno=c.Cno;

select Tname from teacher where Tno=
(select Tno from course where Cno in 
(select Cno from score group by Cno having count(Cno)>5))

 select Tname from Teacher, Course where Teacher.Tno=Course.Tno and 
 Course.Cno =(select Cno from Score group by Cno having count(*)>5)

select * from student where Class in ('95033','95031')

select Cno from score where Degree>85;

select * from score where Cno in(select Cno from course where Tno in(
select Tno from teacher where Depart='计算机系'))

select Tname,Prof from teacher where Depart='计算机系' and Prof not in
(select Prof from teacher where Depart='电子工程系') union
select Tname,Prof from teacher where Depart='电子工程系' and Prof not in
(select Prof from teacher where Depart='计算机系')

select Cno,Sno,Degree from score where


select Cno,Sno,Degree from score where Cno='3-105' and Degree>
any(select Degree from score where Cno='3-245') order by Degree Desc

select Cno,Sno,Degree from score where Cno='3-105' and Degree> 
all(select Degree from score where Cno='3-245') order by Degree desc

select Tname,Tsex,Tbirthday from teacher union
select Sname,Ssex,Sbirthday from student

select Tname,Tsex,Tbirthday from teacher where Tsex='女' union
select Sname,Ssex,Sbirthday from student where Ssex='女'

select  Cno,avg(Degree) from score group by Cno

select Sno,Degree from score a where Degree<
(select avg(Degree) from score b where b.Cno=a.Cno)

select Tname,Depart from teacher where Tno in (select Tno from course)

select Tname,Depart from teacher where Tno not in (select Tno from course where
 Cno in (select Cno from score))

select Class from student where Ssex='男' group by Class having count(*)>=2
select class from student where ssex='男' group by class having count(*)>1

select * from student where Sname not like '王%%'

select Sname,year(now()) - year(Sbirthday) from student

select max(Sbirthday),min(Sbirthday) from student

select * from student order by Class desc,Sbirthday desc

select Cname from course as c,teacher as t where t.Tsex='男' and t.Tno=c.Tno

select Sno,Cno,Degree from score where Degree=(select max(Degree) from score)

select Sname from student where Ssex=(select Ssex from student where Sname='李军')

select Sname from student where Ssex=(select Ssex from student where Sname='李军') and
Class=(select Class from student where Sname='李军')


select Sno,Cno,Degree from score where Cno=(select Cno from course where Cname='计算机导论') and
Sno in(select Sno from student where Ssex='男')
