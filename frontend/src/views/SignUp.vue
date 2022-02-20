<template>
  <div class="container column justify-center">
    <h3>회원가입 Page</h3>
    <q-input
      @keydown="getDuplicateIdState()"
      v-model="userId"
      :label="idLabel"
      :dense="dense"
    />
    <q-input
      v-model="userPwd"
      type="password"
      label="비밀번호"
      :dense="dense"
    />

    <!-- <q-input
      v-model="userPwd"
      type="password"
      label="비밀번호 확인"
      :dense="dense"
    /> -->

    <q-btn
      :disable="isDuplicate"
      @click="postSignUp()"
      class="q-mt-md"
      color="primary"
      >회원가입</q-btn
    >
    <DialogPopUp
      title="회원가입 성공!"
      :isPopupOpen="isModalOpen"
      reDirect="/login"
    />
    <q-btn to="/login" class="q-mt-md" color="primary">취소</q-btn>
  </div>
</template>
<script>
  import DialogPopUp from "../components/popup/DialogPopUp.vue";
  import { ref, defineComponent } from "vue";
  import { debounce } from "quasar";

  export default defineComponent({
    name: "SignUp",
    data() {
      return {
        userId: ref(""),
        userPwd: ref(""),
        idLabel: ref("아이디"),
        isModalOpen: ref(false),
        isDuplicate: ref(false),
      };
    },
    setup() {},
    created() {
      this.getDuplicateIdState = debounce(this.getDuplicateIdState, 1000);
    },
    methods: {
      async getDuplicateIdState() {
        if (this.userId === "") {
          this.idLabel = "아이디";
          this.isDuplicate = false;
          return;
        }
        const config = {
          headers: {
            test: "hello",
          },
        };
        const url = "/api/login/getUser";
        const data = {
          user_id: this.userId,
          user_pwd: this.userPwd,
        };
        const res = await this.$axios.post(url, data, config);
        console.log(res);
        if (res.data.data != null) {
          this.idLabel = "아이디 - 사용불가능한 아이디입니다.";
          this.isDuplicate = true;
        } else {
          this.idLabel = "아이디 - 사용가능한 아이디입니다.";
          this.isDuplicate = false;
        }
      },
      async postSignUp() {
        if (isDuplicate) {
        }

        const config = {
          headers: {
            test: "hello",
          },
        };
        const url = "/api/login/addUser";
        const data = {
          user_id: this.userId,
          user_pwd: this.userPwd,
        };
        const res = await this.$axios.post(url, data, config);
        console.log(res);
        if (res.data.data?.code == 200) {
          isModalOpen.value = true;
        }
      },
    },
  });
</script>
<style scoped>
  .container {
    margin: 50px 0px;
    align-content: center;
  }
</style>
