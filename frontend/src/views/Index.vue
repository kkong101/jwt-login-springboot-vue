<template>
  <div class="container column justify-center">
    <h3>Index Page {{ test }}</h3>
    <q-btn color="primary" to="/login">로그인 페이지 이동</q-btn>
    <h6>현재 로그인 상태 : {{ loginState }}</h6>
  </div>
</template>

<script setup>
  const loginState = "Not Login";
</script>
<style scoped>
  .container {
    margin: 50px 0px;
    align-content: center;
  }
</style>
