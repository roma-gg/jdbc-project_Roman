select id from users;

select count(*) from book_borrow
where is_returned = 0;

select b.name as name, isbn, year, author, c.name as category
from books b left join book_categories c
on b.book_category_id = c.id
where b.name = 'Clean Code';

select * from books
where name = 'Unique Book For Test';

select * from book_borrow
where book_id = 23299;

select users.id, users.full_name, books.name, returned_date, book_borrow.is_returned
from books inner join book_borrow
on books.id = book_borrow.book_id
inner join users
on book_borrow.user_id = users.id
where books.name = 'Unique Book For Test'
order by returned_date desc;

select * from book_borrow;

select id from users
where email = 'student18@library';

select * from book_borrow
order by returned_date desc;