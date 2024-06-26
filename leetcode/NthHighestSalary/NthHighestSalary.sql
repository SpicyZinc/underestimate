/*
Write a SQL query to get the nth highest salary from the Employee table.
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+

For example, given the above Employee table, the nth highest salary where n = 2 is 200.
If there is no nth highest salary, then the query should return null.
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+

idea:
similar to 2nd highest salary
*/

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
	Declare M INT;
	SET M = N - 1;
		RETURN (
			select distinct salary from Employee order by Salary DESC limit M, 1
		);
END