import 'virtual:uno.css';
import '@/style.css';
import type { DialogApi, LoadingBarApi, NotificationApi } from 'naive-ui';
import { createPinia, type Pinia } from 'pinia';
import { createApp } from 'vue';
import App from '@/App.vue';
import http from '@/commons/http';
import i18n from '@/commons/i18n';
import msg from '@/commons/msg';
import {
  hasPermissionDirective,
  hasPermissionOrDirective
} from '@/commons/permission';
import router from '@/router';

declare global {
  interface Window {
    $pinia: Pinia;
    $notice: NotificationApi;
    $dialog: DialogApi;
    $loading: LoadingBarApi;
    $msg: typeof msg;
    $http: typeof http;
    $t: typeof i18n.global.t;
  }
}

const pinia: Pinia = createPinia();

window.$pinia = pinia;
window.$msg = msg;
window.$http = http;
window.$t = i18n.global.t;

createApp(App)
  .use(pinia)
  .use(i18n)
  .use(router)
  .directive('permission', hasPermissionDirective)
  .directive('permission-or', hasPermissionOrDirective)
  .mount('#app');
