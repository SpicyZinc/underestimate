


mysql> select * from employee;
+------+-------+---------+
| uid  | name  | manager |
+------+-------+---------+
|    1 | Liang |       0 |
|    2 | Xin   |       1 |
|    3 | Spicy |       1 |
+------+-------+---------+
3 rows in set (0.00 sec)

mysql> select a.name as 'employee name', b.name as 'manager name'
    -> from employee a left join employee b on
    -> a.manager = b.uid
    -> order by a.uid;
+---------------+--------------+
| employee name | manager name |
+---------------+--------------+
| Liang         | NULL         |
| Xin           | Liang        |
| Spicy         | Liang        |
+---------------+--------------+
3 rows in set (0.00 sec)