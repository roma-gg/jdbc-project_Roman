select *
from EMPLOYEES
order by SALARY desc
;

select * from (select *
        from EMPLOYEES
        order by SALARY desc)
where ROWNUM <=5;

select distinct SALARY, FIRST_NAME, LAST_NAME from EMPLOYEES
order by SALARY desc;

select min(SALARY) from (select distinct SALARY from EMPLOYEES
                             order by SALARY desc )
where ROWNUM <=7;

select FIRST_NAME, LAST_NAME, SALARY from (EMPLOYEES)
where SALARY = (select min(SALARY) from (select distinct SALARY from EMPLOYEES
                                         order by SALARY desc )
                where ROWNUM <=7);

select * from STUDENTS;