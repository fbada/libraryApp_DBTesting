select full_name, b.name, bb.borrowed_date, bb.is_returned
from users u
         inner join book_borrow bb on u.id = bb.user_id
         inner join books b on bb.book_id = b.id
where full_name = 'Test Student 2' and b.name = 'Head First Java'
and bb.is_returned = 0
order by 3 desc;

select * from book_borrow;

select b.isbn as ISBN, b.name as Book_Name, b.author as Author,bc.name as Category,  b.year as Year
from books b
inner join book_categories bc on b.book_category_id = bc.id
where b.name ='Chordeiles minor'
limit 1;




