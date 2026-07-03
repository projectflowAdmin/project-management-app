INSERT INTO projects (id, code, name, status, owner, description)
VALUES
    (1, 'PJF-101', 'Alpha Lane', '進行中', 'Airi Kato', '新規ダッシュボード画面を構築するプロジェクト'),
    (2, 'PJF-102', 'Nova Hub', '設計中', 'Ren Ito', 'API 連携の設計と検証を進めるプロジェクト'),
    (3, 'PJF-103', 'Sprint Canvas', '進行中', 'Mio Sato', '課題管理フローを整備するプロジェクト')
ON CONFLICT (id) DO NOTHING;

INSERT INTO issues (id, issue_key, project_id, title, status, priority, assignee, due_date, description)
VALUES
    (1, 'ISS-101', 1, 'ログイン画面の文言修正', '進行中', 'High', 'Airi Kato', '2026-07-10', '利用者に分かりやすいラベルへ見直す'),
    (2, 'ISS-102', 2, '課題詳細 API のレスポンス確認', '未対応', 'Medium', 'Ren Ito', '2026-07-12', 'フロント連携前にレスポンス内容を確認する'),
    (3, 'ISS-103', 3, 'ダッシュボードの数値表示を調整', 'レビュー待ち', 'Low', 'Mio Sato', '2026-07-15', '主要 KPI の見え方を微調整する')
ON CONFLICT (id) DO NOTHING;

SELECT setval(pg_get_serial_sequence('projects', 'id'), COALESCE((SELECT MAX(id) FROM projects), 1), true);
SELECT setval(pg_get_serial_sequence('issues', 'id'), COALESCE((SELECT MAX(id) FROM issues), 1), true);
