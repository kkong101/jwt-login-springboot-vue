<template>
  <q-footer elevated class="bg-grey-8 text-white">
    <q-toolbar>
      <q-toolbar-title class="row">
        <q-avatar>
          <img src="https://cdn.quasar.dev/logo-v2/svg/logo-mono-white.svg" />
        </q-avatar>
        <div class="q-ml-sm">{{ title }}</div>
      </q-toolbar-title>
    </q-toolbar>
  </q-footer>
</template>
<script setup>
  defineProps({
    title: String,
  });
</script>
<style></style>
