export interface DeezerSDKPlugin {
  initialize(options: {appId: string}): Promise<{ result: boolean }>;
  login(options: {permissions: string[]}): Promise<{ result: any }>;
  playTrack(options: {trackId: string}): Promise<void>;
}
