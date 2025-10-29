import { defineConfig } from 'unocss';
import { presetWind4 } from 'unocss';
import presetRemToPx from '@unocss/preset-rem-to-px';
import cssRules from './src/commons/css-rules';

export default defineConfig({
  presets: [presetWind4(), presetRemToPx()],
  rules: cssRules
});
