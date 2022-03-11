import { registerPlugin } from '@capacitor/core';

import type { DeezerSDKPlugin } from './definitions';

const DeezerSDK = registerPlugin<DeezerSDKPlugin>('DeezerSDK', {
  web: () => import('./web').then(m => new m.DeezerSDKWeb()),
});

export * from './definitions';
export { DeezerSDK };
