import { DateTime } from 'luxon';
import { mainStore } from '@/store';

function format(date: Date, formatStr = 'PP HH:mm:ss'): string {
  const mStore = mainStore(window.$pinia);
  const language = mStore.getLanguage;
  return DateTime.fromJSDate(date).setLocale(language).toFormat(formatStr);
}

export { format };
