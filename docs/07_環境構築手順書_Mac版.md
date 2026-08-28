# 07 環境構築手順書（Mac版）

作成日: 2026-08-02
更新日: 2026-08-02

## 1. 対象プロジェクト

この手順書は、macOSで `project-management-app` を初回構築し、ローカル環境で開発・起動確認するための手順です。

```text
project-management-app/
├─ backend/   Java 21 + Spring Boot + Maven
├─ frontend/  Vue 3 + Vite + TypeScript
└─ docs/      設計書・手順書
```

以降のコマンドは、macOS標準シェルのzshを前提に、ターミナルで実行します。
Dockerは使用しません。

Apple Silicon MacとIntel Macでは、一部のHomebrew配置先が異なります。
代表例は以下です。

| Mac | Homebrewの代表的な配置先 |
| --- | --- |
| Apple Silicon | `/opt/homebrew` |
| Intel Mac | `/usr/local` |

## 2. 必要なインストール資源

未インストールの場合は、以下をインストールしてください。

| 資源 | 用途 | 備考 |
| --- | --- | --- |
| Visual Studio Code | 開発エディタ | Java / Vue / Spring Boot 開発で使用 |
| Git | ソース管理 | `git clone`、差分確認、コミットで使用 |
| JDK 21 | Backend 実行・ビルド | `JAVA_HOME` をJDK 21に設定 |
| Apache Maven 3.9系 | Backend 依存関係解決・起動 | このリポジトリにはMaven Wrapperは含まれていないため、Maven本体が必要 |
| Node.js 22系 | Frontend 実行・ビルド | npm同梱版を使用 |
| PostgreSQL 16系 | ローカルDB | Homebrewで `postgresql@16` を導入 |
| psql | DB操作 | PostgreSQLの接続確認、初期SQL実行で使用 |

## 3. Homebrewの確認・インストール

Homebrewが利用できるか確認します。

```zsh
brew --version
```

未インストールの場合は、Homebrew公式サイトを確認してください。

```text
https://brew.sh/
```

Homebrewのインストールコマンドを記載・実行する場合は、公式サイトに掲載された最新のコマンドを確認してください。

Apple Siliconで `brew` が認識されない場合は、Homebrewの案内に従ってPATHを設定します。
例:

```zsh
echo 'eval "$(/opt/homebrew/bin/brew shellenv)"' >> ~/.zprofile
eval "$(/opt/homebrew/bin/brew shellenv)"
```

既に同じ設定が `~/.zprofile` に存在する場合は、重複追加しないでください。
設定後も認識されない場合は、ターミナルを再起動してください。

## 4. Gitの確認とリポジトリ取得

Gitが利用できるか確認します。

```zsh
git --version
```

Gitが利用できない場合は、Xcode Command Line ToolsまたはHomebrew経由のGitを導入してください。

Xcode Command Line Toolsの導入例:

```zsh
xcode-select --install
```

Homebrew経由で導入する例:

```zsh
brew install git
```

GitHubから `project-management-app` を取得します。

```zsh
git clone https://github.com/projectflowAdmin/project-management-app.git
cd project-management-app
```

アクセスできない場合は、以下を確認してください。

- GitHubアカウントでログインできていること
- 対象リポジトリへのアクセス権限があること
- GitHub Organizationに参加済みであること

## 5. VS Codeと拡張機能

Visual Studio Codeが未インストールの場合は、以下の公式ダウンロードページを確認してください。

```text
https://code.visualstudio.com/download
```

開発に必要なVS Code拡張機能は以下です。

| 拡張機能ID | 用途 |
| --- | --- |
| `ms-ceintl.vscode-language-pack-ja` | VS Code日本語化 |
| `vscjava.vscode-java-pack` | Java開発パック |
| `vmware.vscode-boot-dev-pack` | Spring Boot開発パック |
| `vue.volar` | Vue / TypeScriptサポート |

ターミナルから `code` コマンドを使用する場合は、VS Codeのコマンドパレットから以下を実行します。

```text
Shell Command: Install 'code' command in PATH
```

確認:

```zsh
code --version
```

VS Codeで開発する場合は、リポジトリ内の `ProjectFlow.code-workspace` を開いてください。
親フォルダを開くと、親フォルダ側の `.vscode/launch.json` が自動生成・参照される場合があります。

```zsh
code ProjectFlow.code-workspace
```

## 6. Java 21環境

JDK 21は、macOS用インストーラーでの導入を推奨します。Homebrewでも導入できますが、macOSからJDKを認識できるように追加設定が必要です。

### 6.1 macOS用インストーラーで導入する場合（推奨）

Oracle JDKやEclipse Temurinなど、信頼できるJDKディストリビューションのmacOS用インストーラーを使用してJDK 21をインストールします。

- [Oracle JDK 21ダウンロード](https://www.oracle.com/java/technologies/downloads/#java21)
- [Eclipse Temurin 21ダウンロード](https://adoptium.net/temurin/releases/?version=21)

MacのCPUに合わせてインストーラーを選択します。

| Mac | 選択するアーキテクチャ |
| --- | --- |
| Apple Silicon（M1 / M2 / M3 / M4など） | Arm 64 / AArch64 |
| Intel Mac | x64 / x86_64 |

ダウンロードした `.dmg` または `.pkg` を開き、インストーラーの案内に従ってインストールしてください。この方法では、Homebrew用のシンボリックリンク作成は不要です。

インストール後、「6.3 `JAVA_HOME` とPATHの設定」へ進みます。

### 6.2 Homebrewで導入する場合（任意）

HomebrewでJDK 21をインストールします。

```zsh
brew install openjdk@21
```

HomebrewでインストールしたJDKは、macOSがJDKを検索する場所へ自動登録されません。`/usr/libexec/java_home` からJDK 21を検出できるように、以下のシンボリックリンクを作成します。

```zsh
sudo ln -sfn "$(brew --prefix openjdk@21)/libexec/openjdk.jdk" /Library/Java/JavaVirtualMachines/openjdk-21.jdk
```

作成後、「6.3 `JAVA_HOME` とPATHの設定」へ進みます。

### 6.3 `JAVA_HOME` とPATHの設定

はじめに、macOSがJDK 21を検出できることを確認します。一覧にバージョン21のJDKが表示されることを確認してください。

```zsh
/usr/libexec/java_home -V
```

次に、`JAVA_HOME` とPATHを `~/.zshrc` へ追加します。既に同じ設定が `~/.zshrc` に存在する場合は、重複する行の `echo` は実行しないでください。

```zsh
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 21)' >> ~/.zshrc
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

設定反映後に、JDK 21が使用されていることを確認します。

```zsh
echo "$JAVA_HOME"
java -version
```

`java -version` にバージョン21が表示されれば設定完了です。

## 7. Maven 3.9系環境

Homebrewを利用してMavenを導入する例です。

```zsh
brew install maven
```

確認:

```zsh
mvn -version
```

Mavenは3.9系を想定します。
Homebrew管理の場合、原則として `MAVEN_HOME` の手動設定は不要です。
このリポジトリにはMaven Wrapperは含まれていないため、`mvn` コマンドが利用できる状態にしてください。

## 8. PostgreSQL 16系環境

HomebrewでPostgreSQL 16系を導入します。

```zsh
brew install postgresql@16
```

formula名やPATHの案内は環境によって異なる可能性があるため、以下も確認してください。

```zsh
brew info postgresql@16
```

PATH設定が必要な場合の例です。

Apple Silicon:

```zsh
export PATH="/opt/homebrew/opt/postgresql@16/bin:$PATH"
```

Intel Mac:

```zsh
export PATH="/usr/local/opt/postgresql@16/bin:$PATH"
```

永続化する場合は、同じ設定が存在しないことを確認してから `~/.zshrc` へ追加してください。

PostgreSQLサービスを起動します。

```zsh
brew services start postgresql@16
```

状態確認:

```zsh
brew services list
```

停止:

```zsh
brew services stop postgresql@16
```

バージョン確認:

```zsh
psql --version
```

Homebrew版PostgreSQLでは、初期状態で `postgres` ロールまたは `postgres` データベースが存在しない場合があります。
最初に存在確認を行い、必要な場合だけ作成してください。

存在確認:

```zsh
psql -h localhost -p 5432 -d postgres
```

必要な場合の作成例:

```zsh
createuser -s postgres
createdb -O postgres postgres
```

パスワード設定例:

```sql
ALTER USER postgres WITH PASSWORD 'postgres';
```

以下に注意してください。

- 既にロールやDBが存在する場合は再作成しない
- 実際のパスワードをGitHubへ登録しない
- 社内チャットやドキュメントへ実際のパスワードを記載しない
- 学習用ローカル環境以外では安全な秘密情報管理を利用する

接続確認:

```zsh
psql -h localhost -p 5432 -U postgres -d postgres
```

## 9. DB接続設定

DB接続情報は、Git管理対象外の `backend/src/main/resources/application-local.yml` に設定します。

以下の手順でローカル用設定ファイルを作成してください。

1. VS Codeのエクスプローラーで `backend/src/main/resources` ディレクトリを開く
2. `resources` ディレクトリを右クリックし、「新しいファイル」を選択する
3. ファイル名に `application-local.yml` を入力する
4. 下記のYAMLを記載して保存する

```text
backend/
└─ src/
   └─ main/
      └─ resources/
         ├─ application.yml
         └─ application-local.yml
```

`application-local.yml` に以下を記載します。

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres?currentSchema=management_app
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
```

上記はローカル開発用の設定例です。PostgreSQLの `postgres` ユーザーに別のパスワードを設定している場合は、`password` を実際の値に変更します。

`application-local.yml` は `.gitignore` の対象であり、Gitへコミットしないでください。このファイルは `resources` 配下にあるため、ローカルでJARを作成するとJAR内に含まれます。`application-local.yml` を含むローカルビルドのJARは共有・公開しないでください。

`application.yml` で `local` を既定プロファイルとしているため、ローカル起動時に追加のプロファイル指定は不要です。共有環境や本番環境では `application-local.yml` を使用せず、`SPRING_DATASOURCE_URL`、`SPRING_DATASOURCE_USERNAME`、`SPRING_DATASOURCE_PASSWORD` を環境側から設定してください。

## 10. DB初期構築SQL

このプロジェクトでは、Backend起動時にSQLは自動実行しません。
初回構築時は、Backend起動前に `backend/src/main/resources/sql/init` 配下のSQLを `001` から番号順に手動実行してください。

```yaml
spring:
  sql:
    init:
      mode: never
```

Flyway / Liquibase / 独自Java migration runner は使用しません。

リポジトリのルートディレクトリで以下を実行します。

```zsh
psql -h localhost -p 5432 -U postgres -d postgres -f backend/src/main/resources/sql/init/001_create_schema.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend/src/main/resources/sql/init/002_create_projects_table.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend/src/main/resources/sql/init/003_create_issues_table.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend/src/main/resources/sql/init/004_create_indexes.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend/src/main/resources/sql/init/005_create_sql_history.sql
psql -h localhost -p 5432 -U postgres -d postgres -f backend/src/main/resources/sql/init/006_insert_initial_data.sql
```

実行後、以下でテーブルが作成されていることを確認できます。

```zsh
psql -h localhost -p 5432 -U postgres -d postgres
```

psql接続後に以下を実行します。

```sql
\dt management_app.*
```

初期データの件数を確認する場合は、psql接続後に以下を実行します。

```sql
SELECT COUNT(*) FROM management_app.projects;
SELECT COUNT(*) FROM management_app.issues;
```

SQL適用履歴を確認する場合は、psql接続後に以下を実行します。

```sql
SELECT
    script_no,
    script_name,
    description,
    executed_at,
    executed_by
FROM management_app.sql_history
ORDER BY script_no;
```

## 11. Node.js 22系環境

HomebrewでNode.js 22系を導入する例です。

```zsh
brew install node@22
```

Homebrewでインストールした `node@22` は、通常のPATHへ自動追加されません。はじめにHomebrewの案内を確認します。

```zsh
brew info node@22
```

次に、`node@22` の `bin` ディレクトリをPATHに追加し、設定を反映します。既に同じPATH設定が `~/.zshrc` に存在する場合は、`echo` は実行しないでください。

```zsh
echo 'export PATH="$(brew --prefix node@22)/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
```

設定反映後に、Node.jsとnpmのバージョンを確認します。

```zsh
node -v
npm -v
```

`node -v` にバージョン22が表示されれば設定完了です。

依存関係をインストールします。

```zsh
cd frontend
npm ci
```

このリポジトリには `frontend/package-lock.json` が含まれているため、初回構築やCIに近い確認では `npm ci` を使用します。
依存関係を追加・更新する場合は、変更内容を確認したうえで `npm install` を使用してください。

Macでは `npm.cmd` を使用しません。

## 12. Backend起動

リポジトリルートから以下を実行します。

```zsh
cd backend
mvn spring-boot:run
```

別のターミナルまたはブラウザで以下にアクセスして確認します。

```text
http://localhost:8080/api/health
```

このプロジェクトでは独自のHealth APIとして `/api/health` を使用します。
その他に、Spring BootとDBの状態確認にはSpring Boot Actuatorの `/actuator/health` も利用できます。
正常な場合は、以下のJSONが返ります。

```json
{"status":"UP"}
```

### 12.1 Talend API Testerの導入とAPI確認

GET以外のメソッドやJSONリクエストボディを含むAPIの手動確認には、Chrome拡張機能の `Talend API Tester - Free Edition` を使用します。

1. Chromeで以下のChromeウェブストアを開きます。
   - [Talend API Tester - Free Edition](https://chromewebstore.google.com/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm)
2. 「Chromeに追加」を押し、表示される権限を確認してインストールします。
3. Chromeの拡張機能一覧から `Talend API Tester` を起動します。
4. 以下のリクエストを設定して `Send` を押します。

| 項目 | 設定値 |
| --- | --- |
| Method | `GET` |
| URL | `http://localhost:8080/api/health` |
| Request Body | なし |

HTTPステータス `200 OK` と以下のJSONが返れば、独自Health APIの確認は完了です。

```json
{"status":"UP"}
```

URLは `/api/health` です。`/api/v1/health` ではないため、保存済みリクエストを再利用する場合もURLを確認してください。

Talend API Testerは手動でのAPI動作確認用です。`mvn test` による自動テストの代わりにはなりません。

> **注意:** ローカル開発用データのみを使用し、実際のパスワード、APIキー、顧客情報などは入力しないでください。利用前に所属組織のブラウザ拡張機能ポリシーも確認してください。

## 13. Frontend起動

別のターミナルで以下を実行します。

```zsh
cd frontend
npm run dev
```

起動後、ブラウザで以下にアクセスします。

```text
http://localhost:5173
```

ブラウザで `http://localhost:5173` を開いた後、以下を確認してください。

- ダッシュボード画面が表示されること
- プロジェクト数、課題数、ステータス別件数が表示されること
- 最近更新された課題が表示されること
- 課題一覧画面で初期データが表示されること
- プロジェクト一覧画面で初期データが表示されること

表示されない場合は、Backendが起動していること、`/api/health` が正常に返ること、DB初期データが投入されていることを確認してください。

## 14. VS Codeからの同時起動

`.vscode/launch.json`、`.vscode/tasks.json`、`ProjectFlow.code-workspace` には、BackendとFrontendを同時起動するための構成を用意しています。

VS Codeの「実行とデバッグ」から `ProjectFlow: Backend + Frontend` を選択すると、以下を同時に起動できます。

- Backend: Spring Boot
- Frontend: `npm run dev`

Frontend起動タスクは、Windowsでは `npm.cmd run dev` を使用し、Macでは `npm run dev` を使用するOS別設定になっています。
既存のTask名、problemMatcher、Background Taskの判定、Compound Launch、Backend起動設定は維持しています。

Spring Boot Dashboardのアプリ単体起動ボタンは、基本的にBackend単体の起動に使用します。
Frontendも同時に起動したい場合は、`ProjectFlow: Backend + Frontend` を使用してください。

## 15. 起動順序

ローカルで動作確認する場合は、以下の順序で起動します。

1. PostgreSQLを起動する
2. 初回構築時はDB初期構築SQLを番号順に実行する
3. Backendを起動する
4. `http://localhost:8080/api/health` を確認する
5. Frontendを起動する
6. `http://localhost:5173` を確認する

## 16. ビルド・テスト

Backendのテストを実行します。

```zsh
cd backend
mvn test
```

Backendの自動テストでも `application-local.yml` のローカルPostgreSQL設定を使用します。テスト専用DBの構築は行いません。今後RepositoryやMapperのテストを追加する場合は、テストごとに必要なデータを作成し、テスト後に後片付けを行ってください。

Frontendのビルド確認を実行します。

```zsh
cd frontend
npm run build
```

## 17. よくあるトラブル

### `brew` が認識されない

以下を確認してください。

- Homebrewがインストールされていること
- Apple Siliconでは `/opt/homebrew`、Intel Macでは `/usr/local` が代表的な配置先であること
- `~/.zprofile` にHomebrewのshellenv設定が必要か確認すること
- 同じ設定を重複追加していないこと
- ターミナルを再起動していること

### `code` が認識されない

VS Codeのコマンドパレットから以下を実行してください。

```text
Shell Command: Install 'code' command in PATH
```

実行後、ターミナルを開き直して `code --version` を確認してください。

### `java -version` がJava 21ではない

以下を確認してください。

- `/usr/libexec/java_home -V` でJDK 21が表示されること
- `JAVA_HOME` がJDK 21を指していること
- `~/.zshrc` の設定が正しいこと
- `source ~/.zshrc` を実行していること
- ターミナルを再起動していること

### `mvn` が認識されない

以下を確認してください。

- `brew install maven` を実行済みであること
- `brew --prefix maven` で配置先を確認できること
- PATHが正しく設定されていること
- ターミナルを再起動していること

### `psql` が認識されない

以下を確認してください。

- `brew info postgresql@16` の案内を確認していること
- PostgreSQLのbinディレクトリがPATHに含まれていること
- Apple SiliconとIntel MacでPATH設定が異なること
- ターミナルを再起動していること

### PostgreSQLへ接続できない

以下を確認してください。

- `brew services list` で `postgresql@16` が起動していること
- ポート `5432` で待ち受けていること
- `postgres` ロールが存在すること
- `postgres` データベースが存在すること
- パスワードが正しいこと
- PostgreSQLの `postgres` ユーザーのパスワードと `application-local.yml` の `password` が一致していること
- 初期SQLを番号順に実行済みであること

### VS CodeからFrontendが起動しない

以下を確認してください。

- `.vscode/tasks.json` のFrontendタスクがMacでは `npm run dev` を使用していること
- Windows専用の `npm.cmd` がMac用設定へ混入していないこと
- `npm ci` が完了していること
- `frontend` が作業ディレクトリになっていること

### CORSエラーが出る

Frontendの起動URLが `http://localhost:5173` であることを確認してください。
BackendのCORS設定では、以下が許可されています。

```yaml
projectmanagementapp:
  cors:
    allowed-origins:
      - http://localhost:5173
```

Backendが起動していること、`http://localhost:8080/api/health` が `{"status":"UP"}` を返すことも確認してください。
