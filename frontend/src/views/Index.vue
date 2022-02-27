<template>
  <div class="container column justify-center">
    <h3>Index Page</h3>
    <q-btn color="primary" to="/login">로그인 페이지 이동</q-btn>
    <q-btn class="q-mt-md" color="primary" @click="logout()">로그 아웃</q-btn>
    <q-btn class="q-mt-md" color="primary" to="/admin">관리자 페이지</q-btn>
    <q-btn class="q-mt-md" color="primary" to="/mypage">마이 페이지</q-btn>
    <h6 class="q-my-lg" :style="isJwt ? 'color: red' : ''">
      현재 로그인 상태 : {{ isJwt ? "로그인 상태 !!" : "Not Login" }}
    </h6>
    <div v-if="isJwt">
      <h6 class="q-my-lg">계정 아이디 - {{ userId }}</h6>
      <h6 class="q-my-lg">계정 이름 - {{ userName }}</h6>
      <h6 class="q-my-lg">Access 토큰 만기 시간</h6>
      <h6 class="q-my-lg">{{ expiredDate }}</h6>
      <h6 style="margin: 10px 0px; width: 300px; overflow-wrap: anywhere">
        Access Token - {{ token }}
      </h6>
    </div>
  </div>
</template>

<script>
  import { ref, defineComponent } from "vue";
  import { useStore } from "vuex";
  import { Cookies } from "quasar";
  import { jwtParse } from "../utils/index.js";

  export default defineComponent({
    name: "Index",
    data() {
      return {
        loginState: ref("Not Login"),
      };
    },
    setup() {
      const store = useStore();
      const userId = store.getters.getUserId;
      const userName = store.getters.getUserName;
      const isJwt = store.getters.getIsJwt;

      const logout = () => store.commit("setLogout");

      const token = Cookies.get("AccessToken");

      const expiredDate = new Date(jwtParse(token).exp * 1000);

      return { userId, userName, isJwt, token, logout, expiredDate };
    },
    methods: {
      logout() {
        this.logout();
      },
    },
  });
</script>
<style scoped>
  .container {
    margin: 10px 0px;
    align-content: center;
  }
</style>
