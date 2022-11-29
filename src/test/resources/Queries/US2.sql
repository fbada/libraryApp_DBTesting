select *
from book_borrow;

select count(*) as borrowedBooks
from users u
         inner join book_borrow b on u.id = b.user_id
where is_returned = 0;

select count(*) from book_borrow where is_returned=0;

select status from users
where email = 'librarian43@library';

select  count(*) from users
where status='INACTIVE';
