<template>
  <div class="container column justify-center">
    <h3>마이 페이지</h3>
    <h6>로그인을 해야지 접근 가능한 페이지 입니다.</h6>
    <h6>{{ text }}</h6>
  </div>
</template>
<script>
  import { defineComponent, ref } from "vue";

  export default defineComponent({
    setup() {},
    data() {
      return {
        text: ref(""),
      };
    },
    async beforeCreate() {
      const cookie = this.$cookie.get("AccessToken");
      if (!cookie) {
        window.alert("로그인을 먼저 진행해 주세요.");
        this.$router.replace("/login");
      }

      const res = await this.$api.getMyPage();
      this.text = res.data;
    },
  });
</script>
