import { createWebHashHistory, createRouter } from "vue-router";
import { checkAuth } from "./middleware/checkAuth.js";

const routes = [
  {
    path: "/",
    name: "index",
    component: () => import("./views/Index.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("./views/Login.vue"),
  },
  {
    path: "/signup",
    name: "signup",
    component: () => import("./views/SignUp.vue"),
  },
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("./views/MyInfo.vue"),
    beforeEnter: checkAuth,
  },
  {
    path: "/admin",
    name: "admin",
    component: () => import("./views/Admin.vue"),
    beforeEnter: checkAuth,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
