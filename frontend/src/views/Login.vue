<template>
  <div class="container column justify-center">
    <h3 class="title">Login Page</h3>
    <q-input v-model="userId" label="아이디" :dense="dense" />
    <q-input
      @keyup.enter="postLogin()"
      v-model="userPwd"
      type="password"
      label="비밀번호"
      :dense="dense"
    />
    <q-btn @click="postLogin()" class="q-mt-md" color="primary" to="/login"
      >로그인</q-btn
    >
    <q-btn to="/signup" class="q-mt-md" color="primary">회원가입</q-btn>
  </div>
  <DialogPopUp :title="modalTxt" :isPopupOpen="isModalOpen" reDirect="/" />
</template>
<script>
  import DialogPopUp from "../components/popup/DialogPopUp.vue";
  import { ref, defineComponent, computed } from "vue";
  import { useStore } from "vuex";

  export default defineComponent({
    name: "Login",
    components: {
      DialogPopUp,
    },
    data() {
      return {
        userId: ref(""),
        userPwd: ref(""),
        modalTxt: ref(""),
        isModalOpen: ref(false),
      };
    },
    setup() {
      const store = useStore();

      const setLogin = (obj) => {
        store.commit("setUserInfo", obj);
      };
      return { setLogin };
    },
    methods: {
      async postLogin() {
        const data = {
          user_id: this.userId,
          user_pwd: this.userPwd,
        };
        const res = await this.$api.postLogin(data);
        if (res.data) {
          // 쿠키에 access 토큰 등록

          console.log(res.data);

          this.setLogin({
            id: res.data.data.id,
            name: res.data.data.id,
            token: res.data.data.accessToken,
          });

          this.modalTxt = "로그인 성공!";
          this.isModalOpen = true;
        }

        this.userId = "";
        this.userPwd = "";
      },
    },
  });
</script>
<style scoped>
  .container {
    margin: 50px 0px;
    align-content: center;
  }
  .title {
    color: blue;
  }
</style>
