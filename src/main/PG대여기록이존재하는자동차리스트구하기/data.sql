SELECT DISTINCT(crcr.car_id)
FROM car_rental_company_car AS crcr
    LEFT JOIN car_rental_company_rental_history AS crcrh
    ON crcr.car_id = crcrh.car_id
WHERE crcr.car_type = '세단'
AND MONTH(crcrh.start_date) = 10
ORDER BY crcr.car_id DESC;