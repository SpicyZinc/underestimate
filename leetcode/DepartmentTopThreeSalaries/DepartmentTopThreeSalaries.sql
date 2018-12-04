SELECT
    d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM
    Employee e1
        JOIN
    Department d ON e1.DepartmentId = d.Id
WHERE
    3 > (SELECT
            COUNT(DISTINCT e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > e1.Salary
                AND e1.DepartmentId = e2.DepartmentId
        )
;

SELECT D.Name as Department, E.Name as Employee, E.Salary 
FROM Department D, Employee E, Employee E2  
WHERE D.ID = E.DepartmentId and E.DepartmentId = E2.DepartmentId and E.Salary <= E2.Salary
group by D.ID, E.Name having count(distinct E2.Salary) <= 3
order by D.Name, E.Salary desc;
