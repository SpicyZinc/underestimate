inner == join
left
right
full outer join == full join
http://www.codeproject.com/Articles/33052/Visual-Representation-of-SQL-Joins


Table from which you are taking data is 'LEFT'.
Table you are joining is 'RIGHT'.
LEFT JOIN: Take all items from left table AND (only) matching items from right table.
RIGHT JOIN: Take all items from right table AND (only) matching items from left table.
So:

Select * from Table1 left join Table2 on Table1.id = Table2.id  
gives:

Id     Name       
-------------  
1      A          
2      B      
but:

Select * from Table1 right join Table2 on Table1.id = Table2.id
gives:

Id     Name       
-------------  
1      A          
2      B   
3      C  
you were right joining table with less rows on table with more rows
AND
again, left joining table with less rows on table with more rows
Try:

 If Table1.Rows.Count > Table2.Rows.Count Then  
    ' Left Join  
 Else  
    ' Right Join  
 End If  