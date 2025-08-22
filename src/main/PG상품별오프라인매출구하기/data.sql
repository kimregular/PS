SELECT 
    p.product_code,
    SUM(p.price * os.sales_amount) AS sales
FROM offline_sale os
    LEFT JOIN product AS p
    ON p.product_id = os.product_id
GROUP BY product_code
ORDER BY SUM(p.price * os.sales_amount) DESC, p.product_code ASC;