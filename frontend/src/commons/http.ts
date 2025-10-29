import { createAlova } from 'alova';
import adapterFetch from 'alova/fetch';
import VueHook from 'alova/vue';
import router from '@/router';
import { mainStore } from '@/store';

const http = createAlova({
  baseURL: '/api',
  statesHook: VueHook,
  requestAdapter: adapterFetch(),
  cacheFor: null,
  beforeRequest: (method) => {
    if (!method.meta || method.meta.loading !== false) {
      window.$loading.start();
    }
    const mStore = mainStore(window.$pinia);
    method.config.headers['Accept-Language'] = mStore.getLanguage;
    const token = mStore.getToken;
    if (token) {
      method.config.headers['Authorization'] = token;
    }
  },
  responded: {
    onSuccess: async (response) => {
      const data = await response.json();
      let code;
      let message;
      if (data) {
        if (data.success) {
          window.$loading.finish();
          return data.data;
        } else {
          code = data.code;
          message = data.message;
        }
      } else {
        code = response.status;
        message = response.statusText;
      }

      if (code === 401) {
        const mStore = mainStore(window.$pinia);
        mStore.setToken(null);
        const currentRoute = router.currentRoute.value;
        if (currentRoute.name !== 'login') {
          router
            .push({
              name: 'login',
              query: { redirect: currentRoute.path }
            })
            .then();
        }
      }

      window.$loading.error();
      window.$msg.error(code, message);
      throw new Error(code + ': ' + message);
    },
    onError: (err) => {
      window.$loading.error();
      window.$msg.error(400, err.message);
      throw new Error(400 + ': ' + err.message);
    }
  }
});

export default http;
