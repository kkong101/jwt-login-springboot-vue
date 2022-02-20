<template>
  <q-dialog v-model="isPopupOpen">
    <q-card>
      <q-card-section>
        <div class="text-h6">{{ title }}</div>
      </q-card-section>
      <q-card-section class="row items-center">
        <q-btn
          class="full-width"
          v-close-popup
          label="Close"
          color="primary"
          :to="reDirect"
        ></q-btn>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>
<script setup>
  defineProps({
    title: String,
    content: String,
    isPopupOpen: Boolean,
    reDirect: String,
  });
</script>
