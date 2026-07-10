# Dify入力用 要件コンテキスト

## 1. このファイルの目的

このファイルは、Difyに1ファイルだけ入力するための統合コンテキストです。

正式な設計書の代替ではありません。正式情報は `docs` 配下の各設計書、実装コード、SQLを正とします。

Difyはこのファイルをもとに、ProjectFlowの現状を把握し、GitHub Issueに転記しやすい追加開発Issue案を作成します。実装済み機能と未実装機能を混同しないよう、判断できない内容は「確認が必要」として扱ってください。

## 2. アプリ概要

- アプリ名: ProjectFlow
- リポジトリ名: project-management-app
- ProjectFlowは、プロジェクト管理、課題管理、ダッシュボードを中心にした学習用のプロジェクト管理Webアプリです。
- 2〜5年目程度のエンジニアが、要件定義、設計、実装、レビュー、リリースに近い開発プロセスを経験するための疑似プロジェクトです。
- BacklogやJiraのようなプロジェクト管理ツールを参考にしていますが、完全コピーではなく、一般的なプロジェクト管理アプリとして独自表現にしています。
- 社内情報、顧客情報、個人情報、機密情報は含めません。

## 3. 技術構成

| 分類 | 技術 |
| --- | --- |
| Frontend | Vue 3 + TypeScript + Vite + Vue Router |
| Backend | Java 21 + Spring Boot + Maven |
| DB | PostgreSQL |
| DBアクセス | MyBatis |
| SQL管理 | Mapper XML、`backend/src/main/resources/sql/schema.sql`、`backend/src/main/resources/sql/data.sql` |
| バージョン管理 | Git |
| 資材管理 | GitHub |
| タスク管理 | GitHub Issues / GitHub Projects |
| AI支援 | Dify |

## 4. 現在実装済みの機能

既存docsと実装コードから判断できる範囲では、以下がMVPとして実装済みです。

### 4.1 プロジェクト管理

- プロジェクト一覧表示
- プロジェクト詳細取得
- プロジェクト登録
- プロジェクト編集
- プロジェクト削除
- プロジェクトキーの重複チェック
- 課題が紐づくプロジェクトは削除不可

### 4.2 課題管理

- 課題一覧表示
- 課題詳細表示
- 課題登録
- 課題編集
- 課題削除
- プロジェクトによる絞り込み
- ステータスによる絞り込み
- ステータス表示ラベル
- 優先度表示ラベル

### 4.3 ダッシュボード

- プロジェクト数表示
- 課題数表示
- ステータス別件数表示
- 最近更新された課題表示
- バックエンド接続状態の表示は画面要件に記載あり。実装済みかは確認が必要です。

### 4.4 共通機能

- Request DTOのBean Validationによる入力チェック
- 共通例外処理
- 統一形式のエラーレスポンス
- CORS設定
- Health API
- Vue画面からBackend REST APIを呼び出す構成

## 5. 現在の画面一覧

| 画面名 | パス | 概要 |
| --- | --- | --- |
| ダッシュボード | `/` | 件数サマリー、ステータス別件数、最近更新された課題を表示 |
| プロジェクト一覧 | `/projects` | プロジェクト一覧、編集、削除、新規作成導線を表示 |
| プロジェクト登録 | `/projects/new` | プロジェクトキー、名称、説明を登録 |
| プロジェクト編集 | `/projects/:id/edit` | 既存プロジェクトを編集 |
| 課題一覧 | `/issues` | 課題一覧、絞り込み、詳細、編集、削除、新規作成導線を表示 |
| 課題登録 | `/issues/new` | 課題を登録 |
| 課題詳細 | `/issues/:id` | 課題の詳細項目を表示 |
| 課題編集 | `/issues/:id/edit` | 既存課題を編集 |

## 6. 現在のAPI一覧

Base URLは `http://localhost:8080/api` です。

| メソッド | パス | 概要 | 備考 |
| --- | --- | --- | --- |
| GET | `/api/health` | ヘルスチェック | `{"status":"UP"}` を返す |
| GET | `/api/dashboard` | ダッシュボード集計取得 | プロジェクト数、課題数、ステータス別件数、最近更新された課題 |
| GET | `/api/projects` | プロジェクト一覧取得 | 全プロジェクトを取得 |
| GET | `/api/projects/{id}` | プロジェクト詳細取得 | 存在しないIDはエラー |
| POST | `/api/projects` | プロジェクト登録 | Request DTOで入力チェック |
| PUT | `/api/projects/{id}` | プロジェクト更新 | 自分以外のプロジェクトキー重複は不可 |
| DELETE | `/api/projects/{id}` | プロジェクト削除 | 課題が存在する場合は削除不可 |
| GET | `/api/issues` | 課題一覧取得 | `projectId`、`status` で絞り込み可能 |
| GET | `/api/issues/{id}` | 課題詳細取得 | 存在しないIDはエラー |
| POST | `/api/issues` | 課題登録 | 存在しないプロジェクトIDはエラー |
| PUT | `/api/issues/{id}` | 課題更新 | ステータスと優先度は定義済みEnumを使用 |
| DELETE | `/api/issues/{id}` | 課題削除 | 存在しないIDはエラー |

エラーレスポンスは `message` と `details` を持つJSON形式です。

## 7. 現在のDB定義

DBスキーマは `management_app` です。ER図はこのファイルには含めません。

### 7.1 projects テーブル

| カラム | 型 | 制約 | 用途 |
| --- | --- | --- | --- |
| id | BIGSERIAL | PRIMARY KEY | プロジェクトID |
| project_key | VARCHAR(20) | NOT NULL, UNIQUE, INDEX | プロジェクトを識別するキー |
| name | VARCHAR(100) | NOT NULL | プロジェクト名 |
| description | TEXT | NULL | プロジェクト説明 |
| created_at | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP | 作成日時 |
| updated_at | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP | 更新日時 |

### 7.2 issues テーブル

| カラム | 型 | 制約 | 用途 |
| --- | --- | --- | --- |
| id | BIGSERIAL | PRIMARY KEY | 課題ID |
| project_id | BIGINT | NOT NULL, FOREIGN KEY, INDEX | 紐づくプロジェクトID |
| title | VARCHAR(200) | NOT NULL | 件名 |
| description | TEXT | NULL | 課題説明 |
| status | VARCHAR(30) | NOT NULL, INDEX | ステータス。`TODO`, `IN_PROGRESS`, `REVIEW`, `DONE` |
| priority | VARCHAR(30) | NOT NULL | 優先度。`LOW`, `MEDIUM`, `HIGH` |
| assignee_name | VARCHAR(100) | NULL | 担当者名 |
| due_date | DATE | NULL | 期限日 |
| created_at | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP | 作成日時 |
| updated_at | TIMESTAMP | NOT NULL, DEFAULT CURRENT_TIMESTAMP, INDEX | 更新日時 |

削除制約として、課題が存在するプロジェクトは削除できません。APIは400エラーを返します。

## 8. 現在の実装ルール

### 8.1 Backend

- Controller → Service → Dao → Mapper → DB の順で責務分離します。
- ControllerからDaoやMapperを直接呼びません。
- ServiceからMapperを直接呼びません。
- ControllerはService interfaceのみを呼びます。
- ServiceはinterfaceとImplを分けます。
- ServiceImplは業務ルール、存在チェック、レスポンス変換を担当します。
- DaoはinterfaceとImplを分けます。
- DaoImplはMapper interfaceの呼び出しだけを担当します。
- SQLはMapper XMLに記載します。
- Mapper interfaceにSQLを直接書きません。
- Request / Response はDTOを使用します。
- ModelとDTOを分けます。
- 画面表示用ラベルはbackendで返します。
- 共通例外処理は `GlobalExceptionHandler` に集約します。
- 入力チェックはRequest DTOのBean Validationで行います。

### 8.2 Frontend

- Vue 3 + TypeScriptを使用します。
- API通信は `src/api` に分離します。
- 型定義は `src/types` に分離します。
- 画面は `src/views` に配置します。
- 画面はモック固定値ではなくAPIレスポンスを表示します。
- 画面コンポーネントにAPI処理を詰め込みすぎないようにします。
- 通信失敗時のエラー表示を考慮します。
- 業務ツールとして、一覧、フォーム、詳細を読みやすく保ちます。

### 8.3 DB / SQL

- Spring Data JPAは使用せず、MyBatisを使用します。
- SQLは `src/main/resources/mapper/*Mapper.xml` に記載します。
- DB初期化は `src/main/resources/sql/schema.sql` と `src/main/resources/sql/data.sql` で管理します。
- Flyway / Liquibase はMVPでは導入しません。
- DB変更時はdocsとSQLの両方を更新します。

### 8.4 docs更新ルール

- 仕様変更時はdocsも更新します。
- 要件追加・変更時は `docs/01_要件定義.md` を更新します。
- API変更時は `docs/04_API一覧.md` を更新します。
- DB変更時は `docs/05_DB設計.md` を更新します。
- 画面追加時は `docs/03_画面一覧.md` を更新します。
- コーディング規約変更時は `docs/06_コーディング規約.md` を更新します。
- レビュー観点変更時は `docs/08_レビュー観点.md` を更新します。
- 環境構築手順に変更がある場合のみ `docs/09_環境構築手順書.md` を更新します。
- Difyの入力判断に影響する大きな変更がある場合は、このファイルを更新します。
- 社内情報、顧客情報、個人情報を含めません。

## 9. 現時点で実装しないもの

以下は初期MVPでは実装しない機能です。ただし、今後の追加課題候補として扱ってかまいません。

- 認証・ログイン
- 権限管理
- ユーザー管理
- 通知
- 添付ファイル
- Wiki
- ガントチャート
- 本格的なカンバンボード
- メール送信
- 外部サービス連携
- コメント
- タグ
- 課題変更履歴
- CSV出力
- 帳票出力

## 10. 追加開発候補

2〜5年目のエンジニアが段階的に取り組めるよう、難易度別に整理します。

### 10.1 初級

- 課題一覧に優先度絞り込みを追加する
- 課題一覧に期限切れラベルを表示する
- 課題一覧の並び順を変更できるようにする
- ダッシュボードに期限切れ件数を表示する
- プロジェクト一覧に課題件数を表示する
- 入力フォームの補助メッセージを改善する

### 10.2 初中級

- コメント機能を追加する
- 課題ステータス変更履歴を追加する
- 担当者マスタを追加する
- タグ機能を追加する
- 課題一覧に複合検索条件を追加する
- プロジェクトごとの課題サマリーを表示する

### 10.3 中級

- カンバンボード画面を追加する
- CSV出力機能を追加する
- 簡易ログイン機能を追加する
- プロジェクト別ダッシュボードを追加する
- ページングを追加する
- 課題一括更新機能を追加する

### 10.4 やや難しい

- 添付ファイル機能
- 通知機能
- 権限管理
- Wiki機能
- ガントチャート機能
- 外部サービス連携

## 11. Difyに期待する出力

Difyは抽象的な要件定義ではなく、GitHub Issueに転記しやすい追加開発タスクを出力してください。

- 最大3件まで出力します。
- 1件は2〜3年目のエンジニアが1〜3日程度で対応できる粒度にします。
- 難しすぎる機能は複数Issueに分割します。
- 実装済み機能と重複するIssueを出しません。
- 不明点は推測せず確認事項にします。
- 画面、API、DB、Backend、Frontend、docsの影響範囲を明記します。
- 受入条件とテスト観点を必ず含めます。

## 12. GitHub Issue出力フォーマット

DifyはIssue案を以下の形式で出力してください。追加要件を要件定義に追記する場合は、`docs/01_要件定義.md` の「要件追加テンプレート」に沿って整理してください。

```markdown
## 背景

## 対応内容

## 変更対象

## 受入条件

## テスト観点

## 想定難易度

## 想定作業者

## 補足
```

各項目の説明は以下です。

| 項目 | 説明 |
| --- | --- |
| 背景 | なぜこの追加開発が必要か、現在の課題や利用者メリットを書く |
| 対応内容 | 実装する内容を箇条書きで具体化する |
| 変更対象 | 変更が必要な画面、API、DB、Backend、Frontend、docsを書く |
| 受入条件 | 完了判断できる具体的な条件を書く |
| テスト観点 | 正常系、異常系、表示確認、API確認、DB確認などを書く |
| 想定難易度 | 初級、初中級、中級、やや難しいのいずれかを書く |
| 想定作業者 | 2年目、3年目、4〜5年目などの目安を書く |
| 補足 | 分割案、確認事項、対象外事項を書く |

docs更新対象は以下から現在存在するdocsだけを選択してください。

- [ ] 01_要件定義.md
- [ ] 03_画面一覧.md
- [ ] 04_API一覧.md
- [ ] 05_DB設計.md
- [ ] 06_コーディング規約.md
- [ ] 08_レビュー観点.md
- [ ] 09_環境構築手順書.md
- [ ] 00_Dify入力用_要件コンテキスト.md
- [ ] 更新不要

## 13. 追加要件を作るときの制約

- 社内情報、顧客情報、個人情報を含めません。
- BacklogやJiraの完全コピーにしません。
- 現在の技術構成から大きく外れないようにします。
- 初心者に難しすぎるIssueを出しません。
- 実装済み機能と重複するIssueを出しません。
- 画面、API、DB、Backend、Frontend、docsの影響範囲を明記します。
- 受入条件を具体的にします。
- テスト観点を必ず書きます。
- 認証、権限、添付ファイル、通知などの大きな機能は小さなIssueに分割します。
- DB変更を伴う場合は、マイグレーション方針またはSQL更新方針も確認事項に含めます。

## 14. 不明点の扱い

- 実装済みか判断できない場合は「確認が必要」と書きます。
- docs間で矛盾がある場合は矛盾点を明記します。
- 不足している情報がある場合は確認事項として出します。
- 推測で実装済み・未実装を断定しません。
- このファイルと各docsまたは実装コードに差がある場合は、各docsまたは実装コードの確認を優先します。

このファイル作成時に参照した主なファイルは以下です。

- `docs/01_要件定義.md`
- `docs/03_画面一覧.md`
- `docs/04_API一覧.md`
- `docs/05_DB設計.md`
- `docs/06_コーディング規約.md`
- `docs/08_レビュー観点.md`
- `docs/09_環境構築手順書.md`
- `backend/src/main/resources/sql/schema.sql`
- `frontend/src/router/index.ts`
- `backend/src/main/java/com/projectmanagementapp/controller/*Controller.java`
- `backend/src/main/java/com/projectmanagementapp/domain/model/IssueStatus.java`
- `backend/src/main/java/com/projectmanagementapp/domain/model/IssuePriority.java`
