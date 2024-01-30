select * from students;

create table students (
    student_id INTEGER PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    email varchar(50) UNIQUE,
    age INTEGER CHECK ( age > 18 ),
    status varchar(50) DEFAULT 'Active'
);



insert into students(student_id, first_name, email, age)
VALUES (2, 'Luda', '321@gmail.com', 20);

insert into students(student_id, first_name, email, age)
VALUES (3, 'Sasha', '333@gmail.com', 21);

insert into students(student_id, first_name, email, age)
VALUES (4, 'Baba', '111@gmail.com', 22);

insert into students(student_id, first_name, email, age)
VALUES (5, 'Dada', '222@gmail.com', 29);

insert into students(student_id, first_name, email, age)
VALUES (1, 'Tata', '444@gmail.com', 29);

insert into students(student_id, first_name, email, age)
VALUES (6, 'Roman', '555@gmail.com', 19);

insert into students(student_id, first_name, email, age)
VALUES (7, 'Roman', '123@gmail.com', 19);

commit;

UPDATE students
set status='Active'
where FIRST_NAME = 'Roman';

delete from students
where first_name = 'Roman';

alter table students
add gender varchar(20);

update students
set gender='Male'
where student_id=1
or student_id=5;

update students
set gender='Female'
where gender is null;

alter table students
rename column status to statuSSS;

alter table students
rename column statusss to status;

alter table students
drop column status;

alter table students
rename to students_1;

select * from students_1;

commit;

drop table students_1;

commit;

