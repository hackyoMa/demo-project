import IconLog from '~icons/icon-park-outline/log';

export default [
  {
    path: 'demo',
    name: 'home-demo',
    meta: {
      requiresAuth: true,
      title: 'demoView.title',
      icon: IconLog
    },
    component: () => import('@/views/home/DemoView.vue')
  }
];
