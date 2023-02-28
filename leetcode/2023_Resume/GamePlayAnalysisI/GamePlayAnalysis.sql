/*
Write an SQL query to report the first login date for each player.
Return the result table in any order.
The query result format is in the following example.
*/

# Write your MySQL query statement below
Select A.player_id, min(A.event_date) as first_login
from Activity A
group by A.player_id


# SELECT
#   A.player_id,
#   MIN(A.event_date) AS first_login
# FROM
#   Activity A
# GROUP BY
#   A.player_id;