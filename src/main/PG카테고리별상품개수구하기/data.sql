SELECT LEFT(product_code, 2) AS category, COUNT(*) AS products
FROM product
GROUP BY LEFT(product_code, 2)
ORDER BY product_code ASC;