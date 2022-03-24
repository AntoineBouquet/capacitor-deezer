import { WebPlugin } from '@capacitor/core';

import type { DeezerSDKPlugin } from './definitions';

const errorMessage = "Deezer SDK isn't supported on the web";

export class DeezerSDKWeb extends WebPlugin implements DeezerSDKPlugin {  
  initialize(options: { appId: string; }): Promise<{ result: boolean; }> {
    console.error(errorMessage);
    throw errorMessage;
  }

  login(): Promise<{ result: boolean; }> {
    console.error(errorMessage);
    throw errorMessage;
  }

  logout(): Promise<{ result: any; }> {
    console.error(errorMessage);
    throw errorMessage;
  }

  playTrack(options: { trackId: string; }): Promise<void> {
    console.error(errorMessage);
    throw errorMessage;
  }

  play(): Promise<{ result: boolean; }> {
    console.error(errorMessage);
    throw errorMessage;
  }
  pause(): Promise<{ result: boolean; }> {
    console.error(errorMessage);
    throw errorMessage;
  }
}
