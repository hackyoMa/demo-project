import presetRemToPx from '@unocss/preset-rem-to-px';
import { defineConfig, presetWind4 } from 'unocss';
import cssRules from './src/commons/css-rules';

export default defineConfig({
  presets: [presetWind4(), presetRemToPx()],
  rules: cssRules
});
