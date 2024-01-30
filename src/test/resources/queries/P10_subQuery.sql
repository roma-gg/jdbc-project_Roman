select *
from EMPLOYEES
order by SALARY desc;

select max(SALARY)
from EMPLOYEES;

select *
from EMPLOYEES
where SALARY = (select max(SALARY) from employees);


select FIRST_NAME, LAST_NAME, DEPARTMENT_ID
from EMPLOYEES
where DEPARTMENT_ID = (select DEPARTMENT_ID
                       from DEPARTMENTS
                       where DEPARTMENT_NAME = 'IT');

