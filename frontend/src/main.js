import { createApp } from "vue";
import App from "./App.vue";

import router from "./router";
import axios from "axios";
import store from "./store/index.js";
import { Quasar } from "quasar";
import quasarUserOptions from "./quasar-user-options";
import "@quasar/extras/material-icons/material-icons.css";
import "quasar/src/css/index.sass";

const app = createApp(App).use(Quasar, quasarUserOptions);

app.use(router).use(store).mount("#app");

// 전역변수 등록
app.config.globalProperties.$axios = axios;
