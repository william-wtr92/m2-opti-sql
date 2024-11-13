CREATE MATERIALIZED VIEW project_task_count AS
SELECT
    p.id AS project_id,
    p.name AS project_name,
    COUNT(t.id) AS task_count
FROM
    projects p
        LEFT JOIN
    tasks t ON p.id = t.project_id
GROUP BY
    p.id, p.name;
