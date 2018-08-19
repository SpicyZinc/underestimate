/*
Write a SQL query to get the second highest salary from the Employee table.
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+

For example, given the above Employee table, the query should return 200 as the second highest salary.
If there is no second highest salary, then the query should return null.

+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+

idea:
With two arguments, the first argument specifies the offset of the first row to return,
and the second specifies the maximum number of rows to return. 

*/

-- SELECT DISTINCT salary as SecondHighestSalary FROM Employee ORDER BY Salary DESC LIMIT 1, 1;
SELECT (SELECT DISTINCT Salary FROM Employee ORDER BY Salary DESC LIMIT 1, 1) AS SecondHighestSalary;