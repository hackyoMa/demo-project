<template>
  <n-layout-footer bordered class="leading-16 text-center h-16">
    <n-button class="underline" text @click="jumpGithub()">
      Demo Project
    </n-button>
    <n-text> V{{ packageInfo.version }} · Made by </n-text>
    <n-button class="underline" text @click="jumpBlog()"> hackyo</n-button>
  </n-layout-footer>
</template>

<script lang="ts" setup>
import packageInfo from '../../package.json';

function jumpGithub() {
  window.open('https://github.com/hackyoMa/demo-project');
}

function jumpBlog() {
  window.open('https://blog.hackyo.cn');
}
</script>
