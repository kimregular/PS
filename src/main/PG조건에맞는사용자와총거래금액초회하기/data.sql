SELECT ugu.user_id, ugu.nickname, SUM(ugb.price) AS total_sales
FROM used_goods_board AS ugb
    LEFT JOIN used_goods_user AS ugu
    ON ugb.writer_id = ugu.user_id
WHERE ugb.status = 'DONE'
GROUP BY ugb.writer_id
HAVING total_sales >= 700000
ORDER BY total_sales ASC;