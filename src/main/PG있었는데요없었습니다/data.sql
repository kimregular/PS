SELECT ao.animal_id, ao.name
FROM animal_outs AS ao
    LEFT JOIN animal_ins AS ai
    ON ao.animal_id = ai.animal_id
WHERE ao.datetime < ai.datetime
ORDER BY ai.datetime ASC;