<template>
  <q-layout view="hHh lpR fFf">
    <Header />

    <q-page-container>
      <router-view />
    </q-page-container>

    <Footer :title="'kkong101'" />
  </q-layout>
</template>
<script setup>
  import Header from "./Header.vue";
  import Footer from "./Footer.vue";
</script>
