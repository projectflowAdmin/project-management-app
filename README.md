# ProjectFlow

ProjectFlow は、Backlog や Jira のようなプロジェクト管理ツールを参考にした学習用Webアプリケーションです。実案件に近い開発プロセス（要件定義、設計、実装、レビュー、リリース）を体験できるよう設計されています。

名称・画面構成・文言・配色は独自化し、特定サービスのコピーにならないように設計しています。

## プロジェクト概要

- フロントエンドとバックエンドを分離したモノレポ構成
- フロントエンドは Vue 3 + Vite + TypeScript
- バックエンドは Java 21 + Spring Boot + Maven
- DB はローカル PostgreSQL を利用する想定
- 初期段階ではインメモリ実装を中心に、公開リポジトリで扱いやすいサンプルデータを同梱

## 技術構成

- Frontend: Vue 3, Vite, TypeScript, Vue Router
- Backend: Java 21, Spring Boot 3, Maven
- Database: PostgreSQL 16

## ディレクトリ構成

```text
project-management-app/
├─ README.md
├─ docs/
│  ├─ 00_Dify入力用_要件コンテキスト.md
│  ├─ 01_要件定義.md
│  ├─ 03_画面一覧.md
│  ├─ 04_API一覧.md
│  ├─ 05_DB設計.md
│  ├─ 06_コーディング規約.md
│  ├─ 08_レビュー観点.md
│  └─ 09_環境構築手順書.md
├─ frontend/
├─ backend/
└─ .gitignore
```

## frontend起動手順

```bash
cd frontend
npm install
npm run dev
```

PowerShell の実行ポリシーで `npm` が失敗する場合は、以下を使用してください。

```bash
npm.cmd install
npm.cmd run dev
```

デフォルトでは `http://localhost:5173` で起動します。

## backend起動手順

事前にローカルの PostgreSQL を起動し、以下の接続先を用意してください。

- Database: `project_management_app`
- Username: `project_management_app`
- Password: `project_management_app`

起動コマンド:

```bash
cd backend
mvn spring-boot:run
```

Maven Wrapper を追加した場合は以下でも起動できます。

```bash
cd backend
./mvnw spring-boot:run
```

デフォルトでは `http://localhost:8080` で起動します。

## ローカルDB設定

Spring Boot の DB 接続設定は [application.yml](/abs/path/C:/Users/81802/Desktop/App/project-management-app/backend/src/main/resources/application.yml) に定義しています。

- Host: `localhost`
- Port: `5432`
- Database: `project_management_app`
- Username: `project_management_app`
- Password: `project_management_app`

必要に応じてローカル環境に合わせて変更してください。

## 開発ルール

- 実在企業名、社内情報、機密情報を含めない
- 追加要件を検討する場合は、まず `docs/01_要件定義.md` の「要件追加テンプレート」を使用する
- API 仕様変更時は `docs/04_API一覧.md` を更新する
- DB 設計変更時は `docs/05_DB設計.md` を更新する
- 画面追加時は `docs/03_画面一覧.md` を更新する
- 命名、ブランチ運用、コーディング規約は `docs/06_コーディング規約.md` を参照する
- レビュー観点は `docs/08_レビュー観点.md` を参照する
- 追加開発時は、Issue本文またはPR本文でdocs更新対象を明記する

## Dify利用

Difyで追加開発Issue案を作成する場合は、以下の統合Markdownファイルを入力してください。

```text
docs/00_Dify入力用_要件コンテキスト.md
```

Difyで追加Issue案を作成する場合も、`docs/01_要件定義.md` の要件追加テンプレートとdocs更新対象に沿って整理してください。
