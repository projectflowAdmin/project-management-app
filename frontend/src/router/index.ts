import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/Login/LoginView.vue";
import DashboardView from "../views/Dashboard/DashboardView.vue";
import ProjectListView from "../views/ProjectList/ProjectListView.vue";
import ProjectFormView from "../views/ProjectForm/ProjectFormView.vue";
import IssueListView from "../views/IssueList/IssueListView.vue";
import IssueFormView from "../views/IssueForm/IssueFormView.vue";
import IssueDetailView from "../views/IssueDetail/IssueDetailView.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", redirect: "/login" },
    { path: "/login", name: "login", component: LoginView, meta: { publicLayout: true } },
    { path: "/dashboard", name: "dashboard", component: DashboardView },
    { path: "/projects", name: "projects", component: ProjectListView },
    { path: "/projects/new", name: "project-new", component: ProjectFormView },
    { path: "/projects/:id/edit", name: "project-edit", component: ProjectFormView, props: true },
    { path: "/issues", name: "issues", component: IssueListView },
    { path: "/issues/new", name: "issue-new", component: IssueFormView },
    { path: "/issues/:id", name: "issue-detail", component: IssueDetailView, props: true },
    { path: "/issues/:id/edit", name: "issue-edit", component: IssueFormView, props: true }
  ]
});

export default router;
