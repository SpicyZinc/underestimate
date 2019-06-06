Suppose that a website contains two tables, the Customers table and the Orders table.
Write a SQL query to find all customers who never order anything.

Table: Customers.

+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+

Table: Orders.

+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
Using the above tables as example, return the following:

+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+


# Write your MySQL query statement below
-- note left join

select a.Name as Customers from Customers as a
left join Orders as b on a.Id = b.CustomerId
where b.CustomerId is null;

-- or

SELECT Name as Customers from Customers
WHERE Id NOT IN (SELECT CustomerId from Orders);

create table IF NOT EXISTS Customers (Id INT AUTO_INCREMENT, Name VARCHAR(255) NOT NULL) ENGINE=INNODB;

INSERT INTO Customers (Id, Name) VALUES (1, 'Joe'),(2, 'Henry'),(3, 'Sam'),(4, 'Max')



mysql> select * from Customers a left join Orders as b on a.Id = b.CustomerId order by a.Id;
+------+-------+------+------------+
| Id   | Name  | Id   | CustomerId |
+------+-------+------+------------+
|    1 | Joe   |    2 |          1 |
|    2 | Henry | NULL |       NULL |
|    3 | Sam   |    1 |          3 |
|    4 | Max   | NULL |       NULL |
+------+-------+------+------------+


mysql> select * from Customers a left join Orders as b on a.Id = b.CustomerId where b.CustomerId is null;
+------+-------+------+------------+
| Id   | Name  | Id   | CustomerId |
+------+-------+------+------------+
|    2 | Henry | NULL |       NULL |
|    4 | Max   | NULL |       NULL |
+------+-------+------+------------+