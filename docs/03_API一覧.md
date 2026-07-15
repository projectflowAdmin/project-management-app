# 03 API一覧

## API一覧

| API ID | Method | Path | 概要 | 実装状況 |
| --- | --- | --- | --- | --- |
| API-001 | GET | `/api/health` | ヘルスチェック | 実装済み |
| API-002 | GET | `/api/dashboard` | ダッシュボード集計取得 | 実装済み |
| API-003 | GET | `/api/projects` | プロジェクト一覧取得 | 実装済み |
| API-004 | GET | `/api/projects/{id}` | プロジェクト詳細取得 | 実装済み |
| API-005 | POST | `/api/projects` | プロジェクト登録 | 実装済み |
| API-006 | PUT | `/api/projects/{id}` | プロジェクト更新 | 実装済み |
| API-007 | DELETE | `/api/projects/{id}` | プロジェクト削除 | 実装済み |
| API-008 | GET | `/api/issues` | 課題一覧取得 | 実装済み |
| API-009 | GET | `/api/issues/{id}` | 課題詳細取得 | 実装済み |
| API-010 | POST | `/api/issues` | 課題登録 | 実装済み |
| API-011 | PUT | `/api/issues/{id}` | 課題更新 | 実装済み |
| API-012 | DELETE | `/api/issues/{id}` | 課題削除 | 実装済み |

---

## 運用方針

本ドキュメントは、ProjectFlowで提供するAPIの一覧を管理します。

各APIの詳細仕様は管理しません。

API追加・変更時は一覧表のみ更新してください。

詳細なRequest、Response、エラー仕様はGitHub Issueとソースコードを正とします。
