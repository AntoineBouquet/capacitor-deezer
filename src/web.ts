import { WebPlugin } from '@capacitor/core';

import type { DeezerSDKPlugin } from './definitions';

export class DeezerSDKWeb extends WebPlugin implements DeezerSDKPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
