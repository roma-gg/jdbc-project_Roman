select * from EMPLOYEES;

select concat(concat(INITCAP(FIRST_NAME), ' '), INITCAP(LAST_NAME)) AS full_name,
       lower(EMAIL)||'@gmail.com' AS email
from EMPLOYEES
order by full_name;

select FIRST_NAME, length(FIRST_NAME) AS name_length from EMPLOYEES;

select concat(concat(substr(FIRST_NAME, 0, 1), '.'), concat(substr(LAST_NAME, 0, 1), '.'))  AS INITIALS
from EMPLOYEES
order by FIRST_NAME;

select e.FIRST_NAME, e.LAST_NAME, d.MANAGER_ID from EMPLOYEES e, DEPARTMENTS d;

