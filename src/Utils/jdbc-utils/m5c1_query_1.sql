SELECT od.orderNumber, c.customerName, p.productName, od.quantityOrdered 
FROM orderdetails od, products p, customers c, orders
WHERE od.productCode = p.productCode
  AND od.orderNumber = orders.orderNumber
  AND orders.customerNumber = c.customerNumber
  AND od.orderNumber = 10138
  AND od.productCode = 'S24_2022'