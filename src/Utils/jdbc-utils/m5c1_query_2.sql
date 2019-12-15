SELECT e1.lastName AS EmployeeName, 
	   e2.lastName AS ManagerName, 
       e2.employeeNumber as ManagerNumber
FROM employees e1, employees e2
WHERE e1.reportsTo = e2.employeeNumber
ORDER BY e2.employeeNumber;
