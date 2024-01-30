select * from EMPLOYEES

select * from EMPLOYEES where FIRST_NAME = 'JAMES';

select FIRST_NAME, SALARY from EMPLOYEES
where SALARY < 5000
order by SALARY asc;

select * from EMPLOYEES
where SALARY < 5000 and MANAGER_ID=114;

select FIRST_NAME, LAST_NAME, SALARY from EMPLOYEES
where SALARY between 3000 and 5000
order by SALARY desc;

select FIRST_NAME, LAST_NAME, SALARY from EMPLOYEES
where FIRST_NAME in ('Peter', 'David', 'James')
order by SALARY desc

