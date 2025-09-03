SELECT fh.flavor
FROM first_half AS fh
    LEFT JOIN icecream_info AS ii
    ON fh.flavor = ii.flavor
WHERE fh.total_order > 3000
AND ii.ingredient_type = 'fruit_based';