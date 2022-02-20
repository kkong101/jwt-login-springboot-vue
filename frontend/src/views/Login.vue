<template>
  <div class="container column justify-center">
    <h3 class="title">Login Page</h3>
    <q-input v-model="userId" label="아이디" :dense="dense" />
    <q-input
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
</template>
<script setup>
  import { onMounted, ref, getCurrentInstance } from "vue";
  const instance = getCurrentInstance();
  const axios = instance.appContext.config.globalProperties.$axios;

  const userId = ref("");
  const userPwd = ref("");

  const postLogin = async () => {
    const config = {
      headers: {
        test: "hello",
      },
    };
    const url = "/api/login/checkUser";
    const data = {
      user_id: userId.value,
      user_pwd: userPwd.value,
    };
    const res = await axios.post(url, data, config);
    console.log(res);

    userId.value = "";
    userPwd.value = "";
  };
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
