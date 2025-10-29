import { useOsTheme } from 'naive-ui';

export const SupportThemes = {
  SYNC_SYSTEM: 'syncSystem',
  LIGHT: 'light',
  DARK: 'dark'
} as const;

export type SupportTheme = (typeof SupportThemes)[keyof typeof SupportThemes];

function getDefaultTheme(): SupportTheme {
  const theme = localStorage.getItem('theme') as SupportTheme;
  return Object.values(SupportThemes).includes(theme)
    ? theme
    : SupportThemes.SYNC_SYSTEM;
}

export function getTheme(theme: SupportTheme): SupportTheme {
  if (theme !== SupportThemes.SYNC_SYSTEM) {
    return theme;
  }
  const osTheme = useOsTheme().value;
  return osTheme === SupportThemes.DARK
    ? SupportThemes.DARK
    : SupportThemes.LIGHT;
}

const IconMap = {
  icon: {
    selector: 'link[rel="icon"]',
    paths: {
      [SupportThemes.DARK]: '/favicon-white.svg',
      [SupportThemes.LIGHT]: '/favicon.svg'
    }
  },
  shortcutIcon: {
    selector: 'link[rel="shortcut icon"]',
    paths: {
      [SupportThemes.DARK]: '/favicon-white.ico',
      [SupportThemes.LIGHT]: '/favicon.ico'
    }
  },
  appleTouchIconPrecomposed: {
    selector: 'link[rel="apple-touch-icon-precomposed"]',
    paths: {
      [SupportThemes.DARK]: '/icon72x72@2x-white.png',
      [SupportThemes.LIGHT]: '/icon72x72@2x.png'
    }
  }
};

export function osThemeChange(theme: string | null) {
  const targetTheme =
    theme === SupportThemes.DARK ? SupportThemes.DARK : SupportThemes.LIGHT;
  Object.values(IconMap).forEach(({ selector, paths }) => {
    const icon = document.querySelector(selector) as HTMLLinkElement;
    if (icon) {
      icon.href = paths[targetTheme];
    }
  });
}

export function themeChange(theme: SupportTheme) {
  document.documentElement.setAttribute('class', `${theme}-theme`);
}

export const defaultTheme: SupportTheme = getDefaultTheme();
