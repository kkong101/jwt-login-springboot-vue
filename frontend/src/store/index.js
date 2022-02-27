import { createStore } from "vuex";
import { Cookies } from "quasar";

export default createStore({
  state: {
    userId: "",
    userName: "",
    isJwt: false,
  },
  getters: {
    getUserId(state) {
      return state.userId;
    },
    getUserName(state) {
      return state.userName;
    },
    getIsJwt(state) {
      return state.isJwt;
    },
  },
  mutations: {
    setUserInfo(state, obj) {
      state.userId = obj.id;
      state.userName = obj.name;
      state.isJwt = true;
      Cookies.set("AccessToken", obj.token);
    },
    setLogout(state) {
      state.userId = "";
      state.userName = "";
      state.isJwt = false;
      Cookies.remove("AccessToken");
    },
  },
  actions: {},
  modules: {},
});
