SELECT icecream_info.ingredient_type, SUM(first_half.total_order) AS TOTAL_ORDER
FROM first_half
    LEFT JOIN icecream_info
    ON first_half.flavor = icecream_info.flavor
GROUP BY icecream_info.ingredient_type
ORDER BY SUM(first_half.total_order) ASC;