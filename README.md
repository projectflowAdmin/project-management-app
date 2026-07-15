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
├─ .github/
│  └─ ISSUE_TEMPLATE/
│     └─ feature_request.md
├─ docs/
│  ├─ 01_システム仕様書.md
│  ├─ 02_画面一覧.md
│  ├─ 03_API一覧.md
│  ├─ 04_DB設計.md
│  ├─ 05_コーディング規約.md
│  ├─ 06_レビュー観点.md
│  ├─ 07_環境構築手順書.md
│  └─ 08_ドキュメント更新ルール.md
├─ frontend/
├─ backend/
└─ .gitignore
```

## docsの役割

- `docs/02_画面一覧.md`: 現在存在する画面の一覧を管理する
- `docs/03_API一覧.md`: 現在提供しているAPI一覧を管理する
- 画面・APIの詳細仕様はGitHub Issueとソースコードを正とする

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

事前にローカルの PostgreSQL を起動し、`management_app` スキーマを作成してください。
初期構築SQLは `backend/src/main/resources/sql/init` を番号順に手動実行します。

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

## VS Code から同時起動する場合

VS Codeでは、リポジトリ内の `ProjectFlow.code-workspace` を開いてください。
親フォルダを開くと、親フォルダ側の `.vscode/launch.json` が参照される場合があります。

VS Code の「実行とデバッグ」で `Backend: Spring Boot` または `ProjectFlow: Backend + Frontend` を選択して開始すると、Backend と Frontend を同時に起動できます。
WindowsのPowerShell実行ポリシーに引っかからないよう、VS Codeタスクでは `npm.cmd run dev` を使用します。

この設定は `ProjectFlow.code-workspace`、`.vscode/launch.json`、`.vscode/tasks.json` で管理しています。

## ローカルDB設定

Spring Boot の DB 接続設定は `backend/src/main/resources/application.yml` に定義しています。

- Host: `localhost`
- Port: `5432`
- Database: `postgres`
- Schema: `management_app`
- Username: `postgres`
- Password: `postgres`

必要に応じてローカル環境に合わせて変更してください。

## 開発ルール

- 実在企業名、社内情報、機密情報を含めない
- 追加開発前に `docs/08_ドキュメント更新ルール.md` を確認する
- 追加仕様を検討する場合は、GitHub Issue Template の `feature_request.md` を使用する
- API追加時は `docs/03_API一覧.md` の一覧表へ1行追加する
- DB 設計変更時は `docs/04_DB設計.md` を更新する
- DB 変更SQLは `backend/src/main/resources/sql/changes` に採番して追加する
- 画面追加時は `docs/02_画面一覧.md` の一覧表へ1行追加する
- 命名、ブランチ運用、コーディング規約は `docs/05_コーディング規約.md` を参照する
- レビュー観点は `docs/06_レビュー観点.md` を参照する
- 追加開発時は、Issue本文またはPR本文でdocs更新対象を明記する

## Issue運用

新しい追加開発を開始する場合は、GitHub Issue の `feature_request.md` テンプレートを利用してください。

システム仕様書へ直接テンプレートを書き込まないでください。

## ProjectFlow の開発フロー

システム仕様書更新

↓

Difyへ入力

↓

追加Issue生成

↓

GitHub Issue作成

↓

実装

↓

Pull Request

↓

レビュー

↓

マージ

↓

システム仕様書更新

## Dify利用

Difyで追加開発Issue案を作成する場合は、以下のシステム仕様書を入力してください。

```text
docs/01_システム仕様書.md
```

Difyで追加Issue案を作成する場合も、`docs/01_システム仕様書.md` のDify利用ルールと `.github/ISSUE_TEMPLATE/feature_request.md` の構成に沿って整理してください。

Difyは、システム仕様書の「現在実装済み機能」と「追加開発バックログ」をもとに、実装済み機能と追加開発候補を判断します。
