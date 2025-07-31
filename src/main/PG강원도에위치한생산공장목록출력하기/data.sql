SELECT factory_id, factory_name, address
FROM food_factory
WHERE address like '강원도%'
ORDER By factory_id ASC;