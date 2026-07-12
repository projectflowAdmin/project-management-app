# 03 API仕様

## 共通

Base URL は `http://localhost:8080/api` とする。

認証・認可は現時点では未実装。すべてのAPIはローカル開発環境で認証なしに呼び出す。
ログイン画面はフロントエンドに実装済みだが、ログイン認証API、セッション管理、権限管理は未実装とする。

## エラーレスポンス

エラーレスポンスは以下の形式とする。

```json
{
  "message": "入力内容を確認してください。",
  "details": ["name: must not be blank"]
}
```

主なステータスコード:

| HTTPステータス | 発生条件 |
| --- | --- |
| 400 | バリデーションエラー、不正なリクエストボディ、業務エラー |
| 404 | 指定されたリソースが存在しない |

## エンドポイント一覧

| メソッド | パス | 概要 |
| --- | --- | --- |
| GET | `/health` | ヘルスチェック |
| GET | `/dashboard` | ダッシュボード集計取得 |
| GET | `/projects` | プロジェクト一覧取得 |
| GET | `/projects/{id}` | プロジェクト詳細取得 |
| POST | `/projects` | プロジェクト登録 |
| PUT | `/projects/{id}` | プロジェクト更新 |
| DELETE | `/projects/{id}` | プロジェクト削除 |
| GET | `/issues` | 課題一覧取得 |
| GET | `/issues/{id}` | 課題詳細取得 |
| POST | `/issues` | 課題登録 |
| PUT | `/issues/{id}` | 課題更新 |
| DELETE | `/issues/{id}` | 課題削除 |

## GET /health

### レスポンス例

```json
{
  "status": "UP"
}
```

## GET /dashboard

### レスポンス項目

| 項目 | 型 | 説明 |
| --- | --- | --- |
| projectCount | number | プロジェクト数 |
| issueCount | number | 課題数 |
| todoCount | number | 未着手件数 |
| inProgressCount | number | 対応中件数 |
| reviewCount | number | レビュー中件数 |
| doneCount | number | 完了件数 |
| recentIssues | IssueResponse[] | 最近更新された課題。最大5件 |

## Project API

### ProjectRequest

| 項目 | 型 | 必須 | 制約 |
| --- | --- | --- | --- |
| projectKey | string | 必須 | 最大20文字、`A-Z`, `a-z`, `0-9`, `_`, `-` のみ |
| name | string | 必須 | 最大100文字 |
| description | string | 任意 | 最大2000文字 |

### ProjectResponse

| 項目 | 型 | 説明 |
| --- | --- | --- |
| id | number | プロジェクトID |
| projectKey | string | プロジェクトキー |
| name | string | プロジェクト名 |
| description | string \| null | 説明 |
| createdAt | string | 作成日時 |
| updatedAt | string | 更新日時 |

### 備考

- `POST /projects` は登録成功時に `201 Created` を返す。
- `DELETE /projects/{id}` は削除成功時に `204 No Content` を返す。
- プロジェクトキーが重複する場合は `400` を返す。
- 課題が紐づくプロジェクトは削除できず、`400` を返す。

## Issue API

### GET /issues クエリパラメータ

| パラメータ | 型 | 概要 |
| --- | --- | --- |
| projectId | number | プロジェクトIDで絞り込み |
| status | string | `TODO`, `IN_PROGRESS`, `REVIEW`, `DONE` で絞り込み |

優先度、担当者、期限日、キーワードによるAPI絞り込みは未実装。画面内キーワード検索はフロントエンド側で実施する。

### IssueRequest

| 項目 | 型 | 必須 | 制約 |
| --- | --- | --- | --- |
| projectId | number | 必須 | 既存プロジェクトID |
| title | string | 必須 | 最大200文字 |
| description | string | 任意 | 最大5000文字 |
| status | string | 必須 | `TODO`, `IN_PROGRESS`, `REVIEW`, `DONE` |
| priority | string | 必須 | `LOW`, `MEDIUM`, `HIGH` |
| assigneeName | string | 任意 | 最大100文字 |
| dueDate | string \| null | 任意 | `YYYY-MM-DD` |

### IssueResponse

| 項目 | 型 | 説明 |
| --- | --- | --- |
| id | number | 課題ID |
| projectId | number | プロジェクトID |
| projectKey | string | プロジェクトキー |
| projectName | string | プロジェクト名 |
| title | string | 件名 |
| description | string \| null | 説明 |
| status | string | ステータス値 |
| statusLabel | string | ステータス表示ラベル |
| priority | string | 優先度値 |
| priorityLabel | string | 優先度表示ラベル |
| assigneeName | string \| null | 担当者名 |
| dueDate | string \| null | 期限日 |
| createdAt | string | 作成日時 |
| updatedAt | string | 更新日時 |

### 備考

- `POST /issues` は登録成功時に `201 Created` を返す。
- `DELETE /issues/{id}` は削除成功時に `204 No Content` を返す。
- 存在しないプロジェクトIDを指定した場合は `404` を返す。

## 表示ラベル

### ステータス

| 値 | 表示ラベル |
| --- | --- |
| `TODO` | 未着手 |
| `IN_PROGRESS` | 対応中 |
| `REVIEW` | レビュー中 |
| `DONE` | 完了 |

### 優先度

| 値 | 表示ラベル |
| --- | --- |
| `LOW` | 低 |
| `MEDIUM` | 中 |
| `HIGH` | 高 |

## リクエスト例

```json
{
  "projectId": 1,
  "title": "課題タイトル",
  "description": "説明",
  "status": "TODO",
  "priority": "MEDIUM",
  "assigneeName": "担当者",
  "dueDate": "2026-07-31"
}
```
