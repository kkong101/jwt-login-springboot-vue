import { createApp } from "vue";
import App from "./App.vue";

import router from "./router";
import store from "./store/index.js";
import { Quasar, Cookies } from "quasar";
import quasarUserOptions from "./quasar-user-options";
import API from "./api/index.js";
import "@quasar/extras/material-icons/material-icons.css";
import "quasar/src/css/index.sass";

const app = createApp(App).use(Quasar, quasarUserOptions);

app.use(router).use(store).mount("#app");

app.config.globalProperties.$api = API;
app.config.globalProperties.$cookie = Cookies;
