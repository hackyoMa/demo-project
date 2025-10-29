import IconLog from '~icons/icon-park-outline/log';

export default [
  {
    path: 'personal/:parentId?',
    name: 'files-personal',
    meta: {
      requiresAuth: true,
      title: 'files.personalFile',
      icon: IconLog,
      permission: 'personal_file:read'
    },
    component: () => import('@/views/files/PersonalFile.vue')
  }
];
