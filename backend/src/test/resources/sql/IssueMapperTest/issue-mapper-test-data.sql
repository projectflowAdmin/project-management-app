-- 専用DB上で各テストのトランザクション内に読み込む。削除・投入はテスト終了時にロールバックされる。
DELETE FROM management_app.issues;
DELETE FROM management_app.projects;

INSERT INTO management_app.projects (id, project_key, name)
VALUES
    (90001, 'TEST_A', 'Mapperテスト用プロジェクトA'),
    (90002, 'TEST_B', 'Mapperテスト用プロジェクトB'),
    (90003, 'TEST_EMPTY', '課題のないプロジェクト');

-- プロジェクト・ステータスが異なる行を用意し、AND条件の片方だけの一致を排除する。
-- ステータス別件数はTODO=4、IN_PROGRESS=3、REVIEW=2、DONE=1とし、COUNTの条件も区別する。
-- 同じ更新日時の行をプロジェクト内・間に用意し、id DESCによる第2ソートキーも検証する。
-- ID順・登録日時順と更新日時順が異なるデータにする。
INSERT INTO management_app.issues (
    id, project_id, title, description, status, priority,
    assignee_name, due_date, created_at, updated_at
)
VALUES
    (900001, 90001, '未着手の課題', '課題の詳細説明', 'TODO', 'MEDIUM',
     '山田太郎', DATE '2025-03-31', TIMESTAMP '2025-01-01 09:00:00', TIMESTAMP '2025-02-01 10:00:00'),
    (900002, 90001, '対応中の課題', NULL, 'IN_PROGRESS', 'HIGH',
     NULL, NULL, TIMESTAMP '2025-01-02 09:00:00', TIMESTAMP '2025-02-03 12:00:00'),
    (900003, 90001, 'レビュー中の課題', 'レビュー待ち', 'REVIEW', 'LOW',
     '佐藤花子', DATE '2025-04-01', TIMESTAMP '2025-01-03 09:00:00', TIMESTAMP '2025-02-03 12:00:00'),
    (900004, 90001, '完了した課題', '対応済み', 'DONE', 'MEDIUM',
     '鈴木一郎', DATE '2025-03-01', TIMESTAMP '2025-01-04 09:00:00', TIMESTAMP '2025-02-02 11:00:00'),
    (900005, 90002, '別プロジェクトの未着手課題', '別プロジェクト', 'TODO', 'LOW',
     NULL, NULL, TIMESTAMP '2025-01-05 09:00:00', TIMESTAMP '2025-02-03 12:00:00'),
    (900006, 90002, '最新の課題', '最も新しい更新日時', 'IN_PROGRESS', 'MEDIUM',
     '田中次郎', DATE '2025-04-30', TIMESTAMP '2025-01-06 09:00:00', TIMESTAMP '2025-02-04 13:00:00'),
    (900007, 90002, '別プロジェクトのレビュー課題', NULL, 'REVIEW', 'HIGH',
     NULL, NULL, TIMESTAMP '2025-01-07 09:00:00', TIMESTAMP '2025-02-01 10:00:00'),
    (900008, 90002, '別プロジェクトの未着手課題2', NULL, 'TODO', 'LOW',
     NULL, NULL, TIMESTAMP '2025-01-08 09:00:00', TIMESTAMP '2025-02-02 11:00:00'),
    (900009, 90001, '未着手の課題2', NULL, 'TODO', 'MEDIUM',
     NULL, NULL, TIMESTAMP '2025-01-09 09:00:00', TIMESTAMP '2025-02-03 12:00:00'),
    (900010, 90002, '別プロジェクトの対応中課題2', NULL, 'IN_PROGRESS', 'HIGH',
     NULL, NULL, TIMESTAMP '2025-01-10 09:00:00', TIMESTAMP '2025-02-02 11:00:00');
