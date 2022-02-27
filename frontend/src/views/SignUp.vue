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
      :title="modalTxt"
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
    components: {
      DialogPopUp,
    },
    data() {
      return {
        userId: ref(""),
        userPwd: ref(""),
        idLabel: ref("아이디"),
        modalTxt: ref(""),
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
        const data = {
          user_id: this.userId,
          user_pwd: this.userPwd,
        };
        const res = await this.$api.postIdcheck(data);
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
        if (this.isDuplicate || this.userId == "") {
          this.modalTxt = "중복된 아이디 입니다.";
          this.isModalOpen = true;
          return;
        }
        const data = {
          user_id: this.userId,
          user_pwd: this.userPwd,
        };
        const res = await this.$api.postSignUp(data);
        if (res.data?.code == 200) {
          this.modalTxt = "회원가입 성공!";
          this.isModalOpen = true;
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
