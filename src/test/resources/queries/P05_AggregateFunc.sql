select count(*) from EMPLOYEES;

select count(*) from COUNTRIES;

select * from COUNTRIES;

select REGION_ID, count(*) AS CountResult
from COUNTRIES
GROUP BY REGION_ID;

select REGION_ID, count(*) over () AS CountResult
from COUNTRIES;

select * from EMPLOYEES;

select JOB_ID, max(SALARY), min(SALARY), avg(SALARY), count(*) as employees_quantity
from EMPLOYEES
group by JOB_ID
having count(*) > 4
order by JOB_ID;

select count(*) from EMPLOYEES;

select MANAGER_ID, count(*) AS employees
from EMPLOYEES
GROUP BY MANAGER_ID;

select DEPARTMENT_ID, count(*) as employees
from EMPLOYEES
group by DEPARTMENT_ID
ORDER BY DEPARTMENT_ID;

select max(SALARY) from EMPLOYEES;

select sum(SALARY) from EMPLOYEES;

select round(avg(SALARY), 2) from EMPLOYEES;

select DEPARTMENT_ID, round(avg(SALARY)) AS dpt_average_salary
from EMPLOYEES
group by DEPARTMENT_ID
ORDER BY dpt_average_salary desc;




