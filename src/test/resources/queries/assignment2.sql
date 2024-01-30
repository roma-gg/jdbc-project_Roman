--1. Display full addresses from locations table in a single column
select STREET_ADDRESS || ', ' || POSTAL_CODE || ', ' || CITY || ', ' || STATE_PROVINCE as full_address
from LOCATIONS;

-- 2. Display all informations of the employee who has the minimum salary in employees table
select *
from EMPLOYEES
where SALARY = (select min(SALARY) from EMPLOYEES);

--3. Display the second minimum salary from the employees
select max(SALARY)
from (select * from EMPLOYEES order by SALARY)
where ROWNUM <= 2;

--4. Display all informations of the employee who has the second minimum salary
select *
from EMPLOYEES
where SALARY = (select max(SALARY)
                from (select * from EMPLOYEES order by SALARY)
                where ROWNUM <= 2);

--5. List all the employees who are making above the average salary;
select FIRST_NAME, LAST_NAME, SALARY
from EMPLOYEES
where SALARY > (select avg(SALARY) from EMPLOYEES)
order by SALARY;

--6. List all the employees who are making less than the average salary
select FIRST_NAME, LAST_NAME, SALARY
from EMPLOYEES
where SALARY < (select avg(SALARY) from EMPLOYEES)
order by SALARY desc;

--7. Display manager name of 'Neena'
select EMPLOYEE_ID, FIRST_NAME, LAST_NAME
from EMPLOYEES
where EMPLOYEE_ID = (select MANAGER_ID
                     from EMPLOYEES
                     where FIRST_NAME = 'Neena');

--8. Find the 3rd maximum salary from the employees table (do not include duplicates)
select distinct min(SALARY)
from (select SALARY from EMPLOYEES order by SALARY desc)
where ROWNUM <= 4;

--9. Find the 5th maximum salary from the employees table (do not include duplicates)
select distinct min(SALARY)
from (select SALARY from EMPLOYEES order by SALARY desc)
where ROWNUM <= 6;

--10. Find the 7th maximum salary from the employees table (do not include duplicates)
select distinct min(SALARY)
from (select SALARY from EMPLOYEES order by SALARY desc)
where ROWNUM <= 9;

--11. Find the 10th maximum salary from the employees table (do not include duplicates)
select distinct min(SALARY)
from (select SALARY from EMPLOYEES order by SALARY desc)
where ROWNUM <= 14;

--12. Find the 3rd minimum salary from the employees table (do not include duplicates)
select distinct max(SALARY)
from (select SALARY from EMPLOYEES order by SALARY)
where ROWNUM <= 4;

--13. Find the 5th minimum salary from the employees table (do not include duplicates)
select max(SALARY)
from (select  distinct SALARY from EMPLOYEES order by SALARY)
where ROWNUM <= 5;