select id
from users;

select *
from users;

select name
from book_categories;

select *
from books
where name = 'Chordeiles minor';

select bc.name, count(*)
from book_borrow bb
         inner join books b on bb.book_id = b.id
         inner join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;

select name,author from books
where name = 'Clean Code'
order by id desc;