<template>
  <MainLayout><router-view /></MainLayout>
</template>
<script>
  import MainLayout from "./components/layout/MainLayout.vue";
  import { defineComponent } from "vue";
  export default defineComponent({
    name: "App",
    components: {
      MainLayout,
    },
  });
</script>
