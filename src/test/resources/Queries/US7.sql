select full_name, b.name, bb.borrowed_date, bb.is_returned
from users u
         inner join book_borrow bb on u.id = bb.user_id
         inner join books b on bb.book_id = b.id
where full_name = 'Test Student 2' and b.name = 'Head First Java'
and bb.is_returned = 0
order by 3 desc;

select * from book_borrow;

select * from books
where name = 'Head First Java';
