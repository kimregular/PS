-- 코드를 입력하세요
SELECT 
    f.category, 
    f.price AS max_price,
    f.product_name
FROM food_product AS f
WHERE f.category IN ('과자', '국', '김치', '식용유')
    AND f.price = (
        SELECT MAX(price)
        FROM food_product
        WHERE category = f.category
    )
ORDER BY max_price DESC;