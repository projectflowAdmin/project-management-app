# ProjectFlow

## 1. ProjectFlowについて

ProjectFlow は、Backlog や Jira のようなプロジェクト管理ツールを参考にした学習用Webアプリケーションです。

2〜5年目程度のエンジニアが、要件定義、GitHub Issue作成、実装、Pull Request、レビュー、docs更新、リリースに近い流れを疑似プロジェクトとして経験できるように設計しています。

名称・画面構成・文言・配色は独自化し、特定サービスの完全コピーにはしません。

このリポジトリでは、GitHub Issues、Pull Request、レビュー、docs更新、Difyを使って追加開発を進めます。社内情報、顧客情報、個人情報、機密情報は扱いません。

## 2. 初めて参加する方へ

初めて参加する場合は、まず以下を確認してください。

| 目的 | 参照先 |
|---|---|
| 環境構築 | [docs/07_環境構築手順書.md](docs/07_環境構築手順書.md) |
| 現在の仕様 | [docs/01_システム仕様書.md](docs/01_システム仕様書.md) |
| 開発ルール | [docs/05_開発ルール.md](docs/05_開発ルール.md) |
| ドキュメント更新方法 | [docs/08_ドキュメント更新ルール.md](docs/08_ドキュメント更新ルール.md) |
| レビュー基準 | [docs/06_レビュー観点.md](docs/06_レビュー観点.md) |

参画から開発開始までの流れ:

1. GitHubアカウントを用意する。
2. GitHub Organizationへの招待を受ける。
3. リポジトリをcloneする。
4. [docs/07_環境構築手順書.md](docs/07_環境構築手順書.md) を参照して環境構築する。
5. ローカルでFrontendとBackendの起動確認をする。
6. [docs/01_システム仕様書.md](docs/01_システム仕様書.md) で現在の機能を確認する。
7. [docs/05_開発ルール.md](docs/05_開発ルール.md) を確認する。
8. 担当Issueを確認する。
9. ブランチを作成して実装を開始する。

## 3. 役割

ここでの役割は固定の役職ではなく、疑似プロジェクト内でその時に担当する役割です。1人が複数の役割を兼ねる場合もあります。

### 3.1 メンバー

- 担当Issueの背景、対応内容、受入条件を確認する。
- 不明点を実装前に確認する。
- 担当ブランチで実装する。
- 必要なテストと動作確認を実施する。
- 関連docsを更新する。
- Pull Requestを作成する。
- レビュー指摘へ対応する。

### 3.2 リーダー

- [docs/01_システム仕様書.md](docs/01_システム仕様書.md) で現在の状態を確認する。
- 必要に応じてDifyから追加Issue案を作成する。
- Dify出力をそのまま採用せず、妥当性と粒度を確認する。
- GitHub Issueを作成・整理する。
- 担当者を決める。
- 仕様上の不明点を解消する。
- レビュー担当を調整する。
- マージ可否を判断する。
- 実装後にシステム仕様書が最新化されていることを確認する。

### 3.3 レビュアー

- 実装とIssueの受入条件を確認する。
- [docs/06_レビュー観点.md](docs/06_レビュー観点.md) を参考にレビューする。
- コードとdocsの整合性を確認する。
- 動作確認・テスト内容を確認する。
- 必要な修正を具体的に伝える。

## 4. 開発の全体フロー

```text
現在のシステム仕様を確認
        ↓
Difyで追加機能案を生成（必要な場合）
        ↓
リーダーが内容を確認
        ↓
GitHub Issueを作成
        ↓
担当者を決定
        ↓
developからfeature/bugfixブランチを作成
        ↓
実装・テスト
        ↓
関連docs更新
        ↓
Pull Request作成
        ↓
レビュー・修正
        ↓
feature/bugfix → developへマージ
        ↓
developで動作確認
        ↓
develop → mainへマージ
        ↓
システム仕様書を最新化
```

Issue作成前は、システム仕様書を毎回変更するのではなく、現在のシステム仕様を確認します。追加開発の詳細はGitHub Issueで管理します。実装完了後に、現在実装済みの状態を [docs/01_システム仕様書.md](docs/01_システム仕様書.md) へ反映します。

## 5. 初回セットアップ

詳細手順は [docs/07_環境構築手順書.md](docs/07_環境構築手順書.md) を参照してください。READMEでは概要だけを示します。

1. GitHubからリポジトリをcloneする。
2. Java、Maven、Node.js、PostgreSQLなど必要ソフトウェアをインストールする。
3. PostgreSQLを起動し、初期構築SQLを実行する。
4. BackendとFrontendを起動する。
5. ブラウザで `http://localhost:5173` を開き、画面表示とAPI接続を確認する。

## 6. 通常の開発手順

### 6.1 Issueを確認・作成する

既存Issueを担当する場合は、背景、対応内容、受入条件、テスト観点を確認します。

新規Issueを作成する場合は [.github/ISSUE_TEMPLATE/feature_request.md](.github/ISSUE_TEMPLATE/feature_request.md) を使用します。システム仕様書へIssueの詳細を直接書き込まないでください。

### 6.2 担当とブランチを決める

リーダーが担当者を決め、担当者はIssueに対応するブランチをdevelopから作成します。機能開発は `feature/{Issue番号}-{機能名}`、不具合修正は `bugfix/{Issue番号}-{概要}` とし、機能名・概要は英小文字のkebab-caseで命名してください。

実装方針やブランチ運用は [docs/05_開発ルール.md](docs/05_開発ルール.md) を参照してください。

### 6.3 実装する

- Issueの対応範囲を守る。
- 仕様変更が必要な場合は独断で進めず、Issueへ記録して確認する。
- 必要なテストと動作確認を実施する。
- 社内情報、顧客情報、個人情報、機密情報を含めない。

### 6.4 docsを更新する

docs更新対象は [docs/08_ドキュメント更新ルール.md](docs/08_ドキュメント更新ルール.md) に従って判断します。すべてのdocsを形式的に更新する必要はありません。

- 画面追加時は [docs/02_画面一覧.md](docs/02_画面一覧.md) の一覧表へ1行追加する。
- API追加時は [docs/03_API一覧.md](docs/03_API一覧.md) の一覧表へ1行追加する。
- DB変更時は [docs/04_DB設計.md](docs/04_DB設計.md) と `backend/src/main/resources/sql/changes` の採番SQLを更新する。
- 実装完了後は [docs/01_システム仕様書.md](docs/01_システム仕様書.md) の現在実装済み機能を更新する。

画面・APIの詳細仕様はGitHub Issueで管理し、実装内容の正はソースコードです。

### 6.5 Pull Requestを作成する

現時点では `.github/PULL_REQUEST_TEMPLATE.md` はありません。Pull Requestには最低限以下を記載してください。

- 対応Issue
- 変更内容
- 動作確認結果
- テスト結果
- docs更新内容、または更新不要の理由
- レビューしてほしい点

### 6.6 レビュー・修正・マージ

- レビュアーはIssue、実装、docsの整合性を確認する。
- 指摘対応後に再レビューを依頼する。
- レビュー完了後、マージ権限を持つ担当者がfeature/bugfixブランチをdevelopへマージする。
- developで動作確認し、問題がなければPull Requestを経由してdevelopをmainへマージする。
- mainへ直接コミット・直接マージしない。
- マージ後にIssueを完了状態へする。
- [docs/01_システム仕様書.md](docs/01_システム仕様書.md) が現在状態を表しているか確認する。

## 7. Difyを使った追加Issue作成

Difyで追加開発Issue案を作成する場合は、[docs/01_システム仕様書.md](docs/01_システム仕様書.md) を入力します。

Difyは現在実装済みの機能と追加開発候補をもとに、追加機能案を生成します。出力は提案であり、そのままIssue化せず、リーダーが以下を確認します。

- 実装済み機能と重複していないか。
- ProjectFlowの目的・技術構成・学習用途から逸脱していないか。
- 1〜3日程度で実装可能な粒度か。
- 複数Issueに分割すべき大きさではないか。
- [.github/ISSUE_TEMPLATE/feature_request.md](.github/ISSUE_TEMPLATE/feature_request.md) の形式に整理できているか。

DifyのURLはREADMEへ直接固定記載せず、管理者から共有します。Difyへ社内情報、顧客情報、個人情報、機密情報を入力しないでください。

## 8. ローカル起動

### 8.1 VS Codeから同時起動

VS Codeでは、リポジトリ内の `ProjectFlow.code-workspace` を開いてください。親フォルダを開くと、親フォルダ側の `.vscode/launch.json` が参照される場合があります。

VS Code の「実行とデバッグ」で `Backend: Spring Boot` または `ProjectFlow: Backend + Frontend` を選択して開始すると、Backend と Frontend を同時に起動できます。

WindowsのPowerShell実行ポリシーに引っかからないよう、VS Codeタスクでは `npm.cmd run dev` を使用します。この設定は `ProjectFlow.code-workspace`、`.vscode/launch.json`、`.vscode/tasks.json` で管理しています。

### 8.2 Frontendを個別起動

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

### 8.3 Backendを個別起動

事前にローカルの PostgreSQL を起動し、`management_app` スキーマを作成してください。初期構築SQLは `backend/src/main/resources/sql/init` を番号順に手動実行します。

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

Spring Boot の DB 接続設定は `backend/src/main/resources/application.yml` に定義しています。

| 項目 | 値 |
|---|---|
| Host | `localhost` |
| Port | `5432` |
| Database | `postgres` |
| Schema | `management_app` |
| Username | `postgres` |
| Password | `postgres` |

必要に応じてローカル環境に合わせて変更してください。

## 9. 技術構成

| 分類 | 技術 |
|---|---|
| Frontend | Vue 3 + Vite + TypeScript + Vue Router |
| Backend | Java 21 + Spring Boot 3 + Maven |
| Database | PostgreSQL 16 |
| DBアクセス | MyBatis |
| SQL管理 | Mapper XML、`backend/src/main/resources/sql/init`、`backend/src/main/resources/sql/changes` |
| タスク管理 | GitHub Issues / GitHub Projects |
| AI支援 | Dify |

## 10. ディレクトリ構成

```text
project-management-app/
├─ README.md
├─ ProjectFlow.code-workspace
├─ .github/
│  ├─ ISSUE_TEMPLATE/
│  │  └─ feature_request.md
│  └─ modernize/
├─ .vscode/
│  ├─ launch.json
│  ├─ settings.json
│  └─ tasks.json
├─ docs/
│  ├─ 01_システム仕様書.md
│  ├─ 02_画面一覧.md
│  ├─ 03_API一覧.md
│  ├─ 04_DB設計.md
│  ├─ 05_開発ルール.md
│  ├─ 06_レビュー観点.md
│  ├─ 07_環境構築手順書.md
│  └─ 08_ドキュメント更新ルール.md
├─ frontend/
├─ backend/
│  └─ src/main/resources/sql/
│     ├─ init/
│     ├─ changes/
│     └─ README.md
└─ .gitignore
```

## 11. ドキュメント一覧

| ファイル | 用途 |
|---|---|
| [docs/01_システム仕様書.md](docs/01_システム仕様書.md) | 現在のシステム仕様と実装済み機能 |
| [docs/02_画面一覧.md](docs/02_画面一覧.md) | 現在存在する画面一覧 |
| [docs/03_API一覧.md](docs/03_API一覧.md) | 現在提供するAPI一覧 |
| [docs/04_DB設計.md](docs/04_DB設計.md) | DB構成と変更ルール |
| [docs/05_開発ルール.md](docs/05_開発ルール.md) | 実装・開発ルール |
| [docs/06_レビュー観点.md](docs/06_レビュー観点.md) | レビュー時の確認基準 |
| [docs/07_環境構築手順書.md](docs/07_環境構築手順書.md) | 初回環境構築と起動方法 |
| [docs/08_ドキュメント更新ルール.md](docs/08_ドキュメント更新ルール.md) | 仕様変更時のdocs更新方法 |

## 12. 開発上の注意事項

- 社内情報、顧客情報、個人情報、機密情報を含めない。
- 認証情報、DBパスワード、APIキーをコミットしない。
- Issueにない大きな変更を独断で行わない。
- コードと関連docsは原則同じPull Requestで更新する。
- 適用済みのDB変更SQLを直接修正しない。
- 不明点は推測せず確認する。
- 追加開発時は、Issue本文またはPull Request本文でdocs更新対象を明記する。
