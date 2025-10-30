<script lang="ts" setup>
import { useRouter } from 'vue-router';

const router = useRouter();

function backIndex() {
  router.push({ name: 'index' });
}
</script>

<template>
  <n-layout class="h-full">
    <n-result
      :title="$t('noFound.title')"
      size="large"
      status="404"
      class="top-1/2 relative -translate-y-2/4">
      <template #footer>
        <n-button @click="backIndex()">{{ $t('noFound.backHome') }}</n-button>
      </template>
    </n-result>
  </n-layout>
</template>
