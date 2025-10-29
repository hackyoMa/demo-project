import { defineStore } from 'pinia';
import { defaultLanguage, type SupportLanguage } from '@/commons/i18n';
import { defaultTheme, getTheme, type SupportTheme } from '@/commons/theme';

interface MainState {
  language: SupportLanguage;
  theme: SupportTheme;
  sideMenuCollapsed: string | null;
  token: string | null;
  user: any | null;
  permissionIds: string[] | null;
}

export const mainStore = defineStore('main', {
  state: (): MainState => ({
    language: defaultLanguage,
    theme: defaultTheme,
    sideMenuCollapsed: localStorage.getItem('sideMenuCollapsed'),
    token: localStorage.getItem('token') || sessionStorage.getItem('token'),
    user: null,
    permissionIds: null
  }),
  getters: {
    getLanguage(state): SupportLanguage {
      return state.language;
    },
    getTheme(state): SupportTheme {
      return getTheme(state.theme);
    },
    getSideMenuCollapsed(state): boolean {
      return state.sideMenuCollapsed === 'true';
    },
    getToken(state): string | null {
      return state.token;
    },
    getUser(state): any | null {
      return state.user;
    },
    getPermissionIds(state): string[] | null {
      return state.permissionIds;
    }
  },
  actions: {
    setLanguage(language: SupportLanguage) {
      this.language = language;
      localStorage.setItem('language', language);
    },
    setTheme(theme: SupportTheme) {
      this.theme = theme;
      localStorage.setItem('theme', theme);
    },
    setSideMenuCollapsed(sideMenuCollapsed: boolean) {
      this.sideMenuCollapsed = '' + sideMenuCollapsed;
      localStorage.setItem('sideMenuCollapsed', this.sideMenuCollapsed);
    },
    setToken(token: string | null, remember?: boolean) {
      this.token = token;
      if (token) {
        if (remember) {
          localStorage.setItem('token', token);
        } else {
          sessionStorage.setItem('token', token);
        }
      } else {
        this.user = null;
        this.permissionIds = null;
        localStorage.removeItem('token');
        sessionStorage.removeItem('token');
      }
    },
    setUser(user: any) {
      this.permissionIds = user.permissionIds;
      user.permissionIds = null;
      this.user = user;
    }
  }
});
