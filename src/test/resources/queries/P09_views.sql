create view PERSONAL_INFO as
select FIRST_NAME || ' ' || LAST_NAME as full_name
from EMPLOYEES;

create or replace view PERSONAL_INFO as
select FIRST_NAME || ' ' || LAST_NAME as full_name, SALARY*12 as Annual_salary
from EMPLOYEES;

select "FULL_NAME" from PERSONAL_INFO;

drop view FULL_NAME;

