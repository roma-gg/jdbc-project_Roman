select * from EMPLOYEES;
select * from DEPARTMENTS;

select FIRST_NAME from EMPLOYEES;

-- multiple column names
select EMPLOYEE_ID, FIRST_NAME, DEPARTMENT_ID from EMPLOYEES;

--multiple columns from multiple tables
select EMPLOYEES.EMPLOYEE_ID, EMPLOYEES.FIRST_NAME, EMPLOYEES.DEPARTMENT_ID, DEPARTMENTS.DEPARTMENT_ID, DEPARTMENTS.DEPARTMENT_NAME from EMPLOYEES, DEPARTMENTS;

-- distinct
select distinct (FIRST_NAME) from EMPLOYEES;
select distinct (SALARY) from EMPLOYEES;


