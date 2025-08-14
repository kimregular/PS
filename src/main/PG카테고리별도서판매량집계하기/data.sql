SELECT category, SUM(bs.sales) AS TOTAL_SALES
FROM book AS b
    INNER JOIN book_sales AS bs
    ON b.book_id = bs.book_id
    WHERE YEAR(bs.sales_date) = '2022'
    AND MONTH(bs.sales_date) = '1'
GROUP BY category
ORDER BY category ASC;