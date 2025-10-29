import { createI18n } from 'vue-i18n';
import enUS from '@/assets/languages/en-US.json';
import zhCN from '@/assets/languages/zh-CN.json';
import router, { getRouteTitle } from '@/router';

export const SupportLanguages = {
  EN_US: 'en-US',
  ZH_CN: 'zh-CN'
} as const;
export type SupportLanguage =
  (typeof SupportLanguages)[keyof typeof SupportLanguages];
type LanguageMessageSchema = typeof enUS;

const languageMessages: Record<SupportLanguage, LanguageMessageSchema> = {
  [SupportLanguages.EN_US]: enUS,
  [SupportLanguages.ZH_CN]: zhCN
};

function getDefaultLanguage(): SupportLanguage {
  const language = localStorage.getItem('language') || navigator.language;
  const matchedLang = Object.values(SupportLanguages).find(
    (lang) => lang.toLowerCase() === language.toLowerCase()
  );
  return matchedLang || SupportLanguages.EN_US;
}

export function languageChange(language: SupportLanguage) {
  (i18n.global.locale as any).value = language;
  document.documentElement.setAttribute('lang', language);
  document.title = getRouteTitle(router.currentRoute.value);
}

export const defaultLanguage: SupportLanguage = getDefaultLanguage();

const i18n = createI18n<[LanguageMessageSchema], SupportLanguage>({
  legacy: false,
  locale: defaultLanguage,
  fallbackLocale: SupportLanguages.EN_US,
  messages: languageMessages
});
export default i18n;
