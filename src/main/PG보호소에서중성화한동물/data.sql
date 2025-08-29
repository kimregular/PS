-- 코드를 입력하세요
SELECT ao.animal_id,
    ao.animal_type,
    ao.name
FROM (
    SELECT animal_id
    FROM animal_ins
    WHERE 
        sex_upon_intake NOT LIKE 'Spayed%' 
        AND sex_upon_intake NOT LIKE 'Neutered%'
) AS infertile
JOIN animal_outs AS ao
ON infertile.animal_id = ao.animal_id
WHERE sex_upon_outcome LIKE 'Spayed%'
    OR sex_upon_outcome LIKE 'Neutered%'
ORDER BY ao.animal_id;