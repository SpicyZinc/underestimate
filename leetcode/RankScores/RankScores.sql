/*
Write a SQL query to rank scores.
If there is a tie between two scores, both should have the same ranking.
Note that after a tie, the next ranking number should be the next consecutive integer value.
In other words, there should be no "holes" between ranks.

+----+-------+
| Id | Score |
+----+-------+
| 1  | 3.50  |
| 2  | 3.65  |
| 3  | 4.00  |
| 4  | 3.85  |
| 5  | 4.00  |
| 6  | 3.65  |
+----+-------+

For example, given the above Scores table, your query should generate the following report (order by highest score):
+-------+------+
| Score | Rank |
+-------+------+
| 4.00  | 1    |
| 4.00  | 1    |
| 3.85  | 2    |
| 3.65  | 3    |
| 3.65  | 3    |
| 3.50  | 4    |
+-------+------+

*/

select a.Score, count(distinct b.Score) as Rank
from Scores a
left join Scores b
    on a.Score <= b.Score
    group by a.Id
    order by a.Score DESC

SELECT Scores.Score, COUNT(Ranking.Score) AS RANK
  FROM Scores, (
       SELECT DISTINCT Score
         FROM Scores
       ) Ranking
 WHERE Scores.Score <= Ranking.Score
 GROUP BY Scores.Id, Scores.Score
 ORDER BY Scores.Score DESC;

-- employee and department
-- join
-- department name and 每个department的人数

SELECT department_name AS 'Department Name',
	COUNT(*) AS 'No of Employees'
		FROM departments
			INNER JOIN employees
			ON employees.department_id = departments.department_id
		GROUP BY departments.department_id, department_name
		ORDER BY department_name;